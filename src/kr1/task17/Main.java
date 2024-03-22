package kr1.task17;



public class Main {

    public static void main(String[] args) throws InterruptedException {
        StateHandler state = new StateHandler();
        Thread threadA = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                    State newState = state.getState() == State.R ? State.W : State.R;
                    System.out.println("Thread A changed state to " + newState);
                    state.setState(newState);
                } catch (InterruptedException e) {
                    System.err.println("Thread A has been interrupted.");
                    break;
                }
            }
        });
        Thread threadB = new Thread(() -> {
            while (true) {
                try {
                    synchronized (state) {
                        while (state.getState() != State.R) {
                            state.wait();
                        }
                        for (int i = 100; i > 0; i--) {
                            System.out.println("Countdown: " + i);
                            Thread.sleep(1);
                        }
                        System.out.println("Thread B has been suspended.");
                        state.wait();
                        System.out.println("Thread B has been resumed.");
                    }
                } catch (InterruptedException e) {
                    System.err.println("Thread B has been interrupted.");
                    break;
                }
            }
        });
        threadA.start();
        threadB.start();
        Thread.sleep(1000);
        threadA.interrupt();
        threadB.interrupt();
    }

    private static class StateHandler {
        private State state = State.R;

        public synchronized void setState(State state) {
            this.state = state;
            notifyAll();
        }

        public synchronized State getState() {
            return state;
        }
    }

    private enum State {
        R,
        W
    }
}
