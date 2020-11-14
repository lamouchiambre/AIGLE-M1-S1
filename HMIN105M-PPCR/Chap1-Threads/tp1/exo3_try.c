#include <string.h>
#include <stdio.h>//perror
#include <sys/types.h>
#include <stdlib.h>
#include <unistd.h>
// #include <iostream>
#include <pthread.h>

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;

struct params {
  int n;
  int count;
};

void* fonction(void* params) {
  struct params* p = (struct params*) params;
  pthread_mutex_lock(&mutex);

  p->count += 1;
  fprintf(stderr, "%i sur %i\n", p->count, p->n);

  if (p->count == p->n) {
    fprintf(stderr, "dans le if: %i sur %i -> broadcast\n", p->count, p->n);
    pthread_cond_broadcast(&cond);
  } else {
    pthread_cond_wait(&cond, &mutex);
  }

  pthread_mutex_unlock(&mutex);
  pthread_exit(params);
}

int main(int argc, char * argv[]) {
  if (argc < 2) {
    printf("utilisation: %s nombre_threads\n", argv[0]);
    return 1;
  }     
  int n = atoi(argv[1]);
  pthread_t threads[n];
  struct params* params = (struct params*) malloc(sizeof(struct params));

  params->n = n;
  params->count = 0;

  for (int i = 0; i < n; i++) {
    if (pthread_create(&threads[i], NULL, fonction, params) != 0) {
      perror("erreur creation thread");
      exit(1);
    }
  }

  for (int i = 0; i < n; i++) {
    pthread_join(threads[i], NULL); 
  }

  return 0;
}