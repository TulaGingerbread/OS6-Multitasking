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
	for (int j=0; j < cycles; j++) {
		array[rand()%size_of_array] = 10;
	}
	free(array);
	return 0;
}
