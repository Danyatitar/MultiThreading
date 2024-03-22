package lab2.test;

import lab2.FoxCalculator;
import lab2.Matrix;
import lab2.NativeCalculator;
import lab2.ParallelCalculator;
import lab2.RandomMatrix;

public class Task3Test {
    public static void main(String[] args) {
        RandomMatrix randomMatrixGenerator = new RandomMatrix();

        final int MATRIX_SIZE = 1000;
        final int THREADS_COUNT = 4;

        Matrix matrixA = new Matrix(randomMatrixGenerator.generateRandomMatrix(MATRIX_SIZE, MATRIX_SIZE).getMatrix());
        Matrix matrixB = new Matrix(randomMatrixGenerator.generateRandomMatrix(MATRIX_SIZE, MATRIX_SIZE).getMatrix());

        NativeCalculator nativeCalculator = new NativeCalculator();
        ParallelCalculator parallelCalculator = new ParallelCalculator();
        FoxCalculator foxCalculator = new FoxCalculator(matrixA, matrixB, THREADS_COUNT);

        // Native test
        long startTime = System.currentTimeMillis();
        Matrix seqRes = new Matrix(nativeCalculator.multiplyMatrix(matrixA, matrixB).getMatrix());
        long endTime = System.currentTimeMillis();
        System.out.println("Native: " + (endTime - startTime) + " ms " + "for " + MATRIX_SIZE + " matrix size");

        // Parallel test
        startTime = System.currentTimeMillis();
        Matrix parRes = new Matrix(parallelCalculator.multiplyMatrix(matrixA, matrixB, THREADS_COUNT).getMatrix());
        endTime = System.currentTimeMillis();
        System.out.println("Parallel: " + (endTime - startTime) + " ms " + "for " + MATRIX_SIZE + " matrix size");

        // Fox test
        startTime = System.currentTimeMillis();
        Matrix foxRes = new Matrix(foxCalculator.multiplyMatrix().getMatrix());
        endTime = System.currentTimeMillis();
        System.out.println("Fox: " + (endTime - startTime) + " ms " + "for " + MATRIX_SIZE + " matrix size");

        // Check results
        boolean isError = false;
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (seqRes.get(i, j) != parRes.get(i, j) || seqRes.get(i, j) != foxRes.get(i, j)) {
                    System.out.println("Error at (" + i + ", " + j + ")");
                    isError = true;
                    break;
                }
            }
            if (isError)
                break;
        }

      
    }
}
