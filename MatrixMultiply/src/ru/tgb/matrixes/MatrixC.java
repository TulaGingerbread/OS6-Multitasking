package ru.tgb.matrixes;

public class MatrixC implements Runnable {
    private static int[][] A = new int[Main.M][Main.M];
    private static int[][] B = new int[Main.M][Main.M];
    private static int[][] C = new int[Main.M][Main.M];
    private int myOffset;

    public static void initialize() {
        for (int i = 0; i < Main.M; i++) {
            for (int j = 0; j < Main.M; j++) {
                A[i][j] = 1;
                B[i][j] = 2;
            }
        }
    }

    public MatrixC(int o) {
        myOffset = o;
    }

    @Override
    public void run() {
        for (int i = myOffset, l = myOffset + Main.BLOCK; i < l; i++) {
            for (int j = 0; j < Main.M; j++) {
                for (int k = 0; k < Main.M; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }
}
