package ru.tgb.separation;

public class SimpleCounterThread implements Runnable {

    private final int n;

    public SimpleCounterThread(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        for (long i = 0; i < Main.count; i++) {
            Main.counterSimple[n]++;
        }
    }
}
