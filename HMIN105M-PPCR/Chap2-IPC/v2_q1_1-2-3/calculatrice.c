#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <math.h>
#include <time.h>

#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>

// structure des requetes 
typedef struct requete {
  long etiq; // 1
  struct contenuRequete {
    char op;  // +, -, *, /
    float nb1;
    float nb2;
  } cont;
} msg_req;

// structure des resultats
typedef struct result {
  long etiq;
  struct contenuResult {
    float nb;
  } contRes;
} msg_res;

int main(int argc, char* argv[]) {

  if (argc != 1) {
    perror("Utilisation : ./client");
    exit(1);
  } 

  //msg_req req;
  //msg_res res;
  //req.etiq = getpid();

  //key_t key = ftok("key.txt", 'z'); 
  //if (key == -1) {
  //  perror("ftok");
  //  exit(1);
  //} 
  //printf("ftok ok\n");

  //int msg_id = msgget(key, IPC_CREAT | 0666);
  //if (msg_id == -1) {
  //  perror("msgget");
  //  exit(1);
  //} 
  //printf("msgget ok\n");

  //while (1) {
  //  printf("Calcul : ");
  //  scanf("%d %c %d", &req.cont.nb1, &req.cont.op, &req.cont.nb2);
  //  printf("Envoi de la requete : %d %c %d d'etiquette %ld dans la file d'id %d...\n", req.cont.nb1, req.cont.op, req.cont.nb2, req.etiq, key);

  //  if (msgsnd(key, &req, sizeof(req), 0) == -1) {
  //    perror("msgsnd");
  //    exit(1);
  //  } 
  //  printf("msgsnd ok\n");

  //  if (msgrcv(key, &req, sizeof(req), req.etiq, 0) == -1) {
  //    perror("msgrcv");
  //    exit(1);
  //  } 
  //  printf("msgrcv ok\n");

  //  printf("Resultat : %d\n", res.contRes.nb);
  //}

  return 0;
} 