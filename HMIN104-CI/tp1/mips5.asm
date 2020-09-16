# Écrire une routine qui permute le contenu de deux variables entières
# de la zone de données avec une variable locale pour effectuer la permutation ;

.data
n1:	.word 0
n2: 	.word 0
msg:	.asciiz "Rentrez un entier\n"

.text
main:	li $v0, 4
	la $a0, msg
	syscall # "Rentrez un entier\n"
	
	li $v0, 5
	syscall
	la $a1, n1 # n1 dans a0
	
	li $v0, 4
	la $a0, msg
	syscall # "Rentrez un entier\n"
	
	li $v0, 5
	syscall
	la $a2, n2 # n2 dans a1
	j swap
#	li $v0, 1
#	la $a0, n1
#	syscall
	
swap: 	sub $sp, $sp, 4
	lw $t0, ($a1)
	sw $t0, ($sp)
	lw $t0, ($a2)
	sw $t0, ($a1)
	lw $t0, ($sp)
	sw $t0, ($a2)
	add $sp, $sp, 4
	jr $ra
	