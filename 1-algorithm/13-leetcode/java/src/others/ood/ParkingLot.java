package others.ood;

// enum type for Vehicle
enum VehicleSize {
    Motorcycle,
    Compact,
    Large,
}

abstract class Vehicle {
    abstract boolean park(Level level);
    abstract boolean unpark(Level level);
}

class Motorcycle extends Vehicle {
    @Override
    public boolean park(Level level) {
        if (level.allocate(VehicleSize.Motorcycle, 1)) return true;
        if (level.allocate(VehicleSize.Compact, 1)) return true;
        if (level.allocate(VehicleSize.Large, 1)) return true;
        return false;
    }
    @Override
    public boolean unpark(Level level) {
        if (level.free(VehicleSize.Motorcycle, 1)) return true;
        if (level.free(VehicleSize.Compact, 1)) return true;
        if (level.free(VehicleSize.Large, 1)) return true;
        return false;
    }
}

class Car extends Vehicle {
    @Override
    public boolean park(Level level) {
        if (level.allocate(VehicleSize.Compact, 1)) return true;
        if (level.allocate(VehicleSize.Large, 1)) return true;
        return false;
    }
    @Override
    public boolean unpark(Level level) {
        if (level.free(VehicleSize.Compact, 1)) return true;
        if (level.free(VehicleSize.Large, 1)) return true;
        return false;
    }
}

class Bus extends Vehicle {
    @Override
    public boolean park(Level level) {
        return level.allocate(VehicleSize.Large, 5);
    }
    @Override
    public boolean unpark(Level level) {
        return level.free(VehicleSize.Large, 5);
    }
}

/* Represents a level in a parking garage */
class Level {

    private int m, n;
    private boolean[][] lot;

    Level(int rows, int spots) {
        m = rows;
        n = spots;
        lot = new boolean[rows][spots];
    }

    boolean allocate(VehicleSize type, int num) {
        for (int i = 0; i < m; i++) {
            for (int j = start(type); j <= end(type) - num; j++) {
                if (lot[i][j]) continue;
                while (num-- > 0) {
                    lot[i][j++] = true;
                }
                return true;
            }
        }
        return false;
    }

    boolean free(VehicleSize type, int num) {
        for (int i = 0; i < m; i++) {
            for (int j = end(type) - 1; j >= start(type) + num - 1; j--) {
                if (!lot[i][j]) continue;
                while (num-- > 0) {
                    lot[i][j--] = false;
                }
                return true;
            }
        }
        return false;
    }

    private int start(VehicleSize type) { // inclusive
        if (type == VehicleSize.Motorcycle) return 0;
        else if (type == VehicleSize.Compact) return n / 4;
        else return n / 3 * 4;
    }

    private int end(VehicleSize type) { // exclusive
        if (type == VehicleSize.Motorcycle) return n / 4;
        else if (type == VehicleSize.Compact) return n / 3 * 4;
        else return n;
    }
}

public class ParkingLot {

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
