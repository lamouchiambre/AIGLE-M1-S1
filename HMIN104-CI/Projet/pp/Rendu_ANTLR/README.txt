Binômes : 
Andréa Pires
Alexandre Canton Condes

La gramaire AST fonctionne, le fichier .java compile également.
Lorsqu'on test notre programme avec notre exemple dans test2, nous avons un NullPointeur
Exception sur notre liste de Déclaration qui pourtant n'est pas vide.


commandes :
antlr4 langageAST3.g4
javac langageAST3*.java
java langageAST3 " ..."
