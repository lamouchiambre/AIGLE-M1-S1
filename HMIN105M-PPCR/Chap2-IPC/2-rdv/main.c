#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <sys/stat.h>
#include <sys/types.h>

// int semctl(int semid, int semnum, int cmd, ...);

// union semun {
//   int val ; /* cmd = SETVAL */
//   struct semid ds *buf ; /* cmd = IPC STAT ou IPC SET */
//   unsigned short *array ; /* cmd = GETALL ou SETALL */
//   struct seminfo * buf ; /* cmd = IPC INFO (sous Linux) */
// } ;

// semun egCtrl;
// egCtrl.val=1;
// if(semctl(idSem, 0, SETVAL, egCtrl) == -1) {
//   perror(’’probl`eme init’’);
//   //suite
// }

struct params {
  int id;
  int* size;
  int* current_task;
};

void* activity() {//void* p) {
  // struct params* params = (struct params*) p;

  // for (int i = 0; i < *params->size; i++) {
  //   printf("I do the task %i to %i", params->current_task[i], (*params->size));
  // }

  return 0;
} 

int main(int argc, char* argv[]) {

  if (argc != 2) {
    perror("Utilisation : ./main 10");
    exit(1);
  }

  // Initialisation des variables
  int n = atoi(argv[1]);
  pthread_t* threads = (pthread_t*) malloc(n * sizeof(pthread_t));
  int task[n];

  // Génération du fichier partagée
  printf("Generation du fichier partagee...\n");
  int fd = open("shared.txt", O_RDWR | O_CREAT, 0666);
  close(fd);

  // Création clé d'accès
  printf("Creation cle d'acces IPC...\n");
  key_t key = ftok("shared.txt", 1);
  if (key == -1) {
    perror("ftok");
    exit(1);
  }

  // Récupération segment de mémoire partagée
  printf("Recuperation du segment memoire partagee...\n");
  int sh_id = shmget(key, sizeof(int), IPC_CREAT | 0666);
  printf("Le segment est %i\n", sh_id);
  if (sh_id == -1) {
    perror("shmget");
    exit(1);
  } 

  // Attachement de la variable partagée
  printf("Attachement de la variable partagee...\n");
  int* attach = (int*) shmat(sh_id, NULL, 0);
  printf("L'adresse des taches est %i\n", *attach);
  if ((*attach) == -1) {
    perror("shmat");
    exit(1);
  } 

  for (int i = 0; i < n; i++) {
    // struct params* params = (struct params*) malloc(n * sizeof(struct params));

    // params->id = i;
    // params->size = &n;

    // pthread_create(threads, NULL, activity, params);
    pthread_create(threads, NULL, activity, NULL);
  }  



  return 0;
}