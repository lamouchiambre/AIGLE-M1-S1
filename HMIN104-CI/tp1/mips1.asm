# Demander la saisie d’un entier 
# et rendre la valeur absolue de cet entier (afficher le résultat) ;

.data
message:.asciiz "Rentrez un entier\n"

.text
main:	li $v0, 4
	la $a0, message
	syscall
	
	li $v0, 5
	syscall
	
	bge $v0, $t0, disp
	neg $v0, $v0
disp:	move $a0, $v0
	li $v0, 1
	syscall