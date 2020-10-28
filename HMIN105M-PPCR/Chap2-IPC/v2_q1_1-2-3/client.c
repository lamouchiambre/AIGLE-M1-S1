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
struct result {
  long etiq;
  struct contenuResult {
    float nb;
  } contRes;
} msg_res;

int main(int argc, char* argv[]) {

  if (argc != 1) {
    perror("Utilisation : ./client");
  } 

  int msg_id = 

  return 0;
} 