#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <sys/shm.h>
#include <pthread.h>
#include <fcntl.h>

int parking_init(char** filename, int places) {
	
	printf("Création du fichier partagé\n");
	int fd = open(filename, O_RDWR | O_CREAT, 0666);
	// ferme le fichier
	close(fd);

	printf("Création de la clé d'accès IPC\n");
	key_t key = ftok(filename, 1);
	printf("La clé est %i\n", key);

	printf("Création du segment mémoire partagé\n");
	// deux elements, un pour le nb de place actuel, et un pour le max
	int sh_id = shmget(key, sizeof(int)*2, 0666 | IPC_CREAT);
	printf("Le segment est %i\n", sh_id);
    if (sh_id == -1) {
        perror("shmget ");
        exit(1);
    }

    printf("Initialisation de la variable partagée\n");
	int *nb_places = shmat(sh_id, (void*)0, 0);
	printf("L'addresse du nombre de places est %i\n", nb_places);
    if (nb_places == (int *)(-1)) {
        perror("shmat");
        exit(1);
    }

	// affecte le nombre de places à la variable partagée
	nb_places[0] = places;
	nb_places[1] = places;
	printf("Le nombre de places libres est de %i sur %i\n", nb_places[0],nb_places[1]);

	printf("Détachement du segment mémoire partagé\n");
	int error = shmdt((void*)nb_places);

	return sh_id;

}

// destruction du segment de mémoire partagée.
int parking_destroy(int sh_id){
    return shmctl(sh_id, IPC_RMID, NULL);
}

int main(int argc, char** argv) {

	parking_init("parking_shared.txt", atoi(argv[1]));
	return 0;

}
