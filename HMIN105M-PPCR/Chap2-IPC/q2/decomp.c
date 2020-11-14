#include <stdlib.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <unistd.h>
#include <stdio.h>
// #include <errno.h>

typedef struct msg {
  long etiq;
  struct contenu {
    int n;
  } cont;
} msg;

int main(int argc, char* argv[]) {
  if (argc != 2) {
    perror("Utilisation: ./decomp n");
    exit(1);
  } 

  msg msg;


  return 0;
} 
