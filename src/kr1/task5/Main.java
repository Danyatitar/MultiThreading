package kr1.task5;

import java.util.ArrayList;

public class Main implements Runnable {

    private ArrayList<String> list;
    private ArrayList<String> text;

    public Main(ArrayList<String> list, ArrayList<String> text) {
        this.list = list;
        this.text = text;
    }

    public void run() {
        synchronized (text) {

            for(int i=1; i<1000;i++){
                for (String str : list) {
               
                    text.add(str);
                }
            }
     
          
            }
        

    }

    public static void main(String[] args) {

        var list1 = new ArrayList<String>();
        list1.add("Hello");
        list1.add("World");

        var list2 = new ArrayList<String>();
        list2.add("Goodbye");
        list2.add("World");

        var text = new ArrayList<String>();

        var t1 = new Thread(new Main(list1, text));
        var t2 = new Thread(new Main(list2, text));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(text);
    }
}
