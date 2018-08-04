	.section	__TEXT,__text,regular,pure_instructions
	.macosx_version_min 10, 12
	.globl	_set_display_bits_from_tod
	.p2align	4, 0x90
_set_display_bits_from_tod:             ## @set_display_bits_from_tod
	.cfi_startproc
## BB#0:
	pushq	%rbp
Lcfi0:
	.cfi_def_cfa_offset 16
Lcfi1:
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
Lcfi2:
	.cfi_def_cfa_register %rbp
	movl	%edi, %ecx
	shll	$16, %ecx
	movl	$1, %eax
	cmpl	$786432, %ecx           ## imm = 0xC0000
	jg	LBB0_6
## BB#1:
	movl	%edi, %ecx
	sarl	$16, %ecx
	cmpl	$59, %ecx
	jg	LBB0_6
## BB#2:
	movq	%rdi, %rdx
	shrq	$32, %rdx
	shll	$16, %edx
	cmpl	$3866624, %edx          ## imm = 0x3B0000
	jg	LBB0_6
## BB#3:
	movswl	%di, %eax
	movslq	%ecx, %rdx
	imulq	$1717986919, %rdx, %r8  ## imm = 0x66666667
	movq	%r8, %rdx
	shrq	$63, %rdx
	sarq	$34, %r8
	addl	%edx, %r8d
	leal	(%r8,%r8), %edx
	leal	(%rdx,%rdx,4), %edx
	subl	%edx, %ecx
	cltq
	imulq	$1717986919, %rax, %r11 ## imm = 0x66666667
	movq	%r11, %r9
	shrq	$63, %r9
	sarq	$34, %r11
	leal	(%r11,%r9), %edx
	addl	%edx, %edx
	leal	(%rdx,%rdx,4), %edx
	movl	%eax, %r10d
	subl	%edx, %r10d
	movabsq	$71776119061217280, %rdx ## imm = 0xFF000000000000
	testq	%rdx, %rdi
	movl	$128, %edi
	movl	$256, %edx              ## imm = 0x100
	cmovel	%edi, %edx
	addl	$9, %eax
	cmpl	$19, %eax
	jb	LBB0_5
## BB#4:
	addl	%r9d, %r11d
	movslq	%r11d, %rax
	leaq	l_set_display_bits_from_tod.bitRep(%rip), %rdi
	addl	(%rdi,%rax,4), %edx
LBB0_5:
	shll	$7, %edx
	movslq	%r10d, %rax
	leaq	l_set_display_bits_from_tod.bitRep(%rip), %rdi
	addl	(%rdi,%rax,4), %edx
	shll	$7, %edx
	movslq	%r8d, %rax
	addl	(%rdi,%rax,4), %edx
	shll	$7, %edx
	movslq	%ecx, %rax
	addl	(%rdi,%rax,4), %edx
	movl	%edx, (%rsi)
	xorl	%eax, %eax
LBB0_6:
	popq	%rbp
	retq
	.cfi_endproc

	.section	__TEXT,__const
	.p2align	4               ## @set_display_bits_from_tod.bitRep
l_set_display_bits_from_tod.bitRep:
	.long	63                      ## 0x3f
	.long	6                       ## 0x6
	.long	91                      ## 0x5b
	.long	79                      ## 0x4f
	.long	102                     ## 0x66
	.long	109                     ## 0x6d
	.long	125                     ## 0x7d
	.long	7                       ## 0x7
	.long	127                     ## 0x7f
	.long	111                     ## 0x6f

	.comm	_tod,8,1                ## @tod

.subsections_via_symbols
