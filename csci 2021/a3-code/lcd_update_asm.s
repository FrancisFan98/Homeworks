.text
.global  set_tod_from_secs

## ENTRY POINT FOR REQUIRED FUNCTION
set_tod_from_secs:
        ## assembly instructions here
   movl  $1, %eax
   cmpl  $0, %edi
   jl    .FAIL
   cmpl  $86400, %edi
   jg    .FAIL

   movl	%edi, %r8d    # move time_of_day_sec to r8d
   movq %rsi, %r9     #move tod to r9


   movl %r8d, %eax   # move time_of_day_sec to %eax for division
   cqto
   movl $3600, %esi
   idivl %esi   # %rax to quotient %rdx to remainder
   movl $12, %esi
   cqto
   idivl %esi       # %rax to quotient %rdx to remainder
   movw %dx, 0(%r9)    #set the hour

   cmpl $0, 0(%r9)   #compare hour with 0
   je .resetHour
.resume1:
   ## calculate minutes
   movl  %r8d, %eax
   cqto
   movl $3600, %esi
   idivl %esi
   movl %edx, %eax
   cqto
   movl $60, %esi
   idivl %esi
   movw %ax, 2(%r9)

   ## calculate seconds
   movl %r8d, %eax
   cqto
   movl $60, %esi
   idivl %esi
   movw %dx, 4(%r9)

   ## calculate ispm
   movl %r8d, %eax
   cqto
   movl $43200, %esi
   idivl %esi
   movb %al, 6(%r9)
   xorl %eax, %eax
   ret

.resetHour:
   movl $12, 0(%r8)
   jmp .resume1

.FAIL:
   ret
