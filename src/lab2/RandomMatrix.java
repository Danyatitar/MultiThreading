package lab2;

public class RandomMatrix {
    private Matrix matrixEntity;

    public Matrix getMatrixEntity() {
        return matrixEntity;
    }

    public void setMatrixEntity(Matrix matrixEntity) {
        this.matrixEntity = matrixEntity;
    }

    public Matrix generateRandomMatrix(int rows, int columns) {
        matrixEntity = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrixEntity.set(i, j, (int) (Math.random() * 10));
            }
        }
        return matrixEntity;
    }
}

