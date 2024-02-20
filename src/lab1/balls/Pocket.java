package lab1.balls;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


public class Pocket {
    private final int x;
    private final int y;
    private int radius;

    public Pocket(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void draw(Graphics2D g2) {
        g2.setColor(Color.GREEN);
        g2.fill(new Ellipse2D.Double(x, y, radius*2, radius*2));
    }


    public boolean isBallInPocket(int x, int y) {
        double collideRadius = this.getRadius();
        int pocketX = this.getX();
        int pocketY = this.getY();
        if(pocketX<0){
            pocketX = 0;
        }
        if(pocketY<0){
            pocketY = 0;
        }
        
        double distance = Math.sqrt(Math.pow(pocketX - x, 2) + Math.pow(pocketY - y, 2));
        return distance <= collideRadius;
    }
}
