#ifndef FUNCTIONS_H
#define FUNCTIONS_H 

#include <stdlib.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <unistd.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <pthread.h> 
#include <assert.h> 

#define RED "\x1B[31m"
#define GRN "\x1B[32m"
#define YEL "\x1B[33m"
#define BLU "\x1B[34m"
#define MAG "\x1B[35m"
#define CYN "\x1B[36m"
#define WHT "\x1B[37m"
#define RESET "\x1B[0m"

// structure des requetes 
struct requete {
  long etiq; // 1
  struct contenuRequete {
    char op;  // +, -, *, /
    float nb1;
    float nb2;
  } cont;
  pid_t clientPID;
} req;

// structure des resultats
struct result {
  long etiq;
  struct contenuResult {
    float nb;
  } contRes;
} res;

void calculation_init(float *a, float *b, char *c);
float calculation(float a, float b, char operator); 

#endif 