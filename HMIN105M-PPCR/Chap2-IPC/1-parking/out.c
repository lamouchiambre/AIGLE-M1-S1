#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <sys/shm.h>
#include <sys/sem.h>

struct sembuf op[] = {
  { 0, -1, SEM_UNDO }, // lock 
  { 0, 1, SEM_UNDO } // unlock
};

int main(int argc, char* argv[]) {

  key_t key = ftok("key.txt", 'z');
  if (key == -1) {
    perror("ftok");
    exit(1);
  } 
  printf("ftok ok\n");

  int shm_id = shmget(key, 1, IPC_CREAT | 0666);
  if (shm_id == -1) {
    perror("shmget");
    exit(1);
  } 
  printf("shmget ok\n");

  int sem_id = semget(key, 1, IPC_CREAT | 0666);
  if (sem_id == -1) {
    perror("semget");
    exit(1);
  } 
  printf("semget ok\n");

  while (1) {
    printf("test out\n ");
    sleep(1);

    if (semop(sem_id, op, 1) == -1) {
      perror("semop");
      exit(1);
    }  
    printf("semop ok\n");

    printf("test2 out\n ");

    int* nb_places = (int*)shmat(shm_id, NULL, 0);
    if (*nb_places == -1) {
      perror("shmat");
      exit(1);
    } 
    printf("shmat ok\n");

    if (*nb_places > 0) {
      printf("Out : Request accepted.\n");
      (*nb_places)--;
      printf("Out : Ticket printing.\n");
      printf("Out : nb_places : %i\n", (*nb_places));
    } else {
      printf("Out : No car in the parking.\n");
    } 

    if (semop(sem_id, op + 1, 1) == -1) {
      perror("semop");
      exit(1);
    }  
    printf("semop ok\n");

    if (shmdt((void*)nb_places) == -1) {
      perror("shmdt");
      exit(1);
    } 
    printf("shmdt ok\n");
  } 

  return 0;
} 