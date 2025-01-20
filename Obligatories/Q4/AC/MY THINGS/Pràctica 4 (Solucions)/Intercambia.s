# (c) FIBerHub, https://fiberhub.tk

 .text
	.align 4
	.globl Intercambiar
	.type Intercambiar,@function
Intercambiar:
        pushl %ebp
        movl %esp, %ebp
        subl $12, %esp

        pushl %ebx
        pushl %esi

        imul $12, 12(%ebp), %eax        #eax = i*12
        imul $12, 16(%ebp), %ebx        #ebx = j*12

        addl 8(%ebp), %eax              #eax = v[i]
        addl 8(%ebp), %ebx              #ebx = v[j]

        movb (%eax), %cl                #cl = v[i].c
        movb (%ebx), %dl                #dl = v[j].c
        movb %dl, ( %eax)               #v[i].c = dl
        movb %cl, (%ebx)                #v[j].c = cl

        movl 4(%eax), %ecx              #ecx = v[i].k
        movl 4(%ebx), %esi              #esi = v[j].k
        movl %esi, 4(%eax)              #v[i].k = esi
        movl %ecx, 4(%ebx)              #v[j].k = ecx

        movl 8(%eax), %ecx              #ecx = v[i].m
        movl 8(%ebx), %esi              #esi = v[j].m
        movl %esi, 8(%eax)              #v[i].m = esi
        movl %ecx, 8(%ebx)              #v[j].m = ecx

        popl %esi
        popl %ebx

        movl %ebp, %esp
        popl %ebp
        ret
