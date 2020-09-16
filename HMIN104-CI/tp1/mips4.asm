# Demander la saisie d’un entier 
# et dire si cet entier est pair ou non (afficher le résultat).

.data
msg:	.asciiz "Rentrez un entier\n"
msgp: 	.asciiz "pair\n"
msgi: 	.asciiz "impair\n"

.text
main:	li $v0, 4
	la $a0, msg
	syscall # affichage du msg
	
	li $v0, 5
	syscall
	move $t0, $v0 # sauvegarde de n
	
	li $t1 2
	div $t0, $t1
	mfhi $t2
	beqz $t2, pair
	j impair
	
pair: 	li $v0, 4
	la $a0, msgp
	syscall
	j end
	
impair: li $v0, 4
	la $a0, msgi
	syscall
	j end
end: