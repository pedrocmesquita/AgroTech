#include <stdio.h>
#include "us101.h"

extern long state;
extern long inc;

int main(void)
{
	printf("\nUS101:\n");
	
	//us101
	if (!initialize())	//initialization successful
	{
		for (int i = 0; i < 10; i++)
		{
			printf("%u\n",pcg32_random_r());
		}
	}
	
	else
	{
		printf("An error occured initializing the random number generator.\n");
	}
	
	printf("\n");
	return 0;
}
