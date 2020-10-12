(:return /descendant::node():)

(: 1. Indiquer le nombre de tweets et d’utilisateurs dans la base. :)
(:
let $t := count(/descendant::Tweet)
let $a := count(/descendant::Author)
return ($t, $a)
:)

(: 2. Donner l’ensemble des hashtags contenus dans la base. :)
(:
for $c in /descendant::Tweet/descendant::Content
return $c/Hashtag
:)

(: 3. Créer une liste de paires tweet-auteur, avec chaque paire contenue dans un element result. :)
(:
for $t in /descendant::Tweet
let $idrefa := $t/@idref_author
for $a in /descendant::Author
let $ida := $a/@id_author
where $idrefa = $ida
return 
<result>{($t, $a)}</result>
:)

(: 4. Pour chaque utilisateur, lister le nom de l’utilisateur et la date de tous ses tweets, le tout regroupé dans un élément result. :)
(:
for $a in /descendant::Author
let $a1 := $a/@id_author
let $a2 := $a/Name/text()
for $t in /descendant::Tweet
let $t1 := $t/@idref_author
where $a1 = $t1
return <result>{($a2, $t/@seconds/string(), $t/@timer_zone/string())}</result>
:)

(: 5. Lister les utilisateurs qui ont publié un tweet qui a été retwitté. :)
(:
for $a in /descendant::Author
where /descendant::Retweets > 0
return $a
:)

(: 6. Pour chaque tweet, indiquer la date de ses deux premières réponses. Rajouter un element vide <nonRetwitted/> s’il n’a pas été retwitté. :)
(:
for $t in /descendant::Tweet
let
:)

(: 7. Lister les utilisateurs de la plateforme en ordre alphabétique. :)
(:
for $a in /descendant::Author
order by $a/Name
return $a 
:)

(: 8. Lister les tweets contenants l’hashtag “#I<3XML”. :)
(:
for $t in /descendant::Tweet
where $t/Content/Hashtag/string() = "#I<3XML"
return $t
:)

(: 9. Trouvez le tweet le plus ancien ainsi que le plus recent. :)

