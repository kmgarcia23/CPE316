#Kevin Garcia & Peter Phillips
#CPE 315-01

#This program returns the exponent of two numbers. It asks for the base
#and then it asks for the power. The program consists of two main loops.
#One of the loops repeatedly adds for the multiplication and the other
#loop repeatedly calls the multiplication loop inorder to find the exponent.

#Java Code
#public void exponent()
#{
#	int base, power, exp_count, mult_count, origial_base, result;
#	System.out.println("This program returns the power of two numbers \n\n");
#	System.out.println("Enter a base (Must be a positive number): \n");
#	Scanner input = newScanner(System.in);
#	base = input.nextInt();
#	System.out.println("Enter a power (Must be a positive number): \n");
#	power = input.nextInt();
#	exp_count = power;
#	mult_count = base;
#	original_base = base;
#	if(power == 1)
#	{
#		System.out.println(" \n Result = " + base);
#	}
#	else
#	{
#		exp_count -=2;
#		for(int i = mult_count, i > 0, i--)
#				total += base;
#		for(int j = exp_count, j > 0, j--)
#		{
#			base = total;
#			total = 0;
#			for(int k = mult_count, k > 0, k--)
#				total += base;
#		}
#		System.out.println(" \n Result = + total);
#	}
#}


# declare global so programmer can see actual addresses.
.globl welcome
.globl prompt
.globl sumText

.data

welcome:
	.asciiz " This program returns the power of two numbers \n\n"

base:
	.asciiz " Enter a base (Must be a positive number): "

power:
	.asciiz " Enter a power (Must be a positive number) : "

result: 
	.asciiz " \n Result = "

.text

main:

	#Prints the welcome message
	ori     $v0, $0, 4			
	lui     $a0, 0x1001
	syscall
	
	#Prints the base prompt
	ori     $v0, $0, 4			
	lui     $a0, 0x1001
	ori     $a0, 0x32
	syscall
	
	#Stores base into register t1
	ori     $v0, $0, 5
	syscall
	ori $t1, $v0, 0
	
	#Prints the power prompt
	ori     $v0, $0, 4			
	lui     $a0, 0x1001
	ori     $a0, 0x5e
	syscall
	
	#Stores power into register t2
	ori     $v0, $0, 5
	syscall
	ori $t2, $v0, 0
	
	#Prints the result
	ori     $v0, $0, 4			
	lui     $a0, 0x1001
	ori     $a0, 0x8c
	syscall
	
	ori $t7, $0, 1
	ori $t3, $t2, 0 #Counter for the exponent
	ori $t4, $t1, 0 #Conunter for the multiplication
	ori $t5, $t1, 0 #Storage for origial base
	beq $t2, $t7, printBase
	
	addi $t3, $t3, -2 #Decrements counter by two for initial multiplication
	
	multiply: add $t0, $t0, $t1
	addi $t4, $t4, -1 #Decrements the multiplication counter
	bne $t4, $0, multiply #Checks if multiplication is done
	
	beq $t3, $0, printResult #Checks if the exponent is done
	addi $t3, $t3, -1 #Decrements the exponent
	ori $t1, $t0, 0 #Saves the new base into t1
	andi $t0, $0, 0 #Resets the total
	ori $t4, $t5, 0 #Updates the counter for multiplication
	j multiply	
		
	printBase: ori $a0, $t1, 0
	ori $v0, $0, 1
	syscall
	j end
	
	printResult: ori $a0, $t0, 0
	ori $v0, $0, 1
	syscall
	
	#Exit (load 10 into $v0)
	end: ori     $v0, $0, 10
	syscall