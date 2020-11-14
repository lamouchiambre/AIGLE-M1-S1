grammar langageAST2;

prog : listeDecVar instr| instr|  listeDecVar decFonc instr | listeDecFonc instr ;

listeDecVar : decVar listeDecVar | decVar;

listeDecFonc : decFonc listeDecFonc| decFonc ; 

decVar : 'var' Identif ':' type | WS ;

decFonc : Identif '(' listDecPar ')' ':' type instr | Identif '()' ':' type instr |
Identif '(' listDecPar ')' ':' type listeDecVar instr | Identif '()' ':' type listeDecVar instr;

listDecPar : Identif ':' type | listDecPar ',' listDecPar | expr  ; 

instr : instraffect |
  instrtantque |
  instrappel |
  instrecriture |
  'skip'|
  instrsi |
  instr ';' instr | 
  WS ;

instraffect : Identif ':=' expr ;

instrsi : 'if' expr 'then' instr instrsinon ;

instrsinon : 'else' instr ;

instrtantque : 'while' expr 'do' instr ;

instrappel : appelfct ; // elle merde

instrecriture : 'write(' expr ')' ;

appelfct : Identif '(' expr ')' ;

expr : exprBool | exprInt ;

exprBool : bool | bopBool ;

exprInt : Integer | bopInt;

bopInt : bopInt '<' bopInt2 | bopInt '==' bopInt2 | bopInt '>' bopInt2 | bopInt2;

bopInt2 : bopInt2 '<=' bopInt3 | bopInt2 '=>' bopInt3 | bopInt3 ; 

bopInt3 : bopInt3 '+' bopInt4 | bopInt3 '-' bopInt4 | bopInt4 ;

bopInt4 :  bopInt4 '*' bopInt5 | bopInt4 '/' bopInt5 | bopInt5 ;  

bopInt5 :  '!' bopInt5  | bopInt6 ; 

bopInt6 : Identif | appelfct | Integer | 'read()' | '('expr ')';

bopBool : bopBool 'and' bopBool1 | bopBool 'or' bopBool1 | bopBool1;

bopBool1 : 'not' bopBool1 | bopBool2;

bopBool2 : bool | appelfct | Identif | 'read()';

array : 'array of' type | bool | Integer ;

type : 'bool' | 'integer' | 'array' ;

Integer : ('0'..'9')+ ;

bool : 'true' | 'false' ;

WS : [ \t\r\n]+ -> skip ;

Identif : [a-z]+; //a changer pour que ce soit 