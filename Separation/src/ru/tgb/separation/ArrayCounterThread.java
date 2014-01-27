package ru.tgb.separation;

public class ArrayCounterThread implements Runnable {

    private final int n;

    public ArrayCounterThread(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        for (long i = 0; i < Main.count; i++) {
            Main.counterArray[n][0]++;
        }
    }
}
