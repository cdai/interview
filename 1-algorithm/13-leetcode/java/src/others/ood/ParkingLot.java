package others.ood;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class ParkingLot {

    public static void main(String[] args) {
        ParkingLot parklot = new ParkingLot(1, 20);
        parklot.park(new Motorcycle("ny1234"));
        parklot.park(new Car("nj4567"));
        parklot.park(new Car("pinkman"));
        parklot.park(new Car("hello"));
        parklot.park(new Car("world"));
        parklot.park(new Car("test123"));
        Ticket t = parklot.park(new Car("testtest"));
        parklot.park(new Bus("smithtown"));
        parklot.unpark(t);
        parklot.park(new Bus("smithtown"));
    }

    private Level[] levels;
    private Map<Ticket, Spot[]> inService = new HashMap<>();

    public ParkingLot(int numLevel, int space) {
        levels = new Level[numLevel];
        for (int i = 0; i < numLevel; i++) {
            levels[i] = new Level(space);
        }
    }

    public Ticket park(Vehicle vehicle) {
        for (Level level : levels) {
            Spot[] spots = level.allocate(vehicle);
            if (spots != null) {
                Ticket ticket = new Ticket(vehicle.getPlate());
                inService.put(ticket, spots);
                return ticket;
            }
        }
        return null;
    }

    /** Unpark car and charge customer accordingly */
    public int unpark(Ticket ticket) {
        if (!inService.containsKey(ticket)) { // invalid ticket
            return 0;
        }
        for (Spot spot : inService.get(ticket)) {
            spot.setAvailable(true);
        }
        return charge(ticket);
    }

    /** Calculate charge by start/end time and vehicle type */
    private int charge(Ticket ticket) {
        return 0;
    }
}

class Ticket {
    private String id;
    private String plate;
    private Date startTime;
    private Date endTime;

    public Ticket(String plate) {
        this.id = UUID.randomUUID().toString();
        this.plate = plate;
        this.startTime = new Date();
    }

    public String getPlate() {
        return plate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

class Level {
    private Spot[] small;
    private Spot[] medium;
    private Spot[] large;

    public Level(int space) {
        small = createSpot(space / 4);
        medium = createSpot(space / 2);
        large = createSpot(space / 4);
    }

    public Spot[] allocate(Vehicle vehicle) {
        Spot[][] all;
        if (vehicle.getType() == VehicleType.Motorcycle) {
            all = new Spot[][]{ small, medium, large };
        } else if (vehicle.getType() == VehicleType.Car) {
            all = new Spot[][]{ medium, large };
        } else {
            all = new Spot[][]{ large };
        }

        int size = vehicle.getType().getSize();
        for (Spot[] avail : all) {
            for (int i = 0; i <= avail.length - size;) {
                // Search if consecutive available spots exist
                int j = i;
                while (j < i + size && avail[j].isAvailable()) {
                    j++;
                }

                // Return if so, otherwise start over at next spot (j must be occupied)
                if (j == i + size) {
                    Spot[] spots = new Spot[size];
                    for (int k = 0; k < size; k++, i++) {
                        spots[k] = avail[i];
                        spots[k].setAvailable(false);
                    }
                    System.out.println("Park " + (size == 1 ? "motorcycle" : size == 2 ? "car" : "bus") +
                            " [" + vehicle.getPlate() + "] at " +
                            (avail == small ? "Small" : (avail == medium ? "Medium" : "Large")) +
                            " from " + (j - size) + " to " + (j - 1));
                    return spots;
                }
                i = j + 1;
            }
        }
        System.out.println("Failed to park " +
                (size == 1 ? "motorcycle" : size == 2 ? "car" : "bus") + " [" + vehicle.getPlate() + "]");
        return null;
    }

    private Spot[] createSpot(int space) {
        Spot[] spots = new Spot[space];
        for (int i = 0; i < space; i++) {
            spots[i] = new Spot();
        }
        return spots;
    }
}

class Spot {
    private boolean isAvailable;

    public Spot() {
        this.isAvailable = true;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}

abstract class Vehicle {
    protected String plate;
    protected VehicleType type;

    public Vehicle(String plate, VehicleType type) {
        this.plate = plate;
        this.type = type;
    }

    public String getPlate() {
        return plate;
    }

    public VehicleType getType() {
        return type;
    }
}

enum VehicleType {
    Motorcycle(1), Car(2), Bus(4);

    private final int size;

    VehicleType(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String plate) {
        super(plate, VehicleType.Motorcycle);
    }
}

class Car extends Vehicle {
    public Car(String plate) {
        super(plate, VehicleType.Car);
    }
}

class Bus extends Vehicle {
    public Bus(String plate) {
        super(plate, VehicleType.Bus);
    }
}

