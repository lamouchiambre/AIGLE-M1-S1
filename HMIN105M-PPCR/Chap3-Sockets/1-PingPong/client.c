#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>

int main(int argc, char *argv[]) {
  if (argc != 3){
    printf("utilisation: %s ip_serveur port_serveur\n", argv[0]);
    exit(0);
  }
  
  /* Etape 1 : créer une socket */   
  int ds = socket(PF_INET, SOCK_STREAM, 0);
  if (ds == -1){
    printf("Client: pb creation socket\n");
    exit(1);
  }
  printf("Client: creation de la socket: ok\n");

  /* Etape 2 : designer la socket du serveur : avoir une structure qui
     contient l'adresse de cette socket (IP + numéro de port. */
  struct sockaddr_in adrServ;
  adrServ.sin_addr.s_addr = INADDR_ANY ;
  adrServ.sin_family = AF_INET;
  adrServ.sin_port = htons(atoi(argv[2]));
  
  int lgAdr = sizeof(struct sockaddr_in);
  
  /* Etape 3 : envoyer une demande de connexion au serveur.*/
  int conn = connect(ds, (struct sockaddr*) &adrServ, lgAdr);
  if (conn < 0) {
    perror("Client: pb au connect :");
    close(ds);
    exit(1); 
  }
  printf("Client: demande de connexion reussie \n");
 
  /* Etape 4 : envoyer un message au serveur. Ce message est une chaîne de caractères saisie au clavier. Vous pouvez utiliser une autre fonction pour la saisie. */
  printf("Saisir un message à envoyer (moins de 200 caracteres) \n");
  char m[202]; 
  fgets(m, sizeof(m), stdin);
  m[strlen(m)-1]  = '\0'; // je retire le saut de ligne                   
  
  int snd = send(ds, m, sizeof(m), 0);
  if (snd == -1) {
    perror("Client: error send");
    close(ds);
    exit(1);
  }
  printf("Client: j'ai déposé %d octets \n", snd);
    
  /* Etape 5 : reception d'une réponse du serveur. La réponse est un
    entier représentant le nombre d'octets du message que le serveur
    a effectivement reçu à l'étape précédente. Pour cet exercice,
    faire un seul appel de la fonction recv(..). */
  printf("Client: envoi fait, j'attends la reponse du serveur \n");
    
  int reponse[200];
  int rcv = recv (ds, reponse, sizeof(reponse), 0);
  if (rcv <= 0) {
	  perror("Erreur");
	  close(ds);
	  exit(1);
  }
    
  /* Etape 6 : je compare le nombre d'octets déposés (envoyés) avec
    la valeur reçue. L'objectif est d'avoir la même valeur. */ 
  printf("Client: j'ai envoyé %d octets et le serveur me répond qu'il a reçu : %d octets \n", snd, rcv) ;
  
  /* Etape 7 : je termine proprement. */
  close(ds);
  printf("Client: je termine\n");
}