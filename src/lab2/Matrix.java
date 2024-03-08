package lab2;

import java.util.Arrays;


public class Matrix {
    private final int[][] matrix;


    public Matrix(int[][] matrix) {
        this.matrix = matrix;
    }


    public Matrix(int rows, int columns) {
        matrix = new int[rows][columns];
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getRows() {
        return matrix.length;
    }

    public int getColumns() {
        return matrix[0].length;
    }


    public int get(int i, int j) {
        return matrix[i][j];
    }

    public void set(int i, int j, int value) {
        matrix[i][j] = value;
    }



    public void print() {
        Arrays.stream(matrix).map(Arrays::toString).forEach(System.out::println);
    }
}
