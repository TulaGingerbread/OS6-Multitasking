#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define COUNT 100000 // amount of cycles to run
#define THREADS 64 // amount of threads to use

void * cycle() {
	int trigger, counter = 0;
	int ** arrays = (int **) malloc (COUNT/2 * sizeof(int *));
	for (int i = 0; i < COUNT; i++) {
		trigger = rand() % 3;
		switch (trigger) {
			case 0:
				arrays[counter] = (int *) malloc (256 * (rand() % 4 + 1) * sizeof(int));
				counter++;
				break;
			case 1:
				if (counter) {
					counter--;
					free(arrays[counter]);
				}
				break;
			case 2:
				if (counter) {
					arrays[counter-1][0] = 1;
				}
				break;
		}
	}
}

int main() {
    pthread_t thrs[THREADS];
    for (int i = 0; i < THREADS; i++) {
        pthread_create(&thrs[i], NULL, cycle, NULL);
    }
    for (int i = 0; i < THREADS; i++) {
        pthread_join(thrs[i], NULL);
    }
    return 0;
}