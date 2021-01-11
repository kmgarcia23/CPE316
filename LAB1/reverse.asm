# Lab 1 question 2

.globl welcome
.globl prompt
.globl sumText

.data

welcome:
	.asciiz " Reverse Binary Program \n\n"

prompt:
	.asciiz " Enter an integer: "

sumText: 
	.asciiz " \n Reverse = "
	
.text

main:
	#display welcome
	ori     $v0, $0, 4
	lui     $a0, 0x1001
	syscall
	
	#display first int prompt
	ori     $v0, $0, 4
	lui     $a0, 0x1001
	ori     $a0, $a0,0x1C
	syscall
	
	ori     $v0, $0, 5
	syscall
	
	# t1 = input
	or		$t1, $0, $v0
	
	# set and
	ori		$t2, $0, 1
	
	#t3 = output
	
	or		$t3, $0, $0
	ori		$t5, $0, 0
	
loop:
	and		$t4, $t1, $t2
	add		$t3, $t3, $t4
	sll		$t3, $t3, 1
	srl		$t1, $t1, 1
	addi	$t5, $t5, 1
	beq		$t5, 31, loopend
	j		loop
	
loopend:
#print result
	ori		$v0, $0, 4
	lui     $a0, 0x1001
	ori     $a0, $a0,0x2F
	syscall

	ori		$v0, $0, 1
	or 		$a0, $0, $t3
	syscall
	

	
	#exit
	ori     $v0, $0, 10
	syscall