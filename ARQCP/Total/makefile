main: arqcp.o
	gcc -Wall -g arqcp.o -o main

arqcp.o: arqcp.c arqcp.h
	gcc -Wall -g -c arqcp.c -o arqcp.o

us101_init.o: us101_init.c
	gcc -Wall -g -c us101_init.c -o us101_init.o

us101.o: us101.s
	gcc -Wall -g -c us101.s -o us101.o

asm.o: asm.s
	gcc -Wall -g -c asm.s -o asm.o

run: main
	./main

clean:
	rm *.o main