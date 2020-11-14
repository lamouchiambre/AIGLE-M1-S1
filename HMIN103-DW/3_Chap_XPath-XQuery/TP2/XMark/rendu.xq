(: 1. All the items :)
(:
for $x in /descendant-or-self::node()
return $x
:)

(: 2. The keywords in annotations of closed auctions :)
(:
for $x in /descendant::closed_auction/annotation/description/text
return $x/keyword
:)

(: 3. All the keywords :)
(:
for $x in /descendant::site
return $x/descendant::keyword
:)

(: 4. The keywords in a paragraph item :)
(:
for $x in /descendant::item/descendant::text
return $x/descendant::keyword
:)

(: 5. The (either North or South) American items :)
(:
let $x := /descendant::namerica
let $y := /descendant::samerica
return ($x/item, $y/item)
:)

(: 6. The paragraph items containing a keyword :)
(:
for $x in /descendant::item
where $x/descendant::keyword
return $x/descendant::text
:)

(: 7. The mails containing a keyword :)
(:
for $x in /descendant::mail
where $x/descendant::keyword
return $x
:)

(: 8. The open auctions in which a certain person issued a bid before another :)
(:
for $x in /descendant::open_auction
for $y in /descendant::bider/date
let $z := /descendant::bider/date
where 
return $x
:)