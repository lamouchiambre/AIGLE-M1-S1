#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/ipc.h>
// #include <sys/msg.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

int main(int argc, char* argv[]) {

  int places = atoi(argv[1]);

  printf("Création du fichier partagé\n");
  int fd = open("parking_shared.txt", O_RDWR | O_CREAT, 0666);
  close(fd);

  printf("Création de la clé d'accès\n");
  key_t key = ftok("parking_shared.txt", 1);
  if (key == -1) {
    perror("ftok");
    exit(1);
  } 

  printf("Création du segment mémoire partagé\n");
  // deux elements : un pour le nb de place actuel et un pour le max
  int shm_id = shmget(key, sizeof(int) * 2, IPC_CREAT | 0666);
  printf("Le segment est %i\n", shm_id);
  if (shm_id == -1) {
    perror("shmget");
    exit(1);
  } 

  printf("Initialisation de la variable partagée\n");
  int* nb_places = (int*)shmat(shm_id, NULL, 0);
  printf("L'addresse du nombre de places est %i\n", *nb_places);
  if ((*nb_places) == -1) {
    perror("shmat");
    exit(1);
  } 

  if (shmdt((void*)nb_places) == -1) {
    perror("shmdt");
    exit(1);
  } 
  printf("shmdt ok\n");

  return 0;
} 