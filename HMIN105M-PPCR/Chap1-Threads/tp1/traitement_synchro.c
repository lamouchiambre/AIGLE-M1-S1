#include <string.h>
#include <stdio.h> //perror
#include <sys/types.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include "calcul.h"

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;

struct picture {
  int current_zone;
  int max_zone;  
  int current_activity;
  int* zones;
  // int* zones = (int*) malloc(sizeof(int) * max_zone);
};

void* activity0(void* args) {
  struct picture* pic = (struct picture*) args;
  int activity = 0;
  pthread_mutex_lock(&mutex);

  printf("J'effectue l'activté %d sur la zone %d\n", activity, pic->current_zone);
  pic->current_zone = pic->current_zone + 1;
  printf("current_zone / max_zone : %d / %d\n", pic->current_zone, pic->max_zone);

  if (pic->current_zone == pic->max_zone) {
    pthread_cond_broadcast(&cond);
  } else {
    pthread_cond_wait(&cond, &mutex);
  }

  pthread_mutex_unlock(&mutex);
  pthread_exit(NULL);
}

void* activity1(void* args) {
  struct picture* pic = (struct picture*) args;
  int activity = 1;
  pthread_mutex_lock(&mutex);

  printf("J'effectue l'activté %d sur la zone %d\n", activity, pic->current_zone);
  pic->current_zone = pic->current_zone + 1;
  printf("current_zone / max_zone : %d / %d\n", pic->current_zone, pic->max_zone);

  if (pic->current_zone == pic->max_zone) {
    printf("test2\n");
    pthread_cond_broadcast(&cond);
  } else {
    pthread_cond_wait(&cond, &mutex);
  }
  pthread_mutex_unlock(&mutex);
  pthread_exit(NULL);
}

// Programme
int main(int argc, char* argv[]) {
  if (argc < 2) {
    printf("Utilisation : %s nombre_threads\n", argv[0]);
    return 1;
  }     
  pthread_t threads[atoi(argv[1])];
  pthread_cond_t cond;
  struct picture* pic = malloc(sizeof(struct picture));
  pic->current_zone = 0;
  pic->current_activity = 1;
  pic->max_zone = atoi(argv[1]);
  pic->zones = malloc(sizeof(int) * pic->max_zone);
  

  // Paramètres et threads
  if (pthread_create(&threads[0], NULL, activity0, &pic) != 0) {
    perror("erreur creation thread");
    exit(1);
  } 
  if (pthread_create(&threads[1], NULL, activity1, &pic) != 0) {
    perror("erreur creation thread");
    exit(1);
  }   

  // Join 
  for (int k = 0; k < atoi(argv[1]); k++) {
    pthread_join(threads[k], NULL);
  }

  return 0;
}