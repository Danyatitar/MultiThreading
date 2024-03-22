package lab3.task1.v4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;

class Bank {
    public static final int NTEST = 10000;
    private final AtomicIntegerArray accounts;
    private AtomicLong ntransacts = new AtomicLong(0);

    public Bank(int n, int initialBalance) {
        accounts = new AtomicIntegerArray(n);
        int i;
        for (i = 0; i < accounts.length(); i++)
            accounts.set(i, initialBalance);
        ntransacts.set(0);
    }

    public void transfer(int from, int to, int amount) { // ver 2 Atomic
        synchronized (this) {
            accounts.addAndGet(from, -amount);
            accounts.addAndGet(to, amount);
            if (ntransacts.incrementAndGet() % NTEST == 0) {
                test();
                Thread.currentThread().interrupt();
            }
        }
    }

/*    public void transfer(int from, int to, int amount) { // default
        accounts[from] -= amount;
        accounts[to] += amount;
        ntransacts++;
        if (ntransacts % NTEST == 0)
            test();
    }*/


    public void test() {
        AtomicInteger sum = new AtomicInteger(0); // version 2 Atomic
        for (int i = 0; i < accounts.length(); i++)
            sum.addAndGet(accounts.get(i));
        System.out.println("Transactions:" + ntransacts.get()
                + " Sum: " + sum.get());
    }


    public int size() { // version 2 Atomic
        return accounts.length();
    }
}