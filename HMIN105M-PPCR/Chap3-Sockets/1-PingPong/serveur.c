#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>

int main(int argc, char *argv[]) {
  if (argc < 2) {
    printf("utilisation: %s numero_port\n", argv[0]);
    exit(1);
  }
  
  /* Etape 1 : créer une socket (la socket qui permettra de recevoir
     les demandes de connexion.*/
  int ds = socket(PF_INET, SOCK_STREAM, 0);
  if (ds == -1) {
    perror("Serveur: probleme creation socket");
    exit(1); 
  }
  printf("Serveur: creation de la socket: ok\n");

  /* Etape 2 : nommer la socket. Elle aura une ou des IP de la machine
     sur laquelle le programme sera exécuté et le numéro de port passé
     en paramètre*/
  struct sockaddr_in server;
  server.sin_family = AF_INET;
  server.sin_addr.s_addr = INADDR_ANY;
  server.sin_port = htons(atoi(argv[1]));
  
  if (bind(ds, (struct sockaddr*) &server, sizeof(server)) < 0) {
    perror("Serveur: erreur bind");
    close(ds); 
    exit(1); 
  }
  printf("Serveur: nommage: ok\n");

  /* Etape 3 : mise en ecoute de la socket nommée. Cette opération
     dédie la socket à la réception des demandes de connexion. Pour
     cet exercice, limiter la file des demandes de connexions à 5. */
  int ecoute = listen(ds, 5);
  if (ecoute < 0) {
    printf("Serveur: je suis sourd(e)\n");
    close(ds);
    exit(1);
  } 
  printf("Serveur: mise en écoute: ok\n");

  /* etape 4 : attendre et traiter une demande de connexion d'un
     client. Rappel : lorsque le serveur accepte une demande de
     connexion, il crée une nouvelle socket. Cette dernière est
     connectée à celle du client et elle est à utiliser pour
     communiquer avec lui.*/
  printf("Serveur: j'attends la demande d'un client (accept) \n"); 
  
  struct sockaddr_in adCv;
  socklen_t lgCv = sizeof (struct sockaddr_in);

  int dsCv = accept(ds, (struct sockaddr*) &adCv, &lgCv);
  if (dsCv < 0) { 
    perror("Serveur, probleme accept");
    close(ds);
    exit(1);
  }
  printf("Serveur: le client %s:%d est connecté  \n", inet_ntoa(adCv.sin_addr), adCv.sin_port);

  /* Etape 5 : réception d'un message de type chaîne de caractères */
  char buffer[500];
  int rcv = recv (dsCv, buffer, sizeof(buffer), 0);
  if (rcv <= 0){
    printf("Serveur: je suis sourd(e)\n");
    close(rcv);
    exit(1);
  } 

  printf("Serveur: j'ai recu %d octets \n", rcv);
  printf("Serveur: contenu du message: %s \n", buffer);

  /* Etape 6 : répondre au client en lui envoyant le nombre d'octets
     effectivement reçus à l'étape 5. Pour cet exercice, faire un seul
     appel de la fonction send.*/ 
  int snd = send(dsCv, &rcv, sizeof(int), 0);
  if (snd <= 0) {
    printf("je suis la");
    close(snd);
    exit(1);
  }

  /* Etape 7 : fermeture de la socket du client */ 
  printf("Serveur: fin du dialogue avec le client\n");
  close(dsCv);
  
  // Etape 8 : pour cet exercice, je ne traite qu'un client, donc, je termine proprement.
  close(ds);
  printf("Serveur: je termine\n");
}