.global .text
    .global limit_sensor

limit_sensor:

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
            movl $0, %eax
            ret

            