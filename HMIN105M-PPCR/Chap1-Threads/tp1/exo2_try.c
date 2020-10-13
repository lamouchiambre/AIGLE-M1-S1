#include <string.h>
#include <stdio.h>//perror
#include <sys/types.h>
#include <stdlib.h>
#include <unistd.h>
// #include <iostream>
#include <pthread.h>

struct params {
  int id;
  int nb1;
  int nb2;
};

void* fonction(void* p) {
  struct params* args = (struct params*) p;
  // fprintf(stderr,"fonction: id %i\n", params->id);

  int* threadResult = malloc(sizeof(int));
  *threadResult = args->nb1 * args->nb2; 

  fprintf(stderr, "threadResult = %i\n", *threadResult);
  pthread_exit(threadResult);
}

int main(int argc, char * argv[]) {
  if (argc < 2) {
    printf("utilisation: %s nombre_threads\n", argv[0]);
    return 1;
  }     
  int n = atoi(argv[1]);
  int vec1[n];
  int vec2[n];
  int produitScalaire = 0;
  int* threadResult = NULL;

  for (int i = 0; i < n; i++) {
    printf("vec1[%d] = ", i);
    scanf("%d", &vec1[i]);

    printf("vec2[%d] = ", i);
    scanf("%d", &vec2[i]);

    printf("\n");
  }
  
  // pthread_t* threads = (pthread_t*) malloc(n * sizeof(pthread_t));
  pthread_t threads[n];

  for (int i = 0; i < n; i++) {
    struct params* params = (struct params*) malloc(n * sizeof(struct params));
    params->id = i;
    params->nb1 = vec1[i];
    params->nb2 = vec2[i];

    if (pthread_create(&threads[i], NULL, fonction, params) != 0) {
      perror("erreur creation thread");
      exit(1);
    }
  }

  for (int i = 0; i < n; i++) {
    pthread_join(threads[i], (void**) &threadResult);
    fprintf(stderr, "Avant: Le produit scalaire est %i\n", produitScalaire);  
    produitScalaire += *threadResult; 
    fprintf(stderr, "Après: Le produit scalaire est %i\n", produitScalaire);  
  }

  printf("Fin: Le produit scalaire est %i\n", produitScalaire);

  return 0;
}