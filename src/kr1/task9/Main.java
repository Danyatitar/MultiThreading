package kr1.task9;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Car[] cars = new Car[100];
        TrafficLight trafficLight = new TrafficLight(cars);

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(trafficLight);
        }

        trafficLight.start();
        for (Car car : cars) {
            car.start();
        }

        for (Car car : cars) {
            car.join();
        }
        trafficLight.join();
    }
}

enum Color {
    RED,
    YELLOW,
    GREEN
}

class Car extends Thread {
    private final TrafficLight trafficLight;
    private static int count = 0;

    public Car(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }

    public static int getCount() {
        return count;
    }

    private void go() {
        System.out.println("Car has passed!!!");
    }

    @Override
    public void run() {
        while (count < 1000) {
            try {
                synchronized (trafficLight) {
                    while (trafficLight.getColor() != Color.GREEN) {
                        try {
                            trafficLight.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    sleep(2);
                    go();
                    count++;
                }
                sleep(400);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class TrafficLight extends Thread {
    private Color color;
    private final Car[] cars;

    public TrafficLight(Car[] cars) {
        this.color = Color.GREEN;
        this.cars = cars;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void run() {
        while (Car.getCount() < 100) {
            try {
                sleep(70);
                color = Color.YELLOW;
                System.out.println(color.toString());
                sleep(10);
                color = Color.RED;
                System.out.println(color.toString());
                sleep(40);
                color = Color.YELLOW;
                System.out.println(color.toString());
                sleep(10);
                color = Color.GREEN;
                System.out.println(color.toString());
                synchronized (this) {
                    notifyAll();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


