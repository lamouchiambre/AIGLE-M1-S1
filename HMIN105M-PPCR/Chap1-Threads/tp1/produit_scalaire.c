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

// Renvoie le résultat de la multiplication
void* fonctionThread(void* p) {
  struct paramsFonctionThread* args = (struct paramsFonctionThread*) p;

  int* thread_result = malloc(sizeof(int));
  *thread_result = args->nb1 * args->nb2;

  pthread_exit(thread_result);
}

int main(int argc, char* argv[]) {
  if (argc < 2) {
    printf("Utilisation : %s nombre_threads\n", argv[0]);
    return 1;
  }     
  pthread_t threads[atoi(argv[1])];
  int vec1[atoi(argv[1])];
  int vec2[atoi(argv[1])];
  int produit_scalaire = 0;
  int* thread_result = NULL; 

  // Remplissage des deux tableaux
  for (int i = 0; i < atoi(argv[1]); i++) {
    printf("vec1[%d] = ", i);
    scanf("%d", &vec1[i]);
  }
  for (int i = 0; i < atoi(argv[1]); i++) {
    printf("vec2[%d] = ", i);
    scanf("%d", &vec2[i]);
  }
  
  // Paramètres et threads
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

  // Join et somme des résultats de chaque thread
  for (int k = 0; k < atoi(argv[1]); k++) {
    pthread_join(threads[k], (void**) &thread_result);
    produit_scalaire += *thread_result;
  }
  printf("Produit carthésien : %d\n", produit_scalaire);

  afficheVec(vec1, atoi(argv[1]));
  afficheVec(vec2, atoi(argv[1]));

  return 0;
}