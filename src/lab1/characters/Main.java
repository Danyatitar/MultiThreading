package lab1.characters;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        PrintCharacter thread1 = new PrintCharacter("|",10000);
        PrintCharacter thread2 = new PrintCharacter("-",10000);


        thread1.start();
        thread2.start();

    }
}
