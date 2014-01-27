#!/bin/bash
echo "Serial";
gcc -std=c99 serial.c -o serial 
time ./serial
echo "Random";
gcc -std=c99 random.c -o random 
time ./random
