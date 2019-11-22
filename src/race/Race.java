package race;

import java.util.ArrayList;
import java.util.Arrays;

public class Race {
    private ArrayList<Stage> stages;
    private int finished = 0;

    public ArrayList<Stage> getStages() { return stages; }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public synchronized void finish(Car car) {
        finished++;

        if (finished == 1) {
            System.out.println(car.getName() + " - WIN");
        } else {
            System.out.println(car.getName() + " закончил гонку");
        }
    }
}
