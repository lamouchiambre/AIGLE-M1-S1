#include "functions.h"

int main (int argc, char * argv[]) {

  assert(argc == 1);

  struct requete requete;
  struct result result;

  key_t cle = ftok("key.txt", 'z');
  if (cle == -1) {
    perror("error ftok");
    exit(1);
  } 
  printf("ftok ok\n");

  int msgid = msgget(cle, IPC_CREAT | 0666);
  if ((msgid) < 0) {
    perror("error msgget");
    exit(1);
  } 
  printf("msgget ok\n");
  
  while (1) { 
    if ((msgrcv(msgid, &requete, sizeof(requete), (long) '/', 0)) == -1) {
      perror("error msgrcv");
      exit(1);
    } 
    printf("msgrcv ok\n");

    result.contRes.nb = calculation(requete.cont.nb1, requete.cont.nb2, requete.cont.op);
    printf("Résultat: %f\n", result.contRes.nb);
  } 

  if (msgctl(msgid, IPC_RMID, NULL) != 0) {
    perror("error msgctl");
    exit(1);
  } 

  printf("end\n");
  return 0;
}