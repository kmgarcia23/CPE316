# Kevin Garcia & Peter Phillips
# CPE 315-01
# Lab 1 question 2
#
# This program takes in a 32-bit number and returns the same number with all bits reversed.
# This is accomplished by taking the last bit off the input and adding it to the output,
# then shifting the input 1 bit to the right and the output 1 bit to the left. This is then
# repeated 31 more times to place all bits in reverse order.
#
#Java Code
#public void reverseBit(){
#
#    System.out.println(" Reverse Binary Program \n\n");
#    System.out.println(" Enter an integer: ");
#    Scanner input = new Scanner(System.in);
#    int num = input.nextInt();
#    int output = 0;
#    int count = 0;
#    int andStore;
#    while(count != 31){
#        andStore = num & 1;
#        output = output + andStore;
#        output = output << 1;
#        num = num >> 1;
#        count ++;
#    }
#
#    System.out.println(" \n Reverse =  " + output);
#}

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
	ori     $a0, $a0,0x1B
	syscall
	
	ori     $v0, $0, 5
	syscall
	
	# t1 = input
	or		$t1, $0, $v0
	
	
	#t3 = output
	
	or		$t3, $0, $0
	ori		$t5, $0, 0
	
loop:
	#pull last bit of input
	andi	$t4, $t1, 1
	#add to output
	add		$t3, $t3, $t4
	#shift input and output
	sll		$t3, $t3, 1
	srl		$t1, $t1, 1
	#increment counter
	addi	$t5, $t5, 1
	#check if ready to break loop
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