package race;

import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }

    private CountDownLatch carsReadiness;
    private CountDownLatch notFinishedCars;

    private Race race;
    private int speed;
    private String name;


    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CountDownLatch carsReadiness, CountDownLatch notFinishedCars) {
        this.race = race;
        this.speed = speed;
        this.carsReadiness = carsReadiness;
        this.notFinishedCars = notFinishedCars;

        CARS_COUNT++;

        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");

            carsReadiness.countDown();
            carsReadiness.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        race.finish(this);
        notFinishedCars.countDown();
    }
}
