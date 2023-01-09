main: arqcp.o us101_init.o us101.o us102.o us104.o escolher_dados_sensor.o exportCSV.o gerador_valores.o matriz_diaria.o show_stats.o
	gcc -Wall -g arqcp.o us101_init.o us101.o us102.o us104.o escolher_dados_sensor.o exportCSV.o gerador_valores.o matriz_diaria.o show_stats.o -o main

arqcp.o: arqcp.o arqcp.h
	gcc -Wall -g -c arqcp.c -o arqcp.o

us101_init.o: us101_init.c
	gcc -Wall -g -c us101_init.c -o us101_init.o

us101.o: us101.s
	gcc -Wall -g -c us101.s -o us101.o

us102.o: us102.s
	gcc -Wall -g -c us102.s -o us102.o

us104.o: us104.s
	gcc -Wall -g -c us104.s -o us104.o

escolher_dados_sensor.o: escolher_dados_sensor.c arqcp.h us104.o
	gcc -Wall -g -c escolher_dados_sensor.c -o escolher_dados_sensor.o

exportCSV.o: exportCSV.c arqcp.h
	gcc -Wall -g -c exportCSV.c -o exportCSV.o

gerador_valores.o: gerador_valores.c arqcp.h
	gcc -Wall -g -c gerador_valores.c -o gerador_valores.o

matriz_diaria.o: matriz_diaria.c
	gcc -Wall -g -c matriz_diaria.c -o matriz_diaria.o

show_stats.o: show_stats.c
	gcc -Wall -g -c show_stats.c -o show_stats.o

run: main
	./main

clean:
	rm *.o main
