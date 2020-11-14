#include <string.h>
#include <stdio.h> //perror
#include <sys/types.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include "calcul.h"

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;

struct paramsFonctionThread {
  int n;
  int count;
};

void* fonctionThread(void* p) {
  struct paramsFonctionThread* args = (struct paramsFonctionThread*) p;
  pthread_mutex_lock(&mutex);

  // printf("test0: %d sur %d\n", args->count, args->n);
  args->count = args->count + 1;
  printf("test1: %d sur %d\n", args->count, args->n);

  if (args->count == args->n) {
    printf("args->count == args->n: %d sur %d\n", args->count, args->n);
    pthread_cond_broadcast(&cond);
  } else {
    pthread_cond_wait(&cond, &mutex);
  }

  pthread_mutex_unlock(&mutex);
  pthread_exit(args);
}

// Programme
int main(int argc, char* argv[]) {
  if (argc < 2) {
    printf("Utilisation : %s nombre_threads\n", argv[0]);
    return 1;
  }     
  pthread_t threads[atoi(argv[1])];
  // pthread_cond_t cond;
  struct paramsFonctionThread* params = malloc(sizeof(struct paramsFonctionThread));
  params->n = atoi(argv[1]);
  params->count = 0;
  
  
  // Paramètres et threads
  for (int i = 0; i < atoi(argv[1]); i++) {
    if (pthread_create(&threads[i], NULL, fonctionThread, params) != 0) {
      perror("erreur creation thread");
      exit(1);
    }    
  }

  // Join 
  for (int k = 0; k < atoi(argv[1]); k++) {
    pthread_join(threads[k], NULL);
  }

  return 0;
}