#include "commun.h"

// lire le fichier commun.h pour la documentation et la trsucture utilisée


int jetonsEtape_init(JetonsEtape * v, int k){
  int res = 0;
  
  v->max=k;
  v->nbJetonPris=0;
  pthread_mutex_init(&v->mut,NULL);
  pthread_cond_init(&v->cond,NULL); 
  
  return res;
}


int demande_jeton(JetonsEtape * v){

  int res = 0;
  
 pthread_mutex_lock(&v->mut);
 
 while(v->nbJetonPris >= v->max)
 {
 	
 	pthread_cond_wait(&v->cond,&v->mut);
 }
 v->nbJetonPris++;
 pthread_mutex_unlock(&v->mut);
 
  return res;
}

int liberer_jeton(JetonsEtape * v){

  int res = 0;
  
pthread_mutex_lock(&v->mut);
v->nbJetonPris--;

if(v->max < v->nbJetonPris)
{
	pthread_cond_broadcast(&v->cond);
	
}
pthread_mutex_unlock(&v->mut);

  return res;
}

int jetonsEtape_destroy(JetonsEtape * v){

  int res = 0;
  
  v->max = 0;
  v->nbJetonPris = 0;
  pthread_mutex_destroy(&v->mut);
  pthread_cond_destroy(&v->cond);

 
  return res;
}


