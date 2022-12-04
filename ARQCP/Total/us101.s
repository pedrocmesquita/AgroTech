.section .data
	
	#uint32_t - unsigned int (long)
	#uint64_t - unsigned long (quadword)
	
	.global state
	state:
		.long 0
		
	.global inc
	inc:
		.long 0
		
	#folder:
	#	.string "/dev/random"	#this is the best way i found of doing strings
	#	
	#r:
	#	.string "r"
	
		
.section .text

	#Pretende-se que seja implementada em Assembly uma função que gere números
	#pseudo-aleatórios, a ser usada na simulação dos sensores. É disponibilizada em C a função
	#pcg32_ramdom_r() e pretende-se que seja desenvolvida em Assembly uma função equivalente. É
	#também disponibilizado um exemplo de como ler de /dev/random para inicializar o gerador
	#indicado anteriormente.
	
	#uint64_t state=0;  
	#uint64_t inc=0;
	#uint32_t pcg32_random_r()
	#{
	#    uint64_t oldstate = state;
	#    // Advance internal state
	#    state = oldstate * 6364136223846793005ULL + (inc|1);
	#    // Calculate output function (XSH RR), uses old state for max ILP
	#    uint32_t xorshifted = ((oldstate >> 18u) ^ oldstate) >> 27u;
	#    uint32_t rot = oldstate >> 59u;
	#    return (xorshifted >> rot) | (xorshifted << ((-rot) & 31));
	#}
	
	.global pcg32_random_r #uint32_t pcg32_random_r()
	pcg32_random_r:
	
		#prologue
		pushq %rbp
		movq %rsp, %rbp
		subq $16, %rsp			#allocate 16 bytes for 1 long and 2 ints
		
		#function body
		mov state(%rip), %rax
		mov %rax, -8(%rbp)		#oldstate = state
		
		mov inc(%rip), %rcx
		or $1, %rcx				#(inc|1)
		
		mov $6364136223846793005, %rdx	#ULL is a suffix for unsigned long long
		mul %rdx				#oldstate * 6364136223846793005ULL (dont use imul because they're unsigned variables)
		add %rax, %rcx			#oldstate * 6364136223846793005ULL + (inc|1)
		lea state(%rip), %r10
		mov %rcx, (%r10)		#state = oldstate * 6364136223846793005ULL + (inc|1);
		
		mov %rax, %r8
		shr $18, %r8			#oldstate >> 18u
		xor %rax, %r8			#(oldstate >> 18u) ^ oldstate
		shr $27, %r8
		mov %r8, -12(%rbp)		#xorshifted = ((oldstate >> 18u) ^ oldstate) >> 27u
		
		shr $59, %rax
		mov %rax, -16(%rbp)		#rot = oldstate >> 59u
		
		neg %rax				#-rot
		and $31, %rax			#(-rot) & 31
		mov %r8, %r9
		mov %rax, %rcx			#sal, sar, etc only accept cl as a register
		sal %cl, %r9			#xorshifted << ((-rot) & 31)
		
		shr %cl, %r8			#xorshifted >> rot
		
		or %r9, %r8				#(xorshifted >> rot) | (xorshifted << ((-rot) & 31))
		mov %r8, %rax
		
		#epilogue
		movq %rbp, %rsp
		popq %rbp
		ret
		
		
	#Função que inicializa os valores de state e inc usando valores de /dev/random
	#Não usar, em vez disso existe uma versão implementada em c
	#int main()
	#{
	#	uint32_t buffer[64];
	#	FILE *f;
	#	int result;
	#	int i;
	#	f = fopen("/dev/random", "r");
	#
	#	if (f == NULL)
	#	{
	#		printf("Error: open() failed to open /dev/random for reading\n"); 
	#		return 1;
	#   }
	#
	#   result = fread(buffer , sizeof(uint32_t), 64,f);
	#
	#   if (result < 1)
	#	{
	#		printf("error , failed to read and words\n"); 
	#		return 1;
	#   }
	#
	#   printf("Read %d words from /dev/random\n",result);
	# 
	#   for(i=0;i<result;i++)
	#	{
	#		printf("%08x\n",buffer[i]); 
	#	}   
	#	return 0;
	#}
	
	#.global initialize #char initialize()
	#initialize:
	#	#prologue
	#	pushq %rbp
	#	movq %rsp, %rbp
	#	subq $16, %rsp		#allocate 16 bytes for 1 pointer and an array of 2 ints
	#	
	#	#function body
	#	lea folder(%rip), %rdi	#will pass to function a pointer to the string that contains the folder
	#	lea r(%rip), %rsi		#pointer to reading mode (r for read)
	#	#mov $114, %sil			#UNUSED, FUNCTION IS EXPECTING POINTER, NOT CHAR!!
	#	push %rdi
	#	push %rsi
	#	call fopen				#fopen() returns a pointer to the file "/dev/random"
	#	pop %rsi
	#	pop %rdi
	#	mov %rax, -8(%rbp)		#save file pointer in a local variable
	#	
	#	cmp $0, %rax			#compare file pointer to null
	#	je ret1
	#	
	#	lea -16(%rbp), %rdi		#set the array as 1st argument
	#	mov $4, %esi			#size of int in bytes
	#	mov $2, %edx			#number of values to read
	#	mov -8(%rbp), %rcx		#pointer to file
	#	call fread				#fread() returns a size_t, which is stored like an unsigned int (4 bytes)
	#	
	#	cmp $1, %eax			#check to see if atleast 1 value was read
	#	jle ret1
	#	
	#	lea state(%rip), %r8
	#	lea inc(%rip), %r9
	#	
	#	mov $0, %rcx			#not necessary
	#	mov $0, %rdx
	#	movl -12(%rbp), %ecx	#no movzlq instruction, but since its an unsigned int so we can just zero the registers first
	#	movl -16(%rbp), %edx
	#	movq %rcx, (%r8)		#set state to first int of the array
	#	movq %rdx, (%r9)		#set inc to second int of the array
	#	
	#	#movq $0, (%r8)		#testing for how it should behave if 2 zeroes were read from dev/random
	#	#movq $0, (%r9)
	#	
	#	#epilogue
	#	movq %rbp, %rsp
	#	popq %rbp
	#	mov $0, %rax
	#	ret
	#	
	#jump to this if any error encountered while opening file
	#ret1:
	#	mov $1, %rax
	#	ret
