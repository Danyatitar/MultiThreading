package lab1.balls;



import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Ball {
    private Component canvas;
    private static final int XSIZE = 10;
    private static final int YSIZE = 10;
    private int x = 0;
    private int y = 0;
    private int dx = 2;
    private int dy = 2;
    private Color color;
    private boolean isInPocket = false;
    private final List<Pocket> pockets;
    
    public Ball(ArrayList<Pocket> pockets, Component c, Boolean one) {
        this.canvas = c;
        this.pockets = pockets;
        if(one){
            if (Math.random() < 0.5) {
                x = new Random().nextInt(this.canvas.getWidth());
                y = 30;
            } else {
                x = 30;
                y = new Random().nextInt(this.canvas.getHeight());
            }
        }else{

            x = this.canvas.getWidth() / 2;
            y = this.canvas.getHeight() / 4;

        }     
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    public List<Pocket> getPockets() {
        return pockets;
    }

    private boolean isBallInPocket() {
        for (Pocket pocket : pockets) {
            if (pocket.isBallInPocket(x, y)) {
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));

    }

    public void move() {
        x += dx;
        y += dy;
        if (isBallInPocket()) {
            isInPocket = true;
            System.err.println("Ball is in pocket");
            this.canvas.repaint();
            return;
        }
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= this.canvas.getWidth()) {
            x = this.canvas.getWidth() - XSIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= this.canvas.getHeight()) {
            y = this.canvas.getHeight() - YSIZE;
            dy = -dy;
        }
        this.canvas.repaint();
    }

    
    public boolean isIsInPocket() {
        return isInPocket;
    }

    public void setIsInPocket(boolean isInPocket) {
        this.isInPocket = isInPocket;
    }
}