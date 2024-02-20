package lab1.balls;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;




public class BallCanvas extends JPanel {
    private ArrayList<Ball> balls = new ArrayList<>();
    private ArrayList<Pocket> pockets;
    private int droppedCounter = 0;
    JLabel droppedCounterLabel;

    public void add(Ball b) {
        this.balls.add(b);
    }

    public ArrayList<Pocket> getPockets() {
        return pockets;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        pockets = generatePockets();
        paintPockets(g2);
        for (Ball ball : balls) {
            if (ball.isIsInPocket()) {
                droppedCounter++;
            }
        }
        droppedCounterLabel.setText("Dropped balls: " + droppedCounter);
        balls.removeIf(ball -> ball.isIsInPocket());
        for (Ball b : balls) {
            b.draw(g2);
        }
    }


    private ArrayList<Pocket> generatePockets() {
        ArrayList<Pocket> pockets = new ArrayList<>();
        pockets.add(new Pocket(-20, -20, 20));
        pockets.add(new Pocket(-20, this.getHeight() - 20, 20));
        pockets.add(new Pocket(this.getWidth() - 20, -20, 20));
        pockets.add(new Pocket(this.getWidth() - 20, this.getHeight() - 20, 20));
        return pockets;
    }

    private void paintPockets(Graphics2D g2) {
        for (Pocket p : pockets) {
            p.draw(g2);
        }
    }


}
