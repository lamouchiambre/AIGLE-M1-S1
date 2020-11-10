#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <sys/shm.h>
#include <sys/types.h>

typedef struct parking {
  int places;
} parking;

int main(int argc, char* argv[]) {

  parking park;
  park.places = 40;
  
  key_t key = ftok("key.txt", 'z');
  if (key == -1) {
    perror("ftok");
    exit(1);
  } 
  printf("ftok ok\n");

  int shm_id = shmget(key, sizeof(int), IPC_CREAT | 0666);
  if (shm_id == -1) {
    perror("shmget");
    exit(1);
  } 
  printf("shmget ok\n");

  int* nb_places = (int*)shmat(shm_id, NULL, 0);
  if ((*nb_places) == -1) {
    perror("shmat");
    exit(1);
  } 
  printf("shmat ok\n");

  if (shmdt((void*)nb_places) == -1) {
    perror("shmdt");
    exit(1);
  } 
  printf("shmdt ok\n");

  return 0;
} 