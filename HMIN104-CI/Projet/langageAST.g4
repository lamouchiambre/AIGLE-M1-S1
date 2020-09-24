grammar langageAST;

prog : optdecvar optdecvar listedecfonc | listedecfonc;

optdecvar = listedecvar ',';

listedecvar = decvar listedecvarbis | decvar;

listedecvarbis = ',' decvar listedecvarbis| ',' decvar ;

decvar = identif affectationOpt | identif '[' integer ']' affectationTabOptInt | identif '[' integer ']' affectationTabOptBool ;

affectationOpt = '=' integer| '=' boolean | ; //rien car ca peut être optionnel

affectationTabOptInt = '= {' integer affectationTabOptBisInt '}' | integer ; 

affectationTabOptBisInt = ',' integer affectationTabOptBisInt| ',' integer;

affectationTabOptBool = '= {' integer affectationTabOptBisBool '}' | integer ; 

affectationTabOptBisBool = ',' integer affectationTabOptBisBool| ',' integer;

listedecfonc = decfonc listedecfonc | ; //ou rien car cest la liste finale

decfonc = identif listeparam optdecvar instrbloc | identif listeparam instrbloc ;

listeparam = '()' | '(' listedecvar ')' ;

instr =instraffect |
{instrbloc |
instrsi |
 instrtantque |
 instrlire |
 instrappel |
instrretour |
 instrecriture |
 instrskip |
 instrvide ;

instraffect = var '=' exp ';' ;

instrbloc = '{' listeinst '}' ;

listeinst = instr listeinst | ; //ou la liste finale

instrsi = 'if' exp 'then' instrbloc instrsinon ;

instrsinon = 'else' instrbloc ;

instrtantque = 'while' exp 'do' instrbloc ;

instrappel = appelfct ';' ;

instrecriture = 'write(' exp ');' ;

instrskip = 'skip';

instrvide = ';' ;

appelfct = identif '(' listeexp ')' ;

listeexp = expr listeexpbis|  ;

listeexpbis = ',' expr listeexpbis | ;

expr : exprBool | exprInt ;

exprBool : boolean | bopBool ;

expInt : integer | bopInt;

bopInt : bopInt '<' bopInt2 | bopInt '=' bopInt2 | bopInt '>' bopInt2 | bopInt2;

bopInt2 : bopInt2 '<=' bopInt3 | bopInt2 '=>' bopInt3 | bopInt3 ; 

bopInt3 : bopInt3 '+' bopInt4 | bopInt3 '-' bopInt4 | bopInt4 ;

bopInt4 :  bopInt4 '*' bopInt5 | bopInt4 '/' bopInt5 | bopInt5 ;  

bopInt5 :  '!' bopInt5  | bopInt6 ; 

bopInt6 : var | appelfct | integer | 'read(' exp ')' | '('exp ')';

bopBool : bopBool 'and' bopBool1 | bopBool 'or' bopBool1 | bopBool1;

bopBool1 : 'not' bopBool1 | bopBool2;

bopBool2 : boolean | appelfct | var | 'read(' exp ')'| bopBool;

var : identif '[' expInt ']' | identif;

array : 'array of' type | boolean | integer ;

type : boolean | integer | array ;

integer : ('0'..'9')+ ;

boolean : true | false ;

WS : [ \t\r\n]+ -> skip ;

identif = alpha alphanum*; //a changer pour que ce soit 
