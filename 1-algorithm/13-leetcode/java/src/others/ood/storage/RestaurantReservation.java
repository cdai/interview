package others.ood.storage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
public class RestaurantReservation {

    private Map<Integer, Restaurant> restaurants = new HashMap<>();

    public boolean reserve(Reservation resv) {
        if (!restaurants.containsKey(resv.getRestaurant())) {
            return false;
        }
        return restaurants.get(resv.getRestaurant()).reserve(resv.getPeople(), resv.getTimeslot());
    }

}

class Reservation {
    private int restaurant;
    private int people;
    private int timeslot; /* 0~47 represents 30 min of a day*/

    public int getRestaurant() {
        return restaurant;
    }

    public int getPeople() {
        return people;
    }

    public int getTimeslot() {
        return timeslot;
    }
}

class Restaurant {
    private Table[][] tables;

    public boolean reserve(int people, int timeslot) {
        return true;
    }
}

abstract class Table {
    private List<Seat> seats;
}

class Seat {
    private boolean isAvaiable;
}
