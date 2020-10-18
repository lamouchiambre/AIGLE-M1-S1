#include "functions.h"

int main (int argc, char * argv[]){

  assert(argc != 1);

  struct requete requete;
  struct result result;

  key_t cle = ftok("../bin/key.txt", 'z');
  assert(cle != -1);
  printf("ftok ok\n");

  int msgid = msgget(cle, IPC_CREAT|0666);
  assert((msgid) >= 0);
  printf("msgget ok\n");

  while (1) {
    assert(msgrcv(msgid, &requete, sizeof(requete), getpid(), 0) != -1);
    printf("msgrcv ok\n");

    result.etiq = requete.clientPID;
    result.contRes.nb = calculation(requete.cont.nb1, requete.cont.nb2, requete.cont.op);

    assert(msgsnd(msgid, &result, sizeof(result), 0) != -1);
    printf("msgsnd ok\n");
  } 

  

  printf("end\n");
  return 0;
}
