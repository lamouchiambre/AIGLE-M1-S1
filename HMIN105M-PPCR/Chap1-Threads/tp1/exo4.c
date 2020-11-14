#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;
 
struct image {
  int id;
  int* lenght;
  int* zones; 
};

void* activity(void* p){
  struct image* params = (struct image *) p;

  for (int i = 0; i < *(params->lenght); i++) {
    if (params->zones[i] > params->id) continue;
    pthread_mutex_lock(&mutex);

    if ((params->zones[i] < params->id)) {
      fprintf(stderr, "je vais attendre id %i zones %i\n", params->id, params->zones[i]);
      while (params->zones[i] != params->id) {
	      pthread_cond_wait(&cond, &mutex); 
      }
    }
     
    fprintf(stderr, "Je fais la tâche %i de la zones %i\n", params->id, i);
    params->zones[i] = params->id + 1;
    pthread_cond_broadcast(&cond);
    pthread_mutex_unlock(&mutex);
  }

  return 0;
}

int main (int argc, char* argv[]) {

  int n = 10;
  int zones[n];

  //initialisation zones
  for (int i = 0; i<n; i++) {
    zones[i] = 0;
  }

  // Les threads
  pthread_t* threads = (pthread_t*) malloc(n * sizeof(pthread_t));

  for (int i = 0; i<n; i++) {
   // Specifique à chaque image
    struct image *params = (struct image*) malloc(n * sizeof(struct image) );
    params->id = i; 

    //  Communes
    params->zones = zones;
    params->lenght = &n;
    pthread_create(&threads[i], NULL, activity, params); 
  }

  for (int i=0; i<n; i++) {
    pthread_join(threads[i], NULL);
  }

  return 0;
}