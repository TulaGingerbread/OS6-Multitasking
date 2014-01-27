package ru.tgb.matrixes;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public final static int TN = 4;
    public final static int M = 1024;
    public final static int BLOCK = M/TN;

    public static void main(String[] args) {
        MatrixC.initialize();
        MatrixBlock.initialize();
        List<Thread> matricesC = new ArrayList<Thread>(TN);
        List<Thread> matricesB = new ArrayList<Thread>(TN);
        for (int i = 0; i < TN; i++) {
            matricesC.add(new Thread(new MatrixC(i * BLOCK)));
            matricesB.add(new Thread(new MatrixC(i * BLOCK)));
        }
        System.out.println("Testing " + TN + " threads with " + M + "x" + M + " matrices");
        long matrixCT = timing(matricesC);
        System.out.println("C-type matrices time: " + matrixCT/1000 + "." + matrixCT%1000 + " sec");
        long matrixBT = timing(matricesB);
        System.out.println("Block matrices time: " + matrixBT/1000 + "." + matrixBT%1000 + " sec");
    }

    private static long timing(List<Thread> threads) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < TN; i++) {
            threads.get(i).start();
        }
        for (int i = 0; i < TN; i++) {
            try {
                if (threads.get(i).isAlive()) threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return System.currentTimeMillis() - startTime;
    }
}
