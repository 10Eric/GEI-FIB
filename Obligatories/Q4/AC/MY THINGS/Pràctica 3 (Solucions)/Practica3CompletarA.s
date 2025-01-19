# (c) FIBerHub, https://fiberhub.tk

.text
	.align 4
	.globl OperaVec
	.type	OperaVec, @function
OperaVec:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$16, %esp
	pushl	%ebx
	pushl	%esi
	pushl	%edi
	movl	8(%ebp), %eax
	movl	(%eax), %eax
	movl	%eax, -4(%ebp)
# Aqui has de introducir el codigo

		movl $1, %ebx				#ebx = i = 1
		movl 8(%ebp), %eax			#eax = Vector[0]
		
for:	cmp %ebx, 12(%ebp)
		jle fifor					#jump if i >= elementos
		
		movl (%eax, %ebx, 4), %ecx	#ecx = Vector[i]
		cmp %ecx, -4(%ebp)			#res - Vector[i]
		jge endif					#jump if Vector[i] <= res
		movl %ecx, -4(%ebp)			#res = Vector[i]
		
endif:	incl %ebx					#i++
		jmp for

fifor:	

# El final de la rutina ya esta programado
	movl	-4(%ebp), %eax
	popl	%edi
	popl	%esi
	popl	%ebx
	movl %ebp,%esp
	popl %ebp
	ret
