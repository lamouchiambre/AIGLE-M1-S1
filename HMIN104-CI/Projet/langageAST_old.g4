grammar langageAST;

inst

prog

fonc

atomExpr : expr | '(' expr ')' ;


expr : exprBool | exprInt ;

exprBool : boolean | bopBool | 'not' exprBool ;

expInt : integet | bopInt | '-' exprInt ;

bopInt : exprInt '+' bopInt | exprInt '-' bopInt | bopInt2;

bopInt2 : exprInt '*' bopInt2 | expInt '/' bopInt2 | par ; 

par : 

bopBool : exprBool 'and' bopBool | exprBool 'or' bopBool | boolean ;

type : boolean | integer | array ;

const : Integer | boolean ; 

array : 'array of' type | boolean | integer ;

integer : ('0'..'9')+ ;

boolean : true | false ;

WS : [ \t\r\n]+ -> skip ;
