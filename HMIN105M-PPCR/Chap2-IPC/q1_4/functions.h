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

// structure des requetes 
struct requete {
  long etiq; // 1
  struct contenuRequete {
    char op;  // +, -, *, /
    float nb1;
    float nb2;
  } cont;
} req;

// structure des resultats
struct result {
  long etiq;
  struct contenuResult {
    float nb;
  } contRes;
} res;

// void calculation_init(float *a, float *b, char *c);
float calculation(float a, float b, char operator); 

#endif 