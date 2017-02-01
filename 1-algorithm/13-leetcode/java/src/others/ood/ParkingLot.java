package others.ood;

// enum type for Vehicle
enum VehicleSize {
    Motorcycle,
    Compact,
    Large,
}

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

/* Represents a level in a parking garage */
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

    // @param n number of leves
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
