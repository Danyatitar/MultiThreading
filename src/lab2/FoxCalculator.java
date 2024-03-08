

package lab2;

 public class FoxCalculator {
    private Matrix matrixA;
    private Matrix matrixB;
    private int threadsCount;
    private Matrix resultMatrix;

    public FoxCalculator(Matrix matrixA, Matrix matrixB, int threadsCount) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.resultMatrix = new Matrix(matrixA.getRows(), matrixB.getColumns());

        if (threadsCount > matrixA.getRows() * matrixB.getColumns() / 4) {
            this.threadsCount = matrixA.getRows() * matrixB.getColumns() / 4;
        } else this.threadsCount = Math.max(threadsCount, 1);
    }

    public Matrix multiplyMatrix() {
        var step = (int) Math.ceil(1.0 * matrixA.getRows() / (int) Math.sqrt(threadsCount));

        FoxCalculatorThread[] threads = new FoxCalculatorThread[threadsCount];
        var idx = 0;

        for (int i = 0; i < matrixA.getRows(); i += step) {
            for (int j = 0; j < matrixB.getColumns(); j += step) {
                threads[idx] = new FoxCalculatorThread(matrixA, matrixB, i, j, step, resultMatrix);
                idx++;
            }
        }

        for (int i = 0; i < idx; i++) {
            threads[i].start();
        }

        for (int i = 0; i < idx; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return resultMatrix;
    }


    private static class FoxCalculatorThread extends Thread {
        private final Matrix matrixA;
        private final Matrix matrixB;
        private final int currentRow;
        private final int currentColumn;
        private final int blockSize;
        private final Matrix resultMatrix;

        public FoxCalculatorThread(Matrix matrixA, Matrix matrixB, int currentRow,
                                   int currentColumn, int blockSize, Matrix resultMatrix) {
            this.resultMatrix = resultMatrix;
            this.matrixA = matrixA;
            this.matrixB = matrixB;
            this.currentRow = currentRow;
            this.currentColumn = currentColumn;
            this.blockSize = blockSize;
        }

        @Override
        public void run() {
            int rowSize = (currentRow + blockSize > matrixA.getRows()) ?
                    matrixA.getRows() - currentRow : blockSize;
            int colSize = (currentColumn + blockSize > matrixB.getColumns()) ?
                    matrixB.getColumns() - currentColumn : blockSize;

            for (int k = 0; k < matrixA.getRows(); k += blockSize) {
                int blockARowSize = (k + blockSize > matrixA.getColumns()) ?
                        matrixA.getColumns() - k : blockSize;
                int blockBColumnSize = (k + blockSize > matrixB.getRows()) ?
                        matrixB.getRows() - k : blockSize;

                Matrix blockA = copyBlock(matrixA, currentRow, currentRow + rowSize,
                        k, k + blockARowSize);
                Matrix blockB = copyBlock(matrixB, k, k + blockBColumnSize,
                        currentColumn, currentColumn + colSize);

                Matrix blockResult = new NativeCalculator().multiplyMatrix(blockA, blockB);

                for (int i = 0; i < blockResult.getRows(); i++) {
                    for (int j = 0; j < blockResult.getColumns(); j++) {
                        resultMatrix.set(i + currentRow, j + currentColumn, blockResult.get(i, j)
                                + resultMatrix.get(i + currentRow, j + currentColumn));
                    }
                }
            }
        }

        private Matrix copyBlock(Matrix sourceMatrix, int startRow, int endRow, int startCol, int endCol) {
            int numRows = endRow - startRow;
            int numCols = endCol - startCol;
            Matrix blockMatrix = new Matrix(numRows, numCols);
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    blockMatrix.set(i, j, sourceMatrix.get(i + startRow, j + startCol));
                }
            }
            return blockMatrix;
        }
    }
}
