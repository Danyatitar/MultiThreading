package kr1.task6;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class SumArray {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int ARRAYS = 1000000;
        int TASKS = 100;
        int THREADS = 8;
        var array = new double[ARRAYS];
        for (int i = 0; i < ARRAYS; i++) {
            array[i] = Math.random();
        }
        
        var executorService = Executors.newFixedThreadPool(THREADS);
        double sum = 0;

        for (int i = 0; i < ARRAYS; i += TASKS) {
            int endIndex = Math.min(i + TASKS, ARRAYS);
            Callable<Double> task = new Sum(array, i, endIndex);
            var futureResult = executorService.submit(task);
            sum += futureResult.get();
        }

        executorService.shutdown();
        System.out.println("Sum: " + sum);
    }
}

class Sum implements Callable<Double> {
    private final double[] array;
    private final int start;
    private final int end;

    public Sum(double[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    public Double call() {
        double sum = 0;
        for (int i = start; i < end; i++) {
            sum += array[i];
        }
        return sum;
    }
}

