#include <stdlib.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <unistd.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>

  // structure des requetes 
  struct req {
    long etiqReq; // 1
    struct contenu {
      int idop;  // 1: +, 2 : -, 3: *, 4: /
      int op1;
      int op2;
    } contenu;
  } requete;

int main (int argc, char * argv[]){

  key_t cle = ftok(argv[3], 'z');
  if (cle == -1) {
    perror("erreur ftok");
    exit(1);
  }
  printf("ftok ok\n");

  // Identification
  int msgid;
  if ((msgid = msgget(cle, IPC_CREAT|0666)) < 0) {
    perror("msgget");
    exit(1);
  }
  printf("msgget ok\n");
  
  // Receive an answer of message type 1.
  while(1) {
    if (msgrcv(msgid, (void *)&requete, sizeof(requete.contenu), requete.etiqReq, 0) == -1) {
      perror("msgrcv");
      exit(1);
    }
    printf("%d %d\n", requete.contenu.op1, requete.contenu.op2);
    // printf("msgrcv ok\n");
  }
  
  // reçoit requete 
  // ssize_t result = msgrcv(msqid, (void *)&rbuf, rbuf.mtype, 0);

  // printf("%d\n", requete.contenu.op1);

  printf("end\n");
  return 0;
}
