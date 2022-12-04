.section .data
	
	#uint32_t - unsigned int (long)
	#uint64_t - unsigned long (quadword)
	
		
.section .text

	#Gera o valor de temperatura com base no último valor de temperatura.
	#O novo valor a gerar será o incremento ao último valor gerado, adicionado de um valor
	#aleatório (positivo ou negativo).
	#
	#A componente aleatória não deverá produzir variações drásticas à temperatura entre medições consecutivas.
	#
	#@param ult_temp Último valor de temperatura medido (°C)
	#@param comp_rand Componente aleatório para a geração do novo valor da temperatura
	#
	#@return A nova medição do valor da temperatura (°C)
	.global sens_temp	#char sens_temp(char ult_temp, char comp_rand)
	sens_temp:
	
		mov $5, %cl		#"drastic" temperature change limit, in degrees
		mov %cl, %r8b
		mov %cl, %r9b
		neg %r8b		#comp_rand must be higher than r8b and lower than r9b
		
		cmp %r8b, %sil
		jl inv_l
		
		cmp %r9b, %sil
		jg inv_g
		
		jmp cont
	
	#comp_rand is lower than threshold
	inv_l:
		mov %r8b, %sil
		jmp cont
	
	#comp_rand is higher than threshold
	inv_g:
		mov %r9b, %sil
		jmp cont
		
	cont:
		add %sil, %dil
		mov %dil, %al	#return char of sum of ult_temp and comp_rand
		ret
		

	#Gera o valor de velocidade do vento com base no último valor de velocidade do vento.
	#O novo valor a gerar será o incremento ao último valor gerado, adicionado de um valor
	#aleatório (positivo ou negativo).
	#
	#A componente aleatória pode produzir variações altas entre medições consecutivas, simulando assim o efeito
	#de rajadas de vento.
	#
	#@param ult_velc_vento Última velocidade do vento medida (km/h)
	#@param comp_rand Componente aleatório para a geração do novo valor da velocidade do vento
	#
	#@return O novo medição do valor da velocidade do vento (km/h)
	.global sens_velc_vento	#unsigned char sens_velc_vento(unsigned char ult_velc_vento, char comp_rand)
	sens_velc_vento:
	
		mov %dil, %r8b
		add %sil, %dil
		mov %dil, %al	#return char of sum of ult_temp and comp_rand
		
		cmp $0, %sil
		jl vel_neg
		je vel_end
		
		cmp %r8b, %dil
		jae skip5
		mov $255, %al
		jmp vel_end
		
	vel_neg:
		cmp %r8b, %dil
		jb vel_end
		mov $0, %al
		
	vel_end:
		ret
		

	#Gera o valor de direção do vento com base no último valor de direção do vento.
	#O novo valor a gerar será o incremento ao último valor gerado, adicionado de um valor
	#aleatório (positivo ou negativo).
	#
	#A direção do vento toma valores de 0 a 359, representam graus relativamente ao Norte.
	#
	#A direção do vento não deve variar de forma drástica entre medições consecutivas.
	#
	#@param ult_dir_vento Última direção do vento medida (graus)
	#@param comp_rand Componente aleatório para a geração do novo valor da direção do vento
	#
	#@return A nova medição do valor da direção do vento (graus)
	#
	.global sens_dir_vento	#unsigned short sens_dir_vento(unsigned short ult_dir_vento, short comp_rand)
	sens_dir_vento:
		
		mov $60, %cl	#"drastic" change limit
		mov %cl, %r8b
		mov %cl, %r9b
		neg %r8b		#comp_rand must be higher than r8b and lower than r9b
		
		movsbw %r8b, %r8w
		movsbw %r9b, %r9w
		
		cmp %r8w, %si
		jl inv_l2
		
		cmp %r9w, %si
		jg inv_g2
		
		jmp cont2
	
	#comp_rand is lower than threshold
	inv_l2:
		mov %r8w, %si
		jmp cont2
	
	#comp_rand is higher than threshold
	inv_g2:
		mov %r9w, %si
		jmp cont2
		
	cont2:
		add %si, %di
		mov %si, %ax
	
		cmp $359, %ax
		jbe skip		#unsigned compare, will never be a number below 0
		
		mov $0, %dx		#prepare for div
		mov $360, %cx
		div %cx			#unsigned div ax by cx
		mov %dx, %ax	#number % 360 will convert the current angle to the [0,359] range
		
	skip:
		ret


	#Gera o valor de humidade atmosférica com base no último valor de humidade atmosférica.
	#O novo valor a gerar será o incremento ao último valor gerado, adicionado de um valor
	#de modificação (positivo ou negativo).
	#
	#O valor de modificação terá uma componente aleatória e uma componente relativa ao último
	#valor de pluvisiodade registado, que contribuirá para uma maior ou menor alteração à
	#modificação.
	#
	#A menos que tenha chovido, o valor de modificação não deverá produzir variações drásticas à humidade
	#atmosférica entre medições consecutivas.
	#
	#@param ult_hmd_atm Última humidade atmosférica medida (percentagem)
	#@param ult_pluvio Último valor de pluviosidade medido (mm)
	#@param comp_rand Componente aleatório para a geração do novo valor da humidade atmosférica
	#
	#@return A nova medição do valor da humidade atmosférica (percentagem)
	#
	.global sens_humd_atm	#unsigned char sens_humd_atm(unsigned char ult_hmd_atm, unsigned char ult_pluvio, char comp_rand)
	sens_humd_atm:
	
		mov $15, %r10b	#maximum value of rainfall in mm before drastic changes are allowed
		
		cmp %r10b, %dil
		ja cont3			#jump drastic change limiter if raining
		
		mov $5, %cl		#"drastic" change limit
		mov %cl, %r8b
		mov %cl, %r9b
		neg %r8b		#comp_rand must be higher than r8b and lower than r9b
		
		cmp %r8b, %dl
		jl inv_l3
		
		cmp %r9b, %dl
		jg inv_g3
		
		jmp cont3
	
	#comp_rand is lower than threshold
	inv_l3:
		mov %r8b, %dl
		jmp cont3
	
	#comp_rand is higher than threshold
	inv_g3:
		mov %r9b, %dl
		jmp cont3
		
	cont3:
		add %dl, %dil
		mov %dil, %al	#return char of sum of ult_temp and comp_rand
		cmp $100, %al
		jbe skip3
		mov $100, %al	#percentage, cant go above 100
		
	skip3:
		ret
		

	#Gera o valor de humidade do solo com base no último valor de humidade do solo.
	#O novo valor a gerar será o incremento ao último valor gerado, adicionado de um valor
	#de modificação (positivo ou negativo).
	#
	#O valor de modificação terá uma componente aleatória e uma componente relativa ao último
	#valor de pluvisiodade registado, que contribuirá para uma maior ou menor alteração à
	#modificação.
	#
	#A menos que tenha chovido, o valor de modificação não deverá produzir variações drásticas à humidade do
	#solo entre medições consecutivas.
	#
	#@param ult_hmd_solo Última humidade do solo medida (percentagem)
	#@param ult_pluvio Último valor de pluviosidade medido (mm)
	#@param comp_rand Componente aleatório para a geração do novo valor da humidade do solo
	#
	#@return A nova medição do valor da humidade do solo (percentagem)
	#
	.global sens_humd_solo	#unsigned char sens_humd_solo(unsigned char ult_hmd_solo, unsigned char ult_pluvio, char comp_rand)
	sens_humd_solo:
		mov $15, %r10b	#maximum value of rainfall in mm before drastic changes are allowed
		
		cmp %r10b, %dil
		ja cont4		#jump drastic change limiter if raining
		
		mov $5, %cl		#"drastic" change limit
		mov %cl, %r8b
		mov %cl, %r9b
		neg %r8b		#comp_rand must be higher than r8b and lower than r9b
		
		cmp %r8b, %dl
		jl inv_l4
		
		cmp %r9b, %dl
		jg inv_g4
		
		jmp cont4
	
	#comp_rand is lower than threshold
	inv_l4:
		mov %r8b, %dl
		jmp cont4
	
	#comp_rand is higher than threshold
	inv_g4:
		mov %r9b, %dl
		jmp cont4
		
	cont4:
		add %dl, %dil
		mov %dil, %al	#return char of sum of ult_temp and comp_rand
		cmp $100, %al
		jbe skip4
		mov $100, %al	#percentage, cant go above 100
		
	skip4:
		ret
		

	#Gera o valor de pluviosidade com base no último valor de pluviosidade.
	#O novo valor a gerar será o incremento ao último valor gerado, adicionado de um valor
	#de modificação (positivo ou negativo).
	#
	#O valor de modificação terá uma componente aleatória e uma componente relativa à última
	#temperatura registada, que contribuirá para uma maior ou menor alteração à modificação.
	#
	#Assim produz-se o efeito de, com temperaturas altas ser menos provável que chova, e com
	#temperaturas mais baixas ser mais provável que chova.
	#
	#Quando a pluviosidade anterior for nula, se o valor de modificação for negativo a
	#pluviosidade deverá permanecer nula.
	#
	#@param ult_pluvio Último valor de pluviosidade medido (mm)
	#@param ult_temp Último valor de temperatura medido (°C)
	#@param comp_rand Componente aleatório para a geração do novo valor de pluviosidade
	#
	#@return A nova medição do valor de pluviosidade (mm)
	#
	.global sens_pluvio	#unsigned char sens_pluvio(unsigned char ult_pluvio, char ult_temp, char comp_rand)
	sens_pluvio:
	
		cmp $0, %dil
		jne intro	#jump if current rainfall value isnt null
		
		cmp $0, %dl
		jge intro	#jump if random component isnt negative
		
		mov $0, %al	#maintain null rainfall value return 0
		jmp skip5
		
	intro:
		mov $10, %r10b	#maximum value before drastic changes are allowed
		
		cmp %r10b, %dil
		jle cont5		#jump drastic change limiter
		
		mov $5, %cl		#"drastic" change limit
		mov %cl, %r8b
		mov %cl, %r9b
		neg %r8b		#comp_rand must be higher than r8b and lower than r9b
		
		cmp %r8b, %dl
		jl inv_l5
		
		cmp %r9b, %dl
		jg inv_g5
		
		jmp cont5
	
	#comp_rand is lower than threshold
	inv_l5:
		mov %r8b, %dl
		jmp cont5
	
	#comp_rand is higher than threshold
	inv_g5:
		mov %r9b, %dl
		jmp cont5
		
	cont5:
		mov %dl, %r8b
		add %dil, %dl
		mov %dl, %al
		
		cmp $0, %r8b
		jl rand_neg
		je skip5
		
	rand_pos:
		cmp %dil, %dl
		jae skip5
		mov $255, %al
		jmp skip5
		
	rand_neg:
		cmp %dil, %dl
		jb skip5
		mov $0, %al
		jmp skip5
		
	skip5:
		ret
