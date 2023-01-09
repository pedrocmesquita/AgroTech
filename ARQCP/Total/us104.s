.section .text
    .global limite_sensor

limite_sensor:

    loop:
        cmpl %edx, %edi
        jge max_limit

        jmp end

        max_limit:
            cmpl %edx, %esi
            jle end_true

            jmp end

        end_true:
            movl %edx, %eax
            ret

        end:
            movl $-1, %eax
            ret

            
