package race;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private Semaphore sph;

    public Tunnel(int limit) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.sph = new Semaphore(limit);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                sph.acquire();
                System.out.println(c.getName() + " начал этап: " + description);

                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);

                sph.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
