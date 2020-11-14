#include <string.h>
#include <stdio.h>//perror
#include <sys/types.h>
#include <stdlib.h>
#include <unistd.h>
// #include <iostream>
#include <pthread.h>

struct params {
  int id;
};

void* fonction(void* p) {
  struct params* params = (struct params*) p;
  // fprintf(stderr,"fonction: id %i\n", params->id);

  int var = 0;
  for(int i = 0; i < 10; i++) {
    fprintf(stderr, "id: %i, var = %i\n", params->id, var++);
  }
}

int main(int argc, char * argv[]) {
  if (argc < 2) {
    printf("utilisation: %s nombre_threads\n", argv[0]);
    return 1;
  }     
  int n = atoi(argv[1]);
  
  pthread_t* threads = (pthread_t*) malloc(n * sizeof(pthread_t));

  for (int i = 0; i < n; i++) {
    struct params* params = (struct params*) malloc(n * sizeof(struct params));
    params->id = i;
    if (pthread_create(threads, NULL, fonction, params) != 0) {
      perror("erreur creation thread");
      exit(1);
    }
  }

  for (int i = 0; i < n; i++) {
    pthread_join(threads[i], NULL);
  }

  // printf("saisir un caractère \n");
  // fgetc(stdin);

  return 0;
}