# (c) FIBerHub, https://fiberhub.tk

 .text
	.align 4
	.globl Ordenar
	.type Ordenar,@function
Ordenar:
        pushl %ebp
        movl %esp, %ebp
        subl $8, %esp

        pushl %ebx
        pushl %esi
        pushl %edi


        movl 8(%ebp), %edi                  #edi = v[]
        movl $0, %ebx                       #ebx = i = 0


for:    imul $12, %ebx, %ecx                #eax = i*12
        cmpl $0x80000000, 4(%edi, %eax)
        je fifor                            #jump if v[i].k == 0x80000000

        movl %ebx, %esi                     #esi = ebx
        incl %esi                           #esi = j = i+1

for2:   imul $12, %esi, %eax                #eax = j*12
        cmpl $0x80000000, 4(%edi, %eax)
        je fifor2                           #jump if v[j].k == 0x80000000

        imul $12, %ebx, %ecx
        movl 4(%edi, %ecx), %edx
        cmpl %edx, 4(%edi, %eax)
        jge fi                              #jump if v[j].k >= v[i].k


        pushl %esi                          #esi = j
        pushl %ebx                          #ebx = i
        pushl %edi                          #edi = v[]
        call Intercambiar
        addl $12, %esp


fi:     incl %esi
        jmp for2


fifor2: incl %ebx
        jmp for

fifor:  movl %ebx, %eax                     #eax = i

        popl %ebx
        popl %esi
        popl %edi

        movl %ebp, %esp
        popl %ebp
        ret
