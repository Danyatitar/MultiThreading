package kr1.task3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int count;

    public static void main(String[] args) throws InterruptedException {
        final var example = new ConditionExample();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    example.increment();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

        example.awaitGreaterThan(8);
        System.out.println("Count is greater than 8");
        System.exit(0);
    }

    private void increment() {
        lock.lock();
        try {
            count++;
            System.out.println(count);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private void awaitGreaterThan(int target) throws InterruptedException {
        lock.lock();
        try {
            while (count <= target) {
                condition.await(); // очікуємо сигналу
            }
        } finally {
            lock.unlock();
        }
    }


}