#include "commun.h"

// lire le fichier commun.h pour la documentation et la trsucture utilisée


int jetonsEtape_init(JetonsEtape * v, int k){
  int res = 0;
  v->max = k;  //On affécte le max à k
  v->nbJetonPris = 0; // et le nombre de jeton pris à 0

  pthread_mutex_t verrou = PTHREAD_MUTEX_INITIALIZER;
  pthread_cond_t condi = PTHREAD_COND_INITIALIZER;
  v->mut = verrou; //on innitialise le verrou
  v->cond = condi; //et la condition
  
  return res;
}


int demande_jeton(JetonsEtape * v){
  int res = 0;
  
  while((v->nbJetonPris)>=(v->max)){ //si il n'y a pls de jeton disponible
    pthread_cond_wait(&(v->cond),&(v->mut)); // on attend sur le verrou
  }
    (v->nbJetonPris)++;//sinon on en donne un
    //pthread_mutex_lock(&(v->mut)); //et on le verouille

  
  
  return res;
}

int liberer_jeton(JetonsEtape * v){
  
  int res = 0;
  (v->nbJetonPris)--;//on récupère le jeton
  pthread_mutex_unlock(&(v->mut));//on le debloque
  pthread_cond_broadcast(&(v->cond));//on le signale
  return res;
}

int jetonsEtape_destroy(JetonsEtape * v){

  int res = 0;
  //free(v);
 
  return res;
}


