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

void* activity(void* p) {
  struct image* params = (struct image*) p;
  // fprintf(stderr, "activity %i\n", params->id);

  for (int i = 0; i < *params->lenght; i++) {
    
    if (params->zones[i] <= params->id) { // zone courante <= task
      pthread_mutex_lock(&mutex);
      
      if (params->zones[i] < params->id) { 
        fprintf(stderr, "zones[%i] expected by %i\n", i, params->id);

        while (params->zones[i] != params->id) {
	        pthread_cond_wait(&cond, &mutex); 
        }
      } 
    }
    // fprintf(stderr, "task %i: zone %i processed\n", params->id, i);
    fprintf(stderr, "zones[%i] = %i ok\n", i, params->zones[i] );
    params->zones[i] = params->id + 1;
    pthread_cond_broadcast(&cond);
    pthread_mutex_unlock(&mutex);
  } 
  return 0;
} 

int main(int argc, char* argv[]) {

  int n = 10;
  int zones[n];

  //initialisation zones
  for (int i = 0; i < n; i++) {
    zones[i] = 0;
  }

  pthread_t* threads = (pthread_t*) malloc(n * sizeof(pthread_t));

  for (int i = 0; i < n; i++) {
    struct image* params = (struct image*) malloc(n * sizeof(struct image));

    params->id = i;
    params->lenght = &n;
    params->zones = zones;

    pthread_create(threads, NULL, activity, params);
  }  

  for (int i = 0; i < n; i++) {
    pthread_join(threads[i], NULL);
  } 

  return 0;
}