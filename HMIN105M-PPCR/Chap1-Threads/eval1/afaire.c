#include "commun.h"

// lire le fichier commun.h pour la documentation et la trsucture utilisée


// initialisation des champs de la structure *v. k est le nombre
// maximum de jetons rattachés à cette structure.  Cette fonction
// renvoie 0 en cas de succès sinon -1. Elle est appelée par le thread
// principal avant la création des threads joueurs.

int jetonsEtape_init(JetonsEtape * v, int k){
  int res = 0;
  
  v->max = k;
  v->nbJetonPris = 0;
  pthread_cond_init(&v->cond,NULL);
  res = pthread_mutex_init(&v->mut,NULL);
  return res;
}


// s'il reste des jetons rattachés à *v, la fonction donne un jeton à
// l'appelant, sinon attend la libération de jeton. Cette fonction
// renvoie 0 en cas de succès sinon -1. Elle est appelée par un thread
// joueur avant de commencer chaque étape du jeu.

int demande_jeton(JetonsEtape * v){

  int res = 0;
  
  pthread_mutex_lock(&v->mut);

  while(v->nbJetonPris >= v->max){
  	pthread_cond_wait(&v->cond,&v->mut);
  }

  v->nbJetonPris++;

  res = pthread_mutex_unlock(&v->mut);

  return res;
}

int liberer_jeton(JetonsEtape * v){

 int res = 0;
  
 pthread_mutex_lock(&v->mut);

 v->nbJetonPris--;

  if (v->nbJetonPris < v->max)
  {
    pthread_cond_broadcast(&v->cond);
  }
  
  res = pthread_mutex_unlock(&v->mut);

  return res;
}

int jetonsEtape_destroy(JetonsEtape * v){

  int res = 0;
  v->nbJetonPris = 0;
  v->max = 0;
  res = pthread_cond_destroy(&v->cond);
 
  return res;
}


