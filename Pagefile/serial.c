#include <stdio.h>
#include <stdlib.h>
#define size 80*1024*1024
#define cycles 100000000
int main(){
	int size_of_array = size;
	double *array = (double *) malloc(size_of_array*sizeof(double));//640*1024*1024 bytes; RAM=2Gb;
	if (array == NULL) {	
		printf("error in malloc\n");
		return 1;
	}
	int i=0;
	for (long int j = 0; j < cycles; j++) {
		array[i] = 10;
		i = (i+1) % (size_of_array);
	}
	free(array);
	return 0;
}
