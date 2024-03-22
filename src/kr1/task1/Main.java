package kr1.task1;

import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        var threadPool = Executors.newFixedThreadPool(5);

        // Виконуємо в тердпулі
        for (int i = 0; i < 25; i++) {
            threadPool.execute(new Task());
        }

        // вИмикаємо
        threadPool.shutdown();
    }
}

class Task implements Runnable {
    public void run() {
        // Виконуємо в треді
        System.out.println("Executed in " + Thread.currentThread().getName());
    }
}
