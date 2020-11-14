#include "commun.h"

// lire le fichier commun.h pour la documentation et la structure utilisée

int jetonsEtape_init(JetonsEtape* v, int k) {
  int res = 0;

  v->max = k;

  if (v->max != k) {
    res = -1;
  }
  
  return res;
}

int demande_jeton(JetonsEtape* v) {
  int res = 0;
  if (pthread_mutex_lock(&v->mut) != 0) {
    res = -1;
  };

  if (v->nbJetonPris < v->max) {
    v->nbJetonPris++;
  } else {
    if (pthread_cond_wait(&v->cond, &v->mut) != 0) {
      res = -1;
    }
  }

  if (pthread_mutex_unlock(&v->mut) != 0) {
    res = -1;
  }
  return res;
}

int liberer_jeton(JetonsEtape* v) {
  int res = 0;
  if (pthread_mutex_lock(&v->mut) != 0) {
    res = -1;
  };
  
  if (pthread_cond_signal(&v->cond) != 0) {
    res = -1;
  }

  if (pthread_mutex_unlock(&v->mut) != 0) {
    res = -1;
  }
  return res;
}

int jetonsEtape_destroy(JetonsEtape* v) {
  int res = 0;
  if (pthread_mutex_lock(&v->mut) != 0) {
    res = -1;
  };
  
  v->nbJetonPris--;
  
  if (pthread_mutex_unlock(&v->mut) != 0) {
    res = -1;
  }
  // free(v);
  return res;
}

// lancement : ./bin/jeu NombreJoueurs NombreEtapes NombeMaxJoueursEtape1 ... NombeMaxJoueursEtapeNombreEtapes 