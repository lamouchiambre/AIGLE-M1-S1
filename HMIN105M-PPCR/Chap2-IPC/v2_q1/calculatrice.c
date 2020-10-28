#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <pthread.h>
#include <fcntl.h>

typedef struct requete {
  long etiq; // 1
  struct contenuRequete {
    char op;  // +, -, *, /
    float nb1;
    float nb2;
  } cont;
} msg_req;

typedef struct result {
  long etiq;
  struct contenuResult {
    float nb;
  } contRes;
} msg_res;

// Evalue le calcule et reponde a l'envoyeur:
int add(msg_req* req, int key){
  msg_res res;
  res.etiq = req->etiq;
  res.contRes.nb = req->cont.nb1 + req->cont.nb2;

  if (msgsnd(key, &res, sizeof(res), 0) == -1) {
    perror("msgsnd");
    exit(1);
  }

  return res.contRes.nb;
}

int sub(msg_req* req, int key){
  msg_res res;
  res.etiq = req->etiq;
  res.contRes.nb = req->cont.nb1 - req->cont.nb2;

  if (msgsnd(key, &res, sizeof(res), 0) == -1) {
    perror("msgsnd");
    exit(1);
  }

  return res.contRes.nb;
}

int mult(msg_req* req, int key){
  msg_res res;
  res.etiq = req->etiq;
  res.contRes.nb = req->cont.nb1 * req->cont.nb2;

  if (msgsnd(key, &res, sizeof(res), 0) == -1) {
    perror("msgsnd");
    exit(1);
  }

  return res.contRes.nb;
}

int divi(msg_req* req, int key){
  msg_res res;
  res.etiq = req->etiq;
  res.contRes.nb = req->cont.nb1 / req->cont.nb2;

  if (msgsnd(key, &res, sizeof(res), 0) == -1) {
    perror("msgsnd");
    exit(1);
  }

  return res.contRes.nb;
}

int eval(msg_req* req, int etiq){
  switch (req->cont.op) {
    case '+':
      return add(req, etiq);
      break;
    case '-':
      return sub(req, etiq);
      break;
    case '*':
      return mult(req, etiq);
      break;
    case '/':
      return divi(req, etiq);
      break;
    default:
      printf("Type de calcul inconnue !\n");
      return -1;
      break;
  }
}

int main() {
  int result;
  
  key_t key = ftok("key.txt", 'z'); 
  if (key == -1) {
    perror("ftok");
    exit(1);
  } 
  printf("ftok ok\n");

  int msg_id = msgget(key, IPC_CREAT | 0666);
  if (msg_id == -1) {
    perror("msgget");
    exit(1);
  } 
  printf("msgget ok\n");
  
  msg_req req;
  printf("Creation de la file de message...\n");
  printf("ID FILE : %d\n", key);
  printf("Creation des calculatrices...\n");
    
  while (1) {
    if (msgrcv(key, &req, sizeof(req), 0, 0) == -1) {
      perror("msgrcv");
      exit(1);
    } 

    result = eval(&req, req.etiq);
    printf("Requete + reçu : %f %c %f\n", req.cont.nb1, (char)req.cont.op, req.cont.nb2);
    printf("Resultat : %d\n", result);
  }

  printf("Destruction de la file de message...\n");
  msgctl(key, IPC_RMID, NULL);
	return 0;
}