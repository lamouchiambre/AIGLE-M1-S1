#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

int main(int argc, char* argv[]) {

  if (argc != 2) {
    perror("Utilisation : ./ipc 10");
    exit(1);
  }

  int places = atoi(argv[1]);

  // Création fichier partagée
  printf("Creation du fichier partagee...\n");
  int fd = open("parking_shared.txt", O_RDWR | O_CREAT, 0666);
  close(fd);

  // Création clé d'accès
  printf("Creation de la cle d'acces IPC...\n");
  key_t key = ftok("parking_shared.txt", 1);
  if (key == -1) {
    perror("ftok");
    exit(1);
  } 

  // Récupération segment de mémoire partagée
  printf("Recuperation du segment memoire partagee...\n");
  // deux elements : un pour le nb de place actuel et un pour le max
  int sh_id = shmget(key, sizeof(int) * 2, IPC_CREAT | 0666);
  printf("Le segment est %i\n", sh_id);
  if (sh_id == -1) {
    perror("shmget");
    exit(1);
  } 

  // Attachement de la variable partagée
  printf("Attachement de la variable partagee...\n");
  int* nb_places = (int*) shmat(sh_id, NULL, 0);
  printf("L'adresse du nombre de places est %i\n", *nb_places);
  if ((*nb_places) == -1) {
    perror("shmat");
    exit(1);
  } 

	// affecte le nombre de places à la variable partagée
  nb_places[0] = places;
  nb_places[1] = places;
  printf("Le nombre de places libres est de %i sur %i\n", nb_places[0],nb_places[1]);

  // Détachement du segment mémoire partagé
  printf("Detachement du segment memoire partage...\n");
  if (shmdt((void*)nb_places) == -1) {
    perror("shmdt");
    exit(1);
  } 

  return 0;
} 