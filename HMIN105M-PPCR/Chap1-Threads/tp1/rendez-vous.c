#include <string.h>
#include <stdio.h> //perror
#include <sys/types.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include "calcul.h"

struct paramsFonctionThread {
  int id;
};

void* fonctionThread(void* p) {
  struct paramsFonctionThread* args = (struct paramsFonctionThread*) p;

  calcul(1);

  pthread_exit(NULL);
}

// Programme
int main(int argc, char* argv[]) {
  if (argc < 2) {
    printf("Utilisation : %s nombre_threads\n", argv[0]);
    return 1;
  }     
  pthread_t threads[atoi(argv[1])];
  
  // Paramètres et threads
  for (int i = 0; i < atoi(argv[1]); i++) {
    struct paramsFonctionThread* p = malloc(sizeof(struct paramsFonctionThread));

    if (pthread_create(&threads[i], NULL, fonctionThread, p) != 0) {
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