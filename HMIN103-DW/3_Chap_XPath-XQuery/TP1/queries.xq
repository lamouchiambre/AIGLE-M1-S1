(: 1) XPath : Films :)

(: 1. Les titres des films. :)
(:
//FILM/TITRE
:)

(: 2. Les titres des films parus en 1990. :)
(:
//FILM[ANNEE=1990]/TITRE
:)

(: 3. Le résumé d’Alien. :)
(:
/FILMS/FILM[TITRE = "Alien"]/RESUME
:)

(: 4. Quel est le dernier film du document ? :)
(:
/FILMS/FILM[last()]
:)

(: 5. Quel est le dernier film du document paru en 1990 ? :)
(:
/FILMS/FILM[ANNEE = 1990][last()]
:)

(: 6. Les titres des films qui ont un résumé. :)
(:
/FILMS/FILM[RESUME != ""]
:)

(: 7. Les titres des films dont l’élément résumé n’est pas présent. :)
(:
/FILMS/FILM[not(RESUME)]
:)

(: 8. Donnez les noms des acteurs qui ont joué dans Vertigo. :)
(:
/FILMS/ARTISTE[@id = /FILMS/FILM[TITRE = "Vertigo"]/ROLES/ROLE/@idref]/ACTNOM
:)

(: 9. Qui a mis en scène Vertigo ? :)
(:
/FILMS/ARTISTE[@id = /FILMS/FILM[TITRE = "Vertigo"]/MES/@idref]/ACTNOM
:)

(: 10. Donnez tout les films du directeur de Vertigo. :)
(:
/FILMS/FILM[MES/@idref = /FILMS/FILM[TITRE = "Vertigo"]/MES/@idref]
:)

(: 11. Donnez les titres des films qui contiennent la lettre "V" (utiliser la fonction contains()). :)
(:
/FILMS/FILM[contains(TITRE,"V")]/TITRE
:)

(: 12. Les titres des films où l’acteur Bruce Willis a joué. :)
(:
/FILMS/FILM[/FILMS/ARTISTE[ACTNOM = "Willis" and ACTPNOM = "Bruce"]/@id = ROLES/ROLE/@idref]/TITRE
:)

(: 13. Quel rôle joue Harvey Keitel dans Reservoir dogs ? :)
(:
/FILMS/FILM[TITRE = "Reservoir dogs"]/ROLES/ROLE[@idref = /FILMS/ARTISTE[ACTNOM = "Keitel" and ACTPNOM = "Harvey"]/@id]
:)

(: 14. Qui a joué avec Harvey Keitel dans Reservoir dogs ? :)
(:
/FILMS/FILM[TITRE = "Reservoir dogs"]/ROLES/ROLE[not(@idref = /FILMS/ARTISTE[ACTNOM = "Keitel" and ACTPNOM = "Harvey"]/@id)]
:)

(: 15. Donnez les nœuds qui ont exactement trois descendants (utiliser la fonction count()). :)
(:
//*[count(descendant::*) = 3]
:)

(: 16. Donnez les nœuds dont le nom contient la chaîne "TI" (utiliser la fonction name()). :)
(:
//*[contains(name(), "TI")]
:)

(: 17. Quel est le titre du film qui précède immédiatement Shining (dans l’ordre du document) ? :)
(:
/descendant::FILM[TITRE = "Shining"]/following-sibling::FILM[position() = 1]/TITRE
:)

(: 2) XPath : Recettes :)

(: 1. Le nom complet de toutes les recettes :)
(:
/recettes/recette/@nom
:)

(: 2. Les ingrédients de la recette dont le nom court est "Chiffonnade" :)
(:
/recettes/recette[@nomCourt = "Chiffonnade"]/materiel/ingredient
:)

(: 3. Le nom complet des recettes utilisant du “persil” :)
(:
/recettes/recette[materiel/ingredient = "asperge"]/@nom
:)

(: 4. (Sans utiliser l’axe child) Le nom complet des recettes utilisant du “persil”. :)
(:
/descendant::recette[descendant::ingredient[contains(text(), "persil")]]/@nom
:)

(: 5. Le nom complet des recettes ayant plus de deux ingrédients, et contenant des oeufs. :)
(:
/descendant::recette[count(descendant::ingredient)>2 and descendant::ingredient="oeuf"]/@nom
:)

(: 6. (Sans utiliser la fonction count()) Le nom complet des recettes ayant plus de deux ingrédients, et contenant l'ingrédient "huile". :)
(:
/descendant::recette[descendant::ingredient/position() = 1 and descendant::ingredient/position() = 2 and descendant::ingredient/position() = 3 and descendant::ingredient[contains(text(), "huile")]]/@nom
:)

(: 7. La dernière recette du document. :)
(:
/descendant::recette[last()]
:)

(: 3) XPath : Trains :)

(: 1. Le numéro des trains qui possèdent une voiture-bar. :)
(:
/descendant::train[descendant::voiture/child::bar]/@numero
:)

(: 2. Le nom des usages ayant effectué au moins une réservation. :)
(:
/descendant::usager[@id = /descendant::resa/@id]/@nom
:)