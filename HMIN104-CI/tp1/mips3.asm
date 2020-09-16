# Afficher les n premiers entiers (en partant de 1), 
# où l’entier n sera demandé à l’utilisateur ;

.data
msg:	.asciiz "Rentrez un entier\n"

.text
main:	li $v0, 4
	la $a0, msg
	syscall # affichage du msg
	
	li $v0, 5
	syscall # entrée de n
	
	move $t0, $v0 # n est dans $t0
	li $t1, 0
	
while:	beq $t1, $t0, exit # si 1 < n
	addi $t1, $t1, 1
	move $a0, $t1
	li $v0, 1
	syscall
	j while
exit:	
	