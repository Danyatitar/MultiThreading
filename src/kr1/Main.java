package kr1;
import java.util.ArrayList;

class Buffer {
    private ArrayList<Object> objects;
    private int maxSize;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        this.objects = new ArrayList<>();
    }

    public synchronized void addObject(Object obj) throws InterruptedException {
        while (objects.size() == maxSize) {
            wait(); // Почекати, поки буфер не звільниться
        }
        objects.add(obj);
        System.out.println("Додано об'єкт " + obj);
        notifyAll(); // Сповістити всі потоки, які чекають
    }

    public synchronized Object removeObject() throws InterruptedException {
        while (objects.size() == 0) {
            wait(); // Почекати, поки буфер не заповниться
        }
        Object obj = objects.remove(0);
        System.out.println("Вилучено об'єкт " + obj);
        notifyAll(); // Сповістити всі потоки, які чекають
        return obj;
    }
}

class Producer implements Runnable {
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object obj = new Object(); // Створення нового об'єкта
                buffer.addObject(obj); // Додавання об'єкта до буфера
                Thread.sleep(1000); // Затримка для емуляції обробки
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object obj = buffer.removeObject(); // Вилучення об'єкта з буфера
                Thread.sleep(2000); // Затримка для емуляції обробки
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5); // Створення буфера обмеженої довжини

        // Створення та запуск потоків виробників (Producer)
        Thread producerThreadA = new Thread(new Producer(buffer));
        Thread producerThreadB = new Thread(new Producer(buffer));
        producerThreadA.start();
        producerThreadB.start();

        // Створення та запуск потоку споживача (Consumer)
        Thread consumerThread = new Thread(new Consumer(buffer));
        consumerThread.start();
    }
}