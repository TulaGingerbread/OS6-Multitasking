package ru.tgb.matrixes;

public class MatrixBlock implements Runnable {
    private static final int RS = 32;
    private static final int CS = 32;
    private static int[][][][] A = new int[Main.M/RS][Main.M/CS][RS][CS];
    private static int[][][][] B = new int[Main.M/RS][Main.M/CS][RS][CS];
    private static int[][][][] C = new int[Main.M/RS][Main.M/CS][RS][CS];
    private int myOffset;

    public static void initialize() {
        for (int i = 0; i < Main.M; i++) {
            for (int j = 0; j < Main.M; j++) {
                A[i/RS][j/CS][i%RS][j%CS] = 1;
                B[i/RS][j/CS][i%RS][j%CS] = 2;
            }
        }
    }

    public MatrixBlock(int o) {
        myOffset = o;
    }

    @Override
    public void run() {
        for (int i = myOffset, l = myOffset + Main.BLOCK; i < l; i++) {
            for (int j = 0; j < Main.M; j++) {
                for (int k = 0; k < Main.M; k++) {
                    C[i/RS][j/CS][i%RS][j%CS] += A[i/RS][k/CS][i%RS][k%CS] * B[k/RS][j/CS][k%RS][j%CS];
                }
            }
        }
    }
}
