#include "commun.h"

// lire le fichier commun.h pour la documentation et la trsucture utilisée


//Toutes les fonctions pthread renvoient 0 en cas de succès et un code d'erreur non nul en cas de problème
//donc je peux utiliser leur valeur de retour (pour vérifier d'éventuel erreurs);  

//Possibilité de décommenter les printf si besoin pour visualiser le passage dans les fonctions

int jetonsEtape_init(JetonsEtape * v, int k){
  v->max = k;
  v->nbJetonPris = 0;
  pthread_cond_init(&v->cond, NULL);
  return pthread_mutex_init(&v->mut, NULL);

}


int demande_jeton(JetonsEtape * v){

  pthread_mutex_lock(&v->mut);

 // printf("Je vais demander un jeton, sinon j'attend qu'une place se libère \n");
  while(v->nbJetonPris >= v->max) {
  //	printf("J'attend là car %d/%d jetons sont pris \n", v->nbJetonPris, v->max);
  	pthread_cond_wait(&v->cond,&v->mut);
  }

 	v->nbJetonPris++;

  return pthread_mutex_unlock(&v->mut);
}

int liberer_jeton(JetonsEtape * v){

  pthread_mutex_lock(&v->mut);
  //Je signale que je libère un jeton
  v->nbJetonPris--;
  if(v->nbJetonPris < v->max) {

  	//printf("Je signal qu'il y a une place car %d/%d jetons sont pris \n", v->nbJetonPris, v->max);
  	pthread_cond_signal(&v->cond);
  }
  return pthread_mutex_unlock(&v->mut);
}

int jetonsEtape_destroy(JetonsEtape * v){
//printf("Tout est finis je m'occupe de faire le ménage des champs de la structure \n");
v->max =  0;
v->nbJetonPris = 0;
pthread_mutex_destroy(&v->mut);
  return pthread_cond_destroy(&v->cond);
}


