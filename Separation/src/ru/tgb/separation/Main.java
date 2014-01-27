package ru.tgb.separation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static long count = 1000000;
    public static int threadN = 10;
    public static volatile int[] counterSimple = new int[threadN];
    public static volatile int[][] counterArray = new int[threadN][128];

    public static void main(String[] args) {
        List<Thread> threadSC = new ArrayList<Thread>(threadN);
        List<Thread> threadAC = new ArrayList<Thread>(threadN);
        for (int i = 0; i < threadN; i++) {
            threadSC.add(new Thread(new SimpleCounterThread(i)));
            threadAC.add(new Thread(new ArrayCounterThread(i)));
        }
        System.out.println("Testing " + threadN + " threads with " + count + " iterations");
        long timeSC = timing(threadSC);
        System.out.println("Simple counters time: " + timeSC/1000 + "." + timeSC%1000 + " sec");
        long timeAC = timing(threadAC);
        System.out.println("Array counters time: " + timeAC/1000 + "." + timeAC%1000 + " sec");
    }

    private static long timing(List<Thread> threads) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < threadN; i++) {
            threads.get(i).start();
        }
        for (int i = 0; i < threadN; i++) {
            try {
                if (threads.get(i).isAlive()) threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return System.currentTimeMillis() - startTime;
    }
}
