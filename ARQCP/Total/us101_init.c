#include <stdio.h>
//#include <stdint.h>

extern unsigned long state;
extern unsigned long inc;

char initialize()
{
	//open in read only mode
	FILE *f = fopen("/dev/urandom", "r");
	
	//failsafe if initialization unsuccessful
	state = 0;
	inc = 0;
	
	//file not found
	if (f == NULL)
	{
		return -1;
	}
	
	int n = 2;
	
	//buffer to store read ints
	unsigned long buffer[n];
	
	//result will be ammount of numbers read
	int result = fread(buffer, sizeof(unsigned long), n, f);
	
	//not enough numbers found
	if (result < n)
	{
		return -1;
	}
	
	//set global variables
	state = buffer[n-2];
	inc = buffer[n-1];
	
	//no errors
	return 0;
}
