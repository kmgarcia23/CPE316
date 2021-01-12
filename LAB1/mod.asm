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
	ori     $a0, $a0,0x15
	syscall
	
	ori     $v0, $0, 5
	syscall
	
	# t1 = num
	or		$t1, $0, $v0
	
	#display 2nd int prompt
	ori     $v0, $0, 4
	lui     $a0, 0x1001
	ori     $a0, $a0,0x15
	syscall
	
	ori     $v0, $0, 5
	syscall
	
	# t2 = div
	or		$t2, $0, $v0
	# set iterator
	ori		$t0, $0, 1
	
	# set count store t3
	ori 	$t3, $0, 0
	#copy num into t4
	or		$t4, $0, $t1
	
loop:
	#shift num by 1
	srl		$t4, $t4, 1
	#shift iterator
	sll		$t0, $t0, 1
	#increment count
	addi	$t3, $t3, 1
	#check div against iterator
	beq		$t0, $t2, loopend
	j		loop
	
loopend:
	#shift num by count to create rounded down 
	sll		$t5, $t4, $t3
	#sub original from rounded down
	sub		$t6, $t1, $t5
	# check if mod = div
	bne		$t2, $t6, end
	#if true set mod to 0
	ori		$t6, $0, 0
end:	

	#display answer
	ori     $v0, $0, 4
	lui     $a0, 0x1001
	ori     $a0, $a0,0x29
	syscall
	
	ori		$v0, $0, 1
	or 		$a0, $0, $t6
	syscall
	
	
	#exit
	ori     $v0, $0, 10
	syscall
	
	
	
	
	
	
	
	
	
	