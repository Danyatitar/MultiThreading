package lab2;

public class Main {
    public static void main(String[] args) {
        RandomMatrix randomMatrixGenerator = new RandomMatrix();

        var SIZE = 3;


        var matrixA = new Matrix(
                randomMatrixGenerator
                        .generateRandomMatrix(SIZE, SIZE)
                        .getMatrix());

        var matrixB = new Matrix(
                randomMatrixGenerator
                        .generateRandomMatrix(SIZE, SIZE)
                        .getMatrix());

        System.out.println("Matrix 3:");
        matrixA.print();
        System.out.println(" ----------");

        System.out.println("Matrix 4:");
        matrixB.print();
        System.out.println(" ----------");

        var sequentialCalculator = new NativeCalculator();
        var res = new Matrix(sequentialCalculator.multiplyMatrix(matrixA, matrixB).getMatrix());
        System.out.println("sequential result:");
        res.print();

        var parallelCalculator = new lab2.ParallelCalculator();
        System.out.println("parallel result:");
        var res2 = new Matrix(parallelCalculator.multiplyMatrix(matrixA, matrixB, 4).getMatrix());
        res2.print();

        var foxCalculator = new FoxCalculator(matrixA, matrixB, 4);
        System.out.println("fox result:");
        var res3 = new Matrix(foxCalculator.multiplyMatrix().getMatrix());
        res3.print();
    }

}
