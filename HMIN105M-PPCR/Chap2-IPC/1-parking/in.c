#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/sem.h>

int main(int argc, char* argv[]) {

  struct sembuf op;
  // op.sem_flg = SEM_UNDO;
  op.sem_num = 0;
  op.sem_flg = -1; // allocate = -1, deallocate = 1
  
  // Création de la clé d'accès
  printf("Creation de la cle d'acces IPC...\n");
  key_t key = ftok("parking_shared.txt", 1);
  if (key == -1) {
    perror("ftok");
    exit(1);
  } 
  printf("La cle est %i\n", key);

  // Identification du segment mémoire partagée
  printf("Acces au segment de memoire partagee...\n");
  int sh_id = shmget(key, sizeof(int) * 2, IPC_CREAT | 0666);
  printf("Le segment est %i\n", sh_id);
  if (sh_id == -1) {
    perror("shmget");
    exit(1);
  } 

  // Attachement de la variable partagée
  printf("Attachement de la variable partagee...\n");
  int* nb_places = (int*) shmat(sh_id, NULL, SHM_W);
	printf("L'adresse du nombre de places est %ls\n", nb_places);
  if (nb_places == (int*) (-1)) {
    perror("shmat");
    exit(1);
  }
	printf("Le nombre de places libre au depart est de %i sur %i\n", nb_places[0],nb_places[1]);

  // Création d'un semaphore
  printf("Creation d'un semaphore...\n");
  int sem_id = semget(key, 1, IPC_CREAT | 0666);
	printf("Le semaphore est %i\n", sem_id);
  if (sem_id == -1) {
    perror("semget");
    exit(1);
  }
  printf("Fin initialisation...\n\n");

  int iteration = 0;
  while (1) {
    printf("Itération %i\n", iteration++);
    printf("Le nombre de places libres est de %i sur %i\n", nb_places[0], nb_places[1]);

    // je tente de prendre le sem. sem_op contient -1 donc allocation (ou attente)
    // note: le dernier param est 1 car on envoie une seule struct
    printf("Prise de semaphore...\n");
    op.sem_op = -1;
    // semop(sem_id, &op, 1);
    // if (semop(sem_id, &op, 1) == -1) {
    //   perror("semop");
    //   exit(1);
    // }
    while (semop(sem_id, &op, 1) == -1) {}
    
    if (nb_places[0] > 0) {
      printf("In : Request accepted.\n");
      (*nb_places)--;
      printf("In : Ticket printing.\n");
      printf("In : Number of places : %i to %i\n", nb_places[0], nb_places[1]);
    } else {  
      printf("In : No free places.\n");
    } 

    // je relache le sem, sem_op contient 1 donc desallocation
    // note: le dernier param est 1 car on envoie une seule struct
    printf("Relache de semaphore...\n");
    op.sem_op = 1;
    if (semop(sem_id, &op, 1) == -1) {
      perror("semop");
      exit(1);
    }  

    printf("j'attends 1 seconde...\n");
    sleep(1);
    printf("\n");
  } // fin boucle while
  
  // vu la boucle infinie, on ne passe jamais là... mais pour plus tard...
  printf("Détachement du segment mémoire partagé\n");
  if (shmdt((void*) nb_places) == -1) {
    perror("shmdt");
    exit(1);
  } 

  return 0;
} 