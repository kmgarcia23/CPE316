# Kevin Garcia & Peter Phillips
# CPE 315-01
# Lab 1 question 3
#
# This program takes in two 32-bit numbers representing the upper and lower 32-bits of a 64-bit number and
# a 32-bit divisor that is a power of 2 and divides the 64-bit by the divisor. This acommplished by taking the
# last bit of the upper 32-bits, shifting that bit 31 places to the left, then shifting both upper and lower 32-bits 
# right by one, dividing the whole number by 2 then oring in the stored upper bit. The loop is controlled by a 1 that is
# shift one bit to the left every iteration, once the itorator is equal to the divisor the loop breaks. 
#
#    public void reverseBit(){
#
#        System.out.println(" 64Bit Divid Program \n\n");
#        System.out.println(" Enter an integer: ");
#        Scanner input = new Scanner(System.in);
#        int upper32 = input.nextInt();
#        System.out.println(" Enter an integer: ");
#        int lower32 = input.nextInt();
#        System.out.println(" Enter a divisor: ");
#        int div = input.nextInt();
#        int count = 1;
#        int andStore = 0;
#        while(count != div){
#            andStore = upper32 & 1;
#            andStore = andStore << 31;
#            upper32 = upper32 >> 1;
#            lower32 = lower32 >> 1;
#            lower32 = lower32 | andStore;
#            count = count << 1;
#        }
#
#        System.out.println(" \n Answer Upper = " + upper32);
#        System.out.println(" \n Answer Lpper = " + lower32);
#
#    }
#
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