package others.ood;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Functionality & Test Case
 *  1.Customer/Worker request a customer/freight elevator at floor i
 *  2.People enter the elevator and press the button to go up or down
 *  3.Another person requests on floor on/out of the path
 *
 * Scenario:
 *  Customer/Worker press the button to request an elevator
 *  Controller directs elevator to handle the request
 *  Elevator arrives at specific floor, the request handle is complete
 *
 * Object Model
 *  1.What kind of properties of elevator should we consider: capacity...
 *  2.How many elevators in our system, run from which floor
 *
 * Algorithm
 *  1.How to schedule elevator efficiently to save power?
 */
class Passenger {
    private int srcFloor;

    public void request(int dstFloor) {

    }

    public void request(Type type, int dstFloor) {

    }
}

class ElevatorSystem {

    private List<ElevatorController> elevators;

    public ElevatorSystem() {
        elevators = new ArrayList<>();
        elevators.add(new ElevatorController(new CustomerElevator("1", 10, 1, 20)));
        elevators.add(new ElevatorController(new CustomerElevator("2", 10, 1, 10)));
        elevators.add(new ElevatorController(new FreightElevator("3", 20, 1, 20)));
    }

    public void request(Type type, int floor) {

    }

}

class ElevatorController {
    private Elevator elevator;
    private TreeSet<Integer> task1 = new TreeSet<>(); // go-down tasks
    private TreeSet<Integer> task2 = new TreeSet<>(); // go-up tasks

    public ElevatorController(Elevator elevator) {
        this.elevator = elevator;
    }

    public void execute() {
        if (task1.isEmpty() && task2.isEmpty()) {
            elevator.stop();
        } else if (task1.isEmpty()) {
            TreeSet<Integer> tmp = task1;
            task1 = task2;
            task2 = tmp;
        } else {
            int nextStop;
            if (elevator.getState() == State.MOVING_DOWN) {
                elevator.moveDown();
                nextStop = task1.last();
            } else {
                elevator.moveUp();
                nextStop = task1.first();
            }

            if (elevator.getCurFloor() == nextStop) {
                elevator.stop();
                elevator.openDoor();
                elevator.closeDoor();
            }
        }
    }

    public void handle(int floor) {
        if (elevator.getState() == State.MOVING_DOWN && floor <= elevator.getCurFloor()) {
            task1.add(floor);
        } else if (elevator.getState() == State.MOVING_UP && floor >= elevator.getCurFloor()) {
            task1.add(floor);
        } else {
            task2.add(floor);
        }
    }
}

enum Type {
    CUSTOMER, FREIGHT
}

enum State {
    STOPPED,
    MOVING_UP,
    MOVING_DOWN,
    OUT_OF_SERVICE
}

abstract class Elevator {
    protected String name;
    protected Type type;
    protected State state;
    protected int capacity;
    protected int curFloor;
    protected int fromFloor;
    protected int toFloor;

    public Elevator(String name, Type type, int capacity, int fromFloor, int toFloor) {
        this.type = type;
        this.state = State.STOPPED;
        this.capacity = capacity;
        this.curFloor = 1;
        this.fromFloor = fromFloor;
        this.toFloor = toFloor;
    }

    public void moveUp() {
        if (curFloor < toFloor) {
            curFloor++;
            state = State.MOVING_UP;
            log("Move up to floor %d", curFloor);
        } else {
            stop();
        }
    }

    public void moveDown() {
        if (fromFloor < curFloor) {
            curFloor--;
            state = State.MOVING_DOWN;
            log("Move down to floor %d", curFloor);
        } else {
            stop();
        }
    }

    public void stop() {
        state = State.STOPPED;
        log("Stop at floor %d", curFloor);
    }

    public void openDoor() {
        log("Open door");
    }

    public void board() {

    }

    public void closeDoor() {
        log("Close door and wait for command");
    }

    public State getState() {
        return state;
    }

    public int getCurFloor() {
        return curFloor;
    }

    private void log(String msg, Object...args) {
        System.out.println("Elevator " + name + ": " + String.format(msg, args));
    }
}

class CustomerElevator extends Elevator {

    public CustomerElevator(String name, int capacity, int fromFloor, int toFloor) {
        super(name, Type.CUSTOMER, capacity, fromFloor, toFloor);
    }
}

class FreightElevator extends Elevator {

    public FreightElevator(String name, int capacity, int fromFloor, int toFloor) {
        super(name, Type.FREIGHT, capacity, fromFloor, toFloor);
    }
}

