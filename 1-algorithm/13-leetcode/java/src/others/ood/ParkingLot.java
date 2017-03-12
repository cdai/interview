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
        parklot.park(new Bus("smithtown"));
    }

    private Level[] levels;
    private Map<Ticket, Spot> inService = new HashMap<>();

    public ParkingLot(int numLevel, int space) {
        levels = new Level[numLevel];
        for (int i = 0; i < numLevel; i++) {
            levels[i] = new Level(space);
        }
    }

    public Ticket park(Vehicle vehicle) {
        for (Level level : levels) {
            Spot spot = level.park(vehicle);
            if (spot != null) {
                inService.put(new Ticket(vehicle.getPlate()), spot);
                break;
            }
        }
        return null;
    }

    /** Unpark car and charge customer accordingly */
    public int unpark(Ticket ticket) {
        if (inService.containsKey(ticket)) {
            Spot spot = inService.get(ticket);
            spot.setAvailable(true);
            return charge(ticket);
        }
        return 0;
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
        small = createSpot(space / 4, VehicleType.Motorcycle);
        medium = createSpot(space / 2, VehicleType.Car);
        large = createSpot(space / 4, VehicleType.Bus);
    }

    public Spot park(Vehicle vehicle) {
        return null;
    }

    private Spot[] createSpot(int space, VehicleType type) {
        Spot[] spots = new Spot[space / type.getSize()];
        for (int i = 0; i < spots.length; i++) {
            spots[i] = new Spot(type, i);
        }
        return spots;
    }
}

class Spot {
    private final VehicleType type;
    private final int index;
    private boolean isAvailable;

    public Spot(VehicleType type, int index) {
        this.type = type;
        this.index = index;
        this.isAvailable = true;
    }

    public VehicleType getType() {
        return type;
    }

    public int getIndex() {
        return index;
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

/*
abstract class Vehicle {
    protected String plate = "";
    public abstract boolean park(Level level);
    public abstract boolean unpark(Level level);
}

class Motorcycle extends Vehicle {
    @Override
    public boolean park(Level level) {
        if (level.allocate(VehicleSize.Motorcycle, plate, 1)) return true;
        if (level.allocate(VehicleSize.Compact, plate, 1)) return true;
        if (level.allocate(VehicleSize.Large, plate, 1)) return true;
        return false;
    }
    @Override
    public boolean unpark(Level level) {
        if (level.free(VehicleSize.Motorcycle, plate, 1)) return true;
        if (level.free(VehicleSize.Compact, plate, 1)) return true;
        if (level.free(VehicleSize.Large, plate, 1)) return true;
        return false;
    }
}

class Car extends Vehicle {
    @Override
    public boolean park(Level level) {
        if (level.allocate(VehicleSize.Compact, plate, 1)) return true;
        if (level.allocate(VehicleSize.Large, plate, 1)) return true;
        return false;
    }
    @Override
    public boolean unpark(Level level) {
        if (level.free(VehicleSize.Compact, plate, 1)) return true;
        if (level.free(VehicleSize.Large, plate, 1)) return true;
        return false;
    }
}

class Bus extends Vehicle {
    @Override
    public boolean park(Level level) {
        return level.allocate(VehicleSize.Large, plate, 5);
    }
    @Override
    public boolean unpark(Level level) {
        return level.free(VehicleSize.Large, plate, 5);
    }
}

class Level {

    private int m, n;
    private String[][] lot;

    Level(int rows, int spots) {
        m = rows;
        n = spots;
        lot = new String[rows][spots];
    }

    boolean allocate(VehicleSize type, String plate, int num) {
        for (int i = 0; i < m; i++) {
            for (int j = start(type); j <= end(type) - num; j++) {
                if (lot[i][j] != null) continue;
                while (num-- > 0) {
                    lot[i][j++] = plate;
                }
                return true;
            }
        }
        return false;
    }

    boolean free(VehicleSize type, String plate, int num) {
        for (int i = 0; i < m; i++) {
            for (int j = end(type) - 1; j >= start(type) + num - 1; j--) {
                if (!plate.equals(lot[i][j])) continue;
                while (num-- > 0) {
                    lot[i][j--] = null;
                }
                return true;
            }
        }
        return false;
    }

    private int start(VehicleSize type) { // inclusive
        if (type == VehicleSize.Motorcycle) return 0;
        else if (type == VehicleSize.Compact) return n / 4;
        else return n / 4 * 3; // ?
    }

    private int end(VehicleSize type) { // exclusive
        if (type == VehicleSize.Motorcycle) return n / 4;
        else if (type == VehicleSize.Compact) return n / 4 * 3;
        else return n;
    }
}

public class ParkingLot {

    public static void main(String[] args) {
        ParkingLot parklot = new ParkingLot(1, 1, 11);
        parklot.parkVehicle(new Motorcycle());
        parklot.parkVehicle(new Car());
        parklot.parkVehicle(new Car());
        parklot.parkVehicle(new Car());
        parklot.parkVehicle(new Car());
        parklot.parkVehicle(new Car());
        parklot.parkVehicle(new Bus());
        parklot.unParkVehicle(new Car());
    }

    private Level[] levels;

    // @param n number of levels
    // @param num_rows  each level has num_rows rows of spots
    // @param spots_per_row each row has spots_per_row spots
    public ParkingLot(int n, int rows, int spots) {
        levels = new Level[n];
        for (int i = 0; i < n; i++) {
            levels[i] = new Level(rows, spots);
        }
    }

    // Park the vehicle in a spot (or multiple spots)
    // Return false if failed
    public boolean parkVehicle(Vehicle vehicle) {
        for (Level level : levels) {
            if (vehicle.park(level)) return true;
        }
        return false;
    }

    // unPark the vehicle
    public void unParkVehicle(Vehicle vehicle) {
        for (Level level : levels) {
            if (vehicle.unpark(level)) break;
        }
    }
}
*/
