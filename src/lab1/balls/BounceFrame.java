package lab1.balls;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BounceFrame extends JFrame {
    private BallCanvas canvas;
    public static final int WIDTH = 550;
    public static final int HEIGHT = 350;
    public JLabel droppedCounterLabel;


    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce programm");
        this.canvas = new BallCanvas();
        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton blueBall = new JButton("Blue Ball");
        JButton redBall = new JButton("Red Ball");
        JButton buttonStop = new JButton("Stop");
        droppedCounterLabel = new JLabel("Dropped balls: 0");
        JButton priority = new JButton("Priority");
        JButton join = new JButton("Join");
        canvas.droppedCounterLabel = droppedCounterLabel;
        blueBall.addActionListener(new ActionListener() {
   
            @Override
            public void actionPerformed(ActionEvent e) {

                Ball b = new Ball(canvas.getPockets(), canvas, true);
                b.setColor(Color.blue);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.start();
                System.out.println("Thread name = " +
                        thread.getName());

            }
        });

        redBall.addActionListener(new ActionListener() {
   
            @Override
            public void actionPerformed(ActionEvent e) {

                Ball b = new Ball(canvas.getPockets(), canvas, true);
                b.setColor(Color.red);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.start();
                System.out.println("Thread name = " +
                        thread.getName());

            }
        });
        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        priority.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Ball b = new Ball(canvas.getPockets(),canvas, false);
                b.setColor(Color.red);
                canvas.add(b);

                BallThread thread = new BallThread(b, Thread.MAX_PRIORITY);
                thread.start();
                System.out.println("Thread name = " +
                        thread.getName());
            

                for (int i = 0; i < 100; i++) {
                    Ball blueBall = new Ball(canvas.getPockets(),canvas, false);
                    blueBall.setColor(Color.blue);
                    canvas.add(blueBall);

                    BallThread threadBlue = new BallThread(blueBall, Thread.MIN_PRIORITY);
                    threadBlue.start();
                    System.out.println("Thread name = " +
                            threadBlue.getName());
                }
            }
                
        });

        join.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Ball blue = new Ball(canvas.getPockets(), canvas, false);
                    blue.setColor(Color.blue);
                    canvas.add(blue);
                    Ball red = new Ball(canvas.getPockets(), canvas, false);
                    red.setColor(Color.red);
                    canvas.add(red);


                    BallThread threadBlue = new BallThread(blue);
                    BallThread threadRed = new BallThread(red, threadBlue);
                    threadBlue.start();                           
                    threadRed.start();
              


            }
        });
       
        buttonPanel.add(blueBall);
        buttonPanel.add(redBall);
        buttonPanel.add(buttonStop);
        buttonPanel.add(droppedCounterLabel);
        buttonPanel.add(priority);
        buttonPanel.add(join);
        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}
