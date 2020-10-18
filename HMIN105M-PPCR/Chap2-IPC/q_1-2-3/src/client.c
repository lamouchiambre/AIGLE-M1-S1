#include "functions.h"
void calculation_init(float *a, float *b, char *c);

int main (int argc, char * argv[]){

  assert(argc == 3); // ./client nb1 nb2

  struct requete requete;
  struct result result;
  requete.etiq = 1;
  requete.cont.op = '+';
  requete.cont.nb1 = atoi(argv[1]);
  requete.cont.nb2 = atoi(argv[2]);
  requete.clientPID = getpid();

  key_t cle = ftok("../bin/key.txt", 'z');
  assert(cle != -1);
  printf("ftok ok\n");

  int msgid = msgget(cle, 0666 | IPC_CREAT);
  assert(msgid != -1);
  printf("msgget ok\n");
  
  printf("calculation_ini\nt");
  calculation_init(&requete.cont.nb1, &requete.cont.nb2, &requete.cont.op);

  assert(msgsnd(msgid, &requete, sizeof(requete), 0) != -1);
  printf("msgsnd ok. msgid: %i\n", msgid);
  
  assert(msgrcv(msgid, &result, sizeof(result), getpid(), 0) != -1);
  printf(MAG "   [INFO] * Result received from the calculator : %f * %s\n\n", result.contRes.nb, " ");

  assert(msgctl(msgid, IPC_RMID, NULL) != 0);

  printf("end\n");
  return 0;
}