# Lab 1 question 1

.globl welcome
.globl prompt
.globl sumText

.data

welcome:
	.asciiz " Fast Mod Program \n\n"

prompt:
	.asciiz " Enter an integer: "

sumText: 
	.asciiz " \n Mod = "
	
.text

main:
	#display welcome
	ori     $v0, $0, 4
	lui     $a0, 0x1001
	syscall
	
	#display first int prompt
	ori     $v0, $0, 4
	lui     $a0, 0x1001
	ori     $a0, $a0,0x16
	syscall
	
	ori     $v0, $0, 5
	syscall
	
	# t1 = num
	or		$t1, $0, $v0
	
	#display 2nd int prompt
	ori     $v0, $0, 4
	lui     $a0, 0x1001
	ori     $a0, $a0,0x16
	syscall
	
	ori     $v0, $0, 5
	syscall
	
	# t2 = div
	or		$t2, $0, $v0
	
	srl		$t3, $t2, 1	
	
	srl		$t4, $t1, $t3
	
	sll		$t5, $t4, $t3
	
	beq		$t1, $t5, end
	
	sub		$t6, $t1, $t4
	beq		$t2, $t6, end
	ori		$t6, $0, 0
end:	
	ori		$v0, $0, 1
	or 		$a0, $0, $t6
	syscall
	
	#exit
	ori     $v0, $0, 10
	syscall
	
	
	
	
	
	
	
	
	
	