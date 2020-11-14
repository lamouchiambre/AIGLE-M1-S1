/* DROP TABLE */

DROP TABLE Batiment;
DROP TABLE Batiment_etage;
DROP TABLE Batiment_etage_description;
DROP TABLE Batiment_etage_bureau;
DROP TABLE Batiment_etage_bureau_code;
DROP TABLE Batiment_etage_bureau_personne;
DROP TABLE Batiment_etage_salle;
DROP TABLE Batiment_etage_salle_nombrePlaces;

/* CREATE TABLE */

CREATE TABLE Batiment (
	id INTEGER PRIMARY KEY,
  txt VARCHAR(100),
	num INTEGER
);

CREATE TABLE Batiment_etage (
	id INTEGER PRIMARY KEY,
  txt VARCHAR(100),
	num INTEGER
);

CREATE TABLE Batiment_etage_description (
	id INTEGER PRIMARY KEY,
  txt VARCHAR(100),
	num INTEGER
);

CREATE TABLE Batiment_etage_bureau (
	id INTEGER PRIMARY KEY,
  txt VARCHAR(100),
	num INTEGER
);

CREATE TABLE Batiment_etage_bureau_code (
	id INTEGER PRIMARY KEY,
  txt VARCHAR(100),
	num INTEGER
);

CREATE TABLE Batiment_etage_bureau_personne (
	id INTEGER PRIMARY KEY,
  txt VARCHAR(100),
	num INTEGER
);

CREATE TABLE Batiment_etage_salle (
	id INTEGER PRIMARY KEY,
  txt VARCHAR(100),
	num INTEGER
);

CREATE TABLE Batiment_etage_salle_nombrePlaces (
	id INTEGER PRIMARY KEY,
  txt VARCHAR(100),
	num INTEGER
);