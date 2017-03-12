package others.ood;

import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 */
public class AirportScheduler {

    private Airport airport = new Airport(4);

    public void add(Airplane airplane) {
    }
}

class Airport {
    private Queue<Runway> runways;

    public Airport(int numOfRunway) {
        runways = new PriorityQueue<>(Comparator.comparingInt(Runway::pending));
    }

    public void addToRunwayTask(Airplane airplane) {
        Runway runway = runways.poll();
        runway.pend(airplane);
        runways.offer(runway);
    }

    public void removeFromRunwayTask(Runway runway) {
    }
}

class Runway {
    private Queue<Airplane> departPending;
    private Queue<Airplane> arrivePending;

    public Runway() {
        departPending = new LinkedList<>();
        arrivePending = new LinkedList<>();
    }

    public void pend(Airplane airplane) {
        if (airplane.isDepart()) {
            departPending.offer(airplane);
        } else {
            arrivePending.offer(airplane);
        }
        airplane.pend();
    }

    public int pending() {
        return departPending.size() + arrivePending.size();
    }
}

enum AirplaneType {
    BOEING_747, BOEING_787
}

enum AirplaneState {
    IDLE, ASSIGNED, DEPART_PENDING, DEPART, ARRIVE_PENDING, ARRIVE
}

class Airplane {
    private AirplaneType type;
    private AirplaneState state;
    private Flight flight;

    public Airplane(AirplaneType type) {
        this.type = type;
        this.state = AirplaneState.IDLE;
    }

    public void assignFlight(Flight flight) {
        this.flight = flight;
        state = AirplaneState.ASSIGNED;
    }

    public void complete() {
        if (isDepart()) {
            state = AirplaneState.DEPART;
        } else {
            state = AirplaneState.ARRIVE;
        }
    }

    public void pend() {
        if (isDepart()) {
            state = AirplaneState.DEPART_PENDING;
        } else {
            state = AirplaneState.ARRIVE_PENDING;
        }
    }

    public boolean isDepart() {
        return flight != null && flight.getDepartTime().after(new Date());
    }

    public AirplaneState getState() {
        return state;
    }

}

class Flight {
    private String flightNo;
    private Date departTime;
    private Date arriveTime;

    public Flight(String flightNo, Date departTime, Date arriveTime) {
        this.flightNo = flightNo;
        this.departTime = departTime;
        this.arriveTime = arriveTime;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public Date getArriveTime() {
        return arriveTime;
    }
}
