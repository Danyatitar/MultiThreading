package lab3.task2;

public class Drop {

    private int numMsg;
    private boolean empty = true;

    public synchronized int take() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        empty = true;
        notifyAll();
        return numMsg;
    }

    public synchronized void put(int numMsg) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        empty = false;
        this.numMsg = numMsg;

        notifyAll();
    }
}
