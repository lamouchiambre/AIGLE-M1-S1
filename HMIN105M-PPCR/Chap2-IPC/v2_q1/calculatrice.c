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
int add(msg_req* req, int msg_id){
  msg_res res;
  res.etiq = req->etiq;
  res.contRes.nb = req->cont.nb1 + req->cont.nb2;
  // printf("res.etiq : %li\n", res.etiq);
  // printf("res.contRes.nb = req->cont.nb1 + req->cont.nb2 : %f = %f + %f\n", req->cont.nb1 + req->cont.nb2, req->cont.nb1, req->cont.nb2);

  if (msgsnd(msg_id, &res, sizeof(res), 0) == -1) {
    perror("msgsnd");
    exit(1);
  }

  return res.contRes.nb;
}

int sub(msg_req* req, int msg_id){
  msg_res res;
  res.etiq = req->etiq;
  res.contRes.nb = req->cont.nb1 - req->cont.nb2;

  if (msgsnd(msg_id, &res, sizeof(res), 0) == -1) {
    perror("msgsnd");
    exit(1);
  }

  return res.contRes.nb;
}

int mult(msg_req* req, int msg_id){
  msg_res res;
  res.etiq = req->etiq;
  res.contRes.nb = req->cont.nb1 * req->cont.nb2;

  if (msgsnd(msg_id, &res, sizeof(res), 0) == -1) {
    perror("msgsnd");
    exit(1);
  }

  return res.contRes.nb;
}

int divi(msg_req* req, int msg_id){
  msg_res res;
  res.etiq = req->etiq;
  res.contRes.nb = req->cont.nb1 / req->cont.nb2;

  if (msgsnd(msg_id, &res, sizeof(res), 0) == -1) {
    perror("msgsnd");
    exit(1);
  }

  return res.contRes.nb;
}

int eval(msg_req* req, int msg_id){
  switch (req->cont.op) {
    case '+':
      return add(req, msg_id);
      break;
    case '-':
      return sub(req, msg_id);
      break;
    case '*':
      return mult(req, msg_id);
      break;
    case '/':
      return divi(req, msg_id);
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
  printf("msgget ok. msg_id : %i\n", msg_id);
  
  msg_req req;
  printf("Creation de la file de message...\n");
  printf("ID FILE : %d\n", msg_id);
  printf("Creation des calculatrices...\n");
    
  while (1) {
    if (msgrcv(msg_id, &req, sizeof(req), 0, 0) == -1) {
      perror("msgrcv");
      exit(1);
    } else {
      result = eval(&req, msg_id);
      printf("Requete + reçu : %f %c %f\n", req.cont.nb1, (char)req.cont.op, req.cont.nb2);
      printf("Resultat : %d\n", result);
    } 
  }

  printf("Destruction de la file de message...\n");
  msgctl(msg_id, IPC_RMID, NULL);
	return 0;
}