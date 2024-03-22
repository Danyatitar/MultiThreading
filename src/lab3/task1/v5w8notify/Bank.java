package lab3.task1.v5w8notify;

class Bank {
    public static final int NTEST = 10000;
    private final int[] accounts;
    private long ntransacts = 0;

    private boolean isInProcess = false;

    public Bank(int n, int initialBalance) {
        accounts = new int[n];
        int i;
        for (i = 0; i < accounts.length; i++)
            accounts[i] = initialBalance;
        ntransacts = 0;
    }

    public synchronized void transfer(int from, int to, int amount) {
        while (isInProcess) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isInProcess = true;

        accounts[from] -= amount;
        accounts[to] += amount;
        ntransacts++;
        if (ntransacts % NTEST == 0) {
            test();
        }

        isInProcess = false;

        this.notifyAll();
    }

    public synchronized void test() {
        int sum = 0;
        for (int i = 0; i < accounts.length; i++)
            sum += accounts[i];
        System.out.println("Transactions:" + ntransacts
                + " Sum: " + sum);
    }


    public int size() {
        return accounts.length;
    }
}