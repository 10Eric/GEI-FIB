# (c) FIBerHub, https://fiberhub.tk

.text
	.align 4
	.globl OperaMat
	.type	OperaMat, @function
OperaMat:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$16, %esp
	pushl	%ebx
	pushl	%esi
	pushl	%edi
# Aqui has de introducir el codigo

		movl 8(%ebp), %edx			#edx = Matriz[0][0]
		movl $0, -4(%ebp)			#res = 0

		movl $0, %eax				#eax = i = 0
for1:	cmp $3, %eax
		jge fifor1					#jump if 3 <= i
		
		movl $0, %ebx				#ebx = j = 0
for2:	cmp $3, %ebx
		jge fifor2					#jump if 3 <= j
		
		movl (%edx), %ecx			#ecx = Matriz[i][j]
		addl %ecx, -4(%ebp)			#res += Matriz[i][j]
		
		addl 12(%ebp), %ebx			#j += salto
		
		imul $4, 12(%ebp), %ecx		#ecx = salto*4
		addl %ecx, %edx				#Matriz += salto
		jmp for2

fifor2: addl 12(%ebp), %eax			#i += salto

		imul $4, 12(%ebp), %ecx		#ecx = salto*4
		addl %ecx, %edx				#Matriz += salto
		jmp for1
		
fifor1:

# El final de la rutina ya esta programado
	movl	-4(%ebp), %eax
	popl	%edi
	popl	%esi
	popl	%ebx	
	movl %ebp,%esp
	popl %ebp
	ret
	
