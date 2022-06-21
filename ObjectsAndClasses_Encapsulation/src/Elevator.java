import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class Elevator {
    private int currentFloor = 1;
    private int minFloor;
    private int maxFloor;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public void moveDown() {
        currentFloor -= 1;
    }

    public void moveUp() {
        currentFloor += 1;
    }

    public void move(int floor) throws InterruptedException {
        DecimalFormat dF = new DecimalFormat("00");
        if (floor < minFloor || floor > maxFloor) {
            System.out.println("Error occurred, seemed like You missed the button.");
            System.exit(0);
        } else if (floor == currentFloor) {
            System.out.println("You are already on the " + dF.format(currentFloor) +
                    " floor");
        } else if (floor > currentFloor) {
            while (currentFloor < floor) {
                System.out.println((currentFloor < 0 ? "" : " ") + dF.format(currentFloor) +
                        " floor...");
                TimeUnit.MILLISECONDS.sleep(300);
                this.moveUp();
            }
            System.out.println("You arrived, " + dF.format(currentFloor) + " floor.");
        } else {
            while (currentFloor > floor) {
                System.out.println((currentFloor < 0 ? "" : " ") + dF.format(currentFloor) +
                        " floor...");
                TimeUnit.MILLISECONDS.sleep(300);
                this.moveDown();
            }
            System.out.println("You arrived, " + dF.format(currentFloor) + " floor.");
        }
    }
    public int getCurrentFloor() {
        return currentFloor;
    }
}
