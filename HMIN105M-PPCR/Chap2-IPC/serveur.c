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

  // struct res {
  //   long etiqReq;
  //   struct contenuRes {
  //     int result;
  //   } contenuRes;
  // } res;



int main (int argc, char * argv[]){

  if (argc != 2) {
    printf("lancement : ./client chemin_fichier_cle\n");
    exit(1);
  }

  key_t cle = ftok(argv[1], 'z');
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

  
  // while (1) {
    // if (msgrcv(msgid, (void*) &requete, (size_t) sizeof(requete.contenu), requete.etiqReq, 0) == -1) {
    //   perror("msgrcv");
    //   exit(1);
    // }
    // printf("%d %d\n", requete.contenu.op1, requete.contenu.op2);

    // res.contenuRes.result = requete.contenu.op1 + requete.contenu.op2;
    // res.etiqReq = 2;

    // send
  requete.etiqReq = 1;
  requete.contenu.op1 = 1;
  requete.contenu.op2 = 2;
  requete.contenu.idop = 1;

  if (msgsnd(msgid, (void*) &requete, sizeof(requete.contenu), 0) == -1) {
    perror("erreur send");
    exit(1);
  }

    // printf("%li\n", sizeof(res.contenuRes));
    // printf("%i\n", msgid);
    // if (msgsnd(msgid, &res, (size_t) sizeof(res.contenuRes), 0) == -1) {
    //   perror("erreur send");
    //   exit(1);
    // }
  // }

  printf("%d %d\n", requete.contenu.op1, requete.contenu.op2);
  printf("msgrcv ok\n");

  printf("end\n");
  return 0;
}
