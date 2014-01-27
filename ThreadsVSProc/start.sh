#!/bin/bash
echo "Threads";
gcc -std=c99 threads.c -o threads -lpthread;
time ./threads;
echo;
echo "Processes";
gcc -std=c99 procs.c -o procs -lpthread;
time ./procs;