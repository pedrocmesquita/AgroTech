.global .data
    .global n
.global .text
    .global limit_sensor

limit_sensor:
    movl n(%rip), %r8d

    loop:
        cmpl $0, %r8d
        je end

        cmpl %edx, %edi
        jle max_limit

        jmp decrease

        max_limit:
            cmpl %edx, %esi
            jge min_and_max

            