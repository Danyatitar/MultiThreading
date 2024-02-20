package lab1.characters;

public class PrintCharacter extends Thread {
    private final String character;
    private int times;
    private static final Object monitor = new Object();
    private static volatile boolean turn = false; 

    public PrintCharacter(String character, int times) {
        this.character = character;
        this.times = times;
    }

    public void run() {
        for (int i = 0; i < times; i++) {
                synchronized (monitor) {
                    try {
                        
                        while ((this.character.equals("|") && !turn) || (this.character.equals("-") && turn)) {
                            monitor.wait(); 
                        }
                
                        System.out.print(this.character); 
                        turn = !turn; 
                        monitor.notifyAll(); 
                        
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}
