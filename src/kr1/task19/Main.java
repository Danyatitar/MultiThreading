package kr1.task19;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        var buffer = new Buffer(10);

        var threadA = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                var obj = new Object();
                synchronized (buffer) {
                    while (buffer.isFull()) {
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    buffer.add(obj);
                    buffer.notifyAll();
                }
            }
        });

        var threadB = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Object obj = new Object();
                synchronized (buffer) {
                    while (buffer.isEmpty()) {
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    buffer.add(obj);
                
                    buffer.notifyAll();
                }
            }
        });

        var threadC = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Object obj;
                synchronized (buffer) {
                    while (buffer.isEmpty()) {
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    obj = buffer.remove();
                  
                    buffer.notifyAll();
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }

    static class Buffer {
        private LinkedList<Object> buffer;
        private int maxSize;

        public Buffer(int maxSize) {
            this.maxSize = maxSize;
            buffer = new LinkedList<>();
        }

        public synchronized boolean isFull() {
            return buffer.size() == maxSize;
        }

        public synchronized boolean isEmpty() {
            return buffer.isEmpty();
        }

        public synchronized void add(Object obj) {
            buffer.add(obj);
        }

        public synchronized Object remove() {
            return buffer.remove();
        }
    }
}
