#include "functions.h"

int main (int argc, char * argv[]){

  assert(argc == 4); // ./client + nb1 nb2

  struct requete requete;
  struct result result;
  requete.etiq = (long) *argv[1];
  requete.cont.op = *argv[1];
  requete.cont.nb1 = atoi(argv[2]);
  requete.cont.nb2 = atoi(argv[3]);

  key_t cle = ftok("key.txt", 'z');
  if (cle == -1) {
    perror("error ftok\n");
    exit(1);
  } 
  printf("ftok ok\n");

  int msgid = msgget(cle, IPC_CREAT | 0666);
  if (msgid == -1) { 
    perror("error msgid");
    exit(1);
  } 
  printf("msgget ok\n");
  
  // printf("calculation_init\n");
  // calculation_init(&requete.cont.nb1, &requete.cont.nb2, &requete.cont.op);

  if ((msgsnd(msgid, &requete, sizeof(requete.cont), 0)) == -1) {
    perror("error msgsnd");
    exit(1);
  } 
  printf("msgsnd ok.\n");
  
  // if (msgrcv(msgid, &result, sizeof(result.contRes), requete.etiq, 0) == -1) {
  //   perror("error msgrcv");
  //   exit(1);
  // } 
  // printf("msgrcv ok.\n");
  // printf("Result received from the calculator : %f\n", result.contRes.nb);



  printf("end\n");
  return 0;
}