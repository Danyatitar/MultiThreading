

package lab2;

import java.util.ArrayList;

public class ParallelCalculator {

    public Matrix multiplyMatrix(Matrix matrixA, Matrix matrixB, int threadsCount) {

        if (matrixA.getColumns() != matrixB.getRows()) {
            throw new IllegalArgumentException("Matrix cannot be multiplied because the " +
                    "number of columns of matrix A is not equal to the number of rows of matrix B.");
        }

        int resultHeight = matrixA.getRows();
        int resultWidth = matrixB.getColumns();
        Matrix resultMatrix = new Matrix(resultHeight, resultWidth);

        int rowsPerThread = resultHeight / threadsCount;
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < threadsCount; i++) {
            int from = i * rowsPerThread;
            int to;

            if (i == threadsCount - 1) {
                to = resultHeight;
            } else {
                to = (i + 1) * rowsPerThread;
            }

            threads.add(new Thread(() -> {
                for (int row = from; row < to; row++) {
                    for (int col = 0; col < resultWidth; col++) {
                        int sum = 0;
                        for (int k = 0; k < matrixB.getRows(); k++) {
                            sum += matrixA.get(row, k) * matrixB.get(k, col);
                        }
                        resultMatrix.set(row, col, sum);
                    }
                }
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return resultMatrix;
    }
}
