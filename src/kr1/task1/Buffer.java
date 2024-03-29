package kr1.task1;


// Напишіть фрагмент коду, в якому створюються потоки А,В,С.
// Потік А виконує створення та додавання об’єктів в буфер обмеженої довжини, а потоки С і В – їх вилучення.
// Використовуйте тип Object для об'єктів, які створюються, додаються та вилучаються.
// Write a code snippet that creates threads A, B, C.
// Thread A creates and adds objects to a buffer of limited length, and threads C and B delete them.
// Use the Object type for objects that are created, added, and deleted.
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Buffer {
    private BlockingQueue<Object> buffer;
    private int maxLength;

    public Buffer(int maxLength) {
        this.maxLength = maxLength;
        this.buffer = new LinkedBlockingQueue<>(maxLength);
    }

    public void add(Object obj) throws InterruptedException {
        buffer.put(obj);
    }

    public Object remove() throws InterruptedException {
        return buffer.take();
    }

    public static void main(String[] args) {
        final int maxLength = 10;
        final Buffer buffer = new Buffer(maxLength);

        Thread threadA = new Thread(() -> {
            for (int i = 0; i < maxLength; i++) {
                try {
                    buffer.add(new Object());
                    System.out.println("Thread A added object to buffer.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < maxLength; i++) {
                try {
                    buffer.add(new Object());
                    System.out.println("Thread B added object to buffer.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadC = new Thread(() -> {
            for (int i = 0; i < maxLength; i++) {
                try {
                    buffer.remove();
                    System.out.println("Thread C removed object from buffer.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }
}

