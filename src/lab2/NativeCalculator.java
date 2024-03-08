// package lab2;

// public class NativeCalculator {
//     public Matrix multiplyMatrix(Matrix matrixEntity1, Matrix matrixEntity2) {
//         if (matrixEntity1.getColumns() != matrixEntity2.getRows()) {
//             throw new IllegalArgumentException("matrices cannot be multiplied because the " +
//                     "number of columns of matrix A is not equal to the number of rows of matrix B.");
//         }

//         var resultMatrix = new Matrix(matrixEntity1.getRows(), matrixEntity2.getColumns());
//         for (int i = 0; i < matrixEntity1.getRows(); i++) {
//             for (int j = 0; j < matrixEntity2.getColumns(); j++) {
//                 for (int k = 0; k < matrixEntity1.getColumns(); k++) {
//                     resultMatrix.set(i, j, resultMatrix.get(i, j) + matrixEntity1.get(i, k) * matrixEntity2.get(k, j));
//                 }
//             }
//         }

//         return resultMatrix;
//     }

// }

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



