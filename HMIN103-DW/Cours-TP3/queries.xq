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