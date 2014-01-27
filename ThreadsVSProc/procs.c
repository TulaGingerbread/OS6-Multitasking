#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <unistd.h>

#define COUNT 100000 // amount of cycles to run
#define PIDS 64 // amount of forks to make

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
    pid_t pid;
    for (int i = 0; i < PIDS; i++) {
        if (pid = fork()) {
            cycle();
            wait(0);
        }
        else {
            exit(0);
        }
    }
    return 0;
}
