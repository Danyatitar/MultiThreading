package lab2.test;

import lab2.Matrix;
import lab2.RandomMatrix;

public class Task4Test {
    public static void main(String[] args) {
        RandomMatrix randomMatrixGenerator = new RandomMatrix();

        var MATRIX_SIZE = 1500;
        var THREADS_COUNT = 8;

        var startTime = System.currentTimeMillis();
        var endTime = System.currentTimeMillis();


        var matrixA = new Matrix(
                randomMatrixGenerator
                        .generateRandomMatrix(MATRIX_SIZE, MATRIX_SIZE)
                        .getMatrix());

        var matrixB = new Matrix(
                randomMatrixGenerator
                        .generateRandomMatrix(MATRIX_SIZE, MATRIX_SIZE)
                        .getMatrix());

        var parallelCalculator = new lab2.ParallelCalculator();
        var foxCalculator = new lab2.FoxCalculator(matrixA, matrixB, THREADS_COUNT);

        // Parallel test
        startTime = System.currentTimeMillis();
        var parRes = new Matrix(parallelCalculator.multiplyMatrix(matrixA, matrixB, THREADS_COUNT).getMatrix());
        endTime = System.currentTimeMillis();
        System.out.println("Parallel: " + (endTime - startTime) + " ms " + "with " + THREADS_COUNT + " threads" );

        // Fox test
        startTime = System.currentTimeMillis();
        var foxRes = new Matrix(foxCalculator.multiplyMatrix().getMatrix());
        endTime = System.currentTimeMillis();
        System.out.println("Fox: " + (endTime - startTime) + " ms " + "with " + THREADS_COUNT + " threads" );

        // Check results
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (parRes.get(i, j) != foxRes.get(i, j)) {
                    System.out.println("Error");
                    return;
                }
            }
        }
    }
}
