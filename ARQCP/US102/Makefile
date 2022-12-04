prog101: us101.o us101_main.o us101_init.o
	gcc -Wall -g us101.o us101_main.o us101_init.o -o prog101
	
us101_main.o: us101_main.c us101.h
	gcc -Wall -g -c us101_main.c -o us101_main.o
	
us101_init.o: us101_init.c
	gcc -Wall -g -c us101_init.c -o us101_init.o
	
us101.o: us101.s
	gcc -Wall -g -c us101.s -o us101.o

101: prog101
	./prog101

clean:
	rm *.o prog101 prog102
