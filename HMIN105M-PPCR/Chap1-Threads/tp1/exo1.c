#include <string.h>
#include <stdio.h> //perror
#include <sys/types.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>

struct paramsFonctionThread {
  int id;
  int nb1;
  int nb2;
};

void* afficheVec(int* vec, int n) {
  printf("[ ");
  for (int i = 0; i < n; i++) {
    printf("%d ", vec[i]);
  }
  printf("]\n");
}

void* fonctionThread(void* p) {
  struct paramsFonctionThread* args = (struct paramsFonctionThread*) p;

  int* result = malloc(sizeof(int));
  *result = args->nb1 * args->nb2;

  pthread_exit(result);
}

int main(int argc, char* argv[]) {
  if (argc < 2) {
    printf("Utilisation : %s nombre_threads\n", argv[0]);
    return 1;
  }     
  pthread_t threads[atoi(argv[1])];
  int vec1[atoi(argv[1])];
  int vec2[atoi(argv[1])];

  for (int j = 0; j < atoi(argv[1]); j++) {
    printf("vec1[%d] = ", j);
    scanf("%d", &vec1[j]);
  }

  for (int j = 0; j < atoi(argv[1]); j++) {
    printf("vec2[%d] = ", j);
    scanf("%d", &vec2[j]);
  }
  
  for (int i = 0; i < atoi(argv[1]); i++) {
    struct paramsFonctionThread* p = malloc(sizeof(struct paramsFonctionThread));
    p->id = i;
    p->nb1 = vec1[i];
    p->nb2 = vec2[i];

    if (pthread_create(&threads[i], NULL, fonctionThread, p) != 0) {
      perror("erreur creation thread");
      exit(1);
    }    
  }
  int result_global = 0;
  int* result = 0; 
  for (int k = 0; k < atoi(argv[1]); k++) {
    pthread_join(threads[k], (void**) &result);
    result_global += *result;
  }
  printf("Produit carthésien : %d\n", result_global);

  afficheVec(vec1, atoi(argv[1]));
  afficheVec(vec2, atoi(argv[1]));

  return 0;
}