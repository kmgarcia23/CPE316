# Lab 1 question 3

.globl welcome
.globl prompt
.globl prompt2
.globl sumText
.globl sumText2

.data

welcome:
	.asciiz " 64Bit Divid Program \n\n"

prompt:
	.asciiz " Enter an integer: "
	
prompt2:
	.asciiz " Enter a divisor: "

sumText: 
	.asciiz " \n Answer Upper = "
	
sumText2: 
	.asciiz " \n Answer Lower = "
	

	
.text

main:
	#display welcome
	ori     $v0, $0, 4
	lui     $a0, 0x1001
	syscall
	
	#display first int prompt
	ori     $v0, $0, 4
	lui     $a0, 0x1001
	ori     $a0, $a0,0x18
	syscall
	
	ori     $v0, $0, 5
	syscall
	
	# t1 = upper32
	or		$t1, $0, $v0
	
	#display 2nd int prompt
	ori     $v0, $0, 4
	lui     $a0, 0x1001
	ori     $a0, $a0,0x18
	syscall
	
	ori     $v0, $0, 5
	syscall
	
	# t2 = lower32
	or		$t2, $0, $v0
	
	#display div int prompt
	ori     $v0, $0, 4
	lui     $a0, 0x1001
	ori     $a0, $a0,0x2C
	syscall
	
	ori     $v0, $0, 5
	syscall
	
	# t3 = div
	or		$t3, $0, $v0
	
	
	#set iterator $t5
	ori		$t5, $0, 1
	
loop:
	#grab transfer bit
	andi	$t4, $t1, 1
	#set to position in lower
	sll		$t4, $t4, 31
	#shift lower and upper
	srl		$t1, $t1, 1
	srl		$t2, $t2, 1
	#place bit
	or		$t2, $t2, $t4
	#rotate interator
	sll 	$t5, $t5, 1
	beq		$t5, $t3, loopend
	j		loop
	
loopend:
	#print result
	ori		$v0, $0, 4
	lui     $a0, 0x1001
	ori     $a0, $a0,0x3F
	syscall

	ori		$v0, $0, 1
	or 		$a0, $0, $t1
	syscall
	
	ori		$v0, $0, 4
	lui     $a0, 0x1001
	ori     $a0, $a0,0x52
	syscall
	
	ori		$v0, $0, 1
	or 		$a0, $0, $t2
	syscall
	
	
	#exit
	ori     $v0, $0, 10
	syscall