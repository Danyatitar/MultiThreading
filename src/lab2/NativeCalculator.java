
package lab2;

public class NativeCalculator {
    public Matrix multiplyMatrix(Matrix matrixA, Matrix matrixB) {
        if (matrixA.getColumns() != matrixB.getRows()) {
            throw new IllegalArgumentException("Matrix cannot be multiplied because the " +
                    "number of columns of matrix A is not equal to the number of rows of matrix B.");
        }

        int rowsA = matrixA.getRows();
        int colsA = matrixA.getColumns();
        int colsB = matrixB.getColumns();

        Matrix resultMatrix = new Matrix(rowsA, colsB);

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                int sum = 0;
                for (int k = 0; k < colsA; k++) {
                    sum += matrixA.get(i, k) * matrixB.get(k, j);
                }
                resultMatrix.set(i, j, sum);
            }
        }

        return resultMatrix;
    }
}



