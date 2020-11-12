#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <pthread.h>
#include <fcntl.h>
#include <signal.h>

// numéro du sémaphore, opération sur le sémaphore, options (ex SEM_UNDO)

/*
struct sembuf {
    ushort sem_num;
    short sem_op;
    short sem_flg;
};
*/

/*
Of course, sem_num is the number of the semaphore in the set that you want to manipulate.
Then, sem_op is what you want to do with that semaphore.
*/

/* sem_op description :
Negative	Allocate resources. Block the calling process until the value of the semaphore is greater than or equal to the absolute value of sem_op. (That is, wait until enough resources have been freed by other processes for this one to allocate.) Then add (effectively subtract, since it's negative) the value of sem_op to the semaphore's value.
Positive	Release resources. The value of sem_op is added to the semaphore's value.
Zero		This process will wait until the semaphore in question reaches 0.
*/

/* sem_flg description :
Another very useful flag is the SEM_UNDO flag. This causes semop() to record, in a way, the change made to the semaphore.
When the program exits, the kernel will automatically undo all changes that were marked with the SEM_UNDO flag.
Of course, your program should do its best to deallocate any resources it marks using the semaphore,
but sometimes this isn't possible when your program gets a SIGKILL or some other awful crash happens.
*/

void signal_handler(int signo) {

}

int main(int argc, char** argv) {
	// structure de controle de semaphore (cf commentaire + haut)
	struct sembuf op;
	op.sem_flg = SEM_UNDO;
	op.sem_num = 0;
	op.sem_flg = -1; // allocate = -1, deallocate = 1

	// génération de la clé
	const char* filename = "parking_shared.txt";
    printf("Creation de la clé d'acces IPC...\n");
    key_t key = ftok(filename, 1); 
	printf("La clé est %i\n", key);

    // recup du segment de mémoire
	printf("Accès au segment de mémoire partagée...\n");
    int sh_id = shmget(key, sizeof(int)*2, IPC_CREAT|0666);
	printf("Le segment est %i\n", sh_id);
    if (sh_id == -1) {
        perror("shmget");
        exit(1);
    }

    // j'attache le nb de place au segment partagé
    int* nb_places = (int*)shmat(sh_id, NULL, SHM_W);
	printf("L'addresse du nombre de places est %ls\n", nb_places);
    if (nb_places == (int *)(-1)) {
        perror("shmat");
        exit(1);
    }
	printf("Le nombre de places libre au départ est de %i sur %i\n", nb_places[0],nb_places[1]);

    // j'alloue un semaphore
    int sem_id = semget(key, 1, IPC_CREAT|0666);
	printf("Le semaphore est %i\n", sem_id);
    if (sem_id == -1) {
        perror("semget");
        exit(1);
    }

	int error;
    int iterations = 0;

    while (1) {
    	iterations++;
    	printf("Itération %i\n", iterations);
    	printf("Le nombre de places libres est de %i sur %i\n", nb_places[0],nb_places[1]);

        // je tente de prendre le sem. sem_op contient -1 donc allocation (ou attente)
    	// note: le dernier param est 1 car on envoie une seule struct
    	printf("prise de semaphore...\n");
    	op.sem_op = -1;
    	error = semop(sem_id, &op, 1);

    	// je fait mon taf...
        if (nb_places[0] > 0) {
            printf("Demande accepté\n");
            (*nb_places)--;
            printf("Impression ticket\n");
        	printf("Le nombre de places restantes est de %i sur %i\n", nb_places[0],nb_places[1]);
        } else {
            printf("Pas de place !\n");
        }

        // je relache le sem, sem_op contient 1 donc desallocation
        // note: le dernier param est 1 car on envoie une seule struct
    	printf("relache de semaphore...\n");
        op.sem_op = 1;
    	error = semop(sem_id, &op, 1);

        printf("j'attends...\n");
        sleep(1);
    }

	// vu la boucle infinie, on ne passe jamais là... mais pour plus tard...
    printf("Détachement du segment mémoire partagé\n");
	error = shmdt((void*)nb_places);
    return 0;	
}
