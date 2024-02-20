package lab1.balls;

public class BallThread extends Thread {
    private Ball b;
    private BallThread thread = null;

 
    public BallThread(Ball ball) {
        b = ball;
    }

    public BallThread(Ball ball, int priority) {
        b = ball;
        this.setPriority(priority);
    }
    public BallThread(Ball ball, BallThread thread) {
        b = ball;
        this.thread = thread;
    }


    @Override
    public void run() {
        try {
            if(thread!=null){
                thread.join();
            }
            while(!b.isIsInPocket()){
                b.move();
                System.out.println("Thread name = "
                + Thread.currentThread().getName());

                    Thread.sleep(5);
            } 
            }catch (InterruptedException e) {
        }
    }

}
