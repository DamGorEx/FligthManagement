package main.airport;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Data
@Slf4j
@EqualsAndHashCode(exclude = "passengerSet")
@ToString(exclude = "passengerSet")
public abstract class Flight {
    @Setter(AccessLevel.NONE)
    protected final Set<Passenger> passengerSet = new HashSet<>();
    protected final int miles;
    private final Long id;

    public Flight(Long id, int miles) {
        this.id = id;
        this.miles = miles;
    }

    public abstract boolean addPassenger(Passenger passenger);

    public abstract boolean removePassenger(Passenger passenger);

    protected boolean associatePassenger(Passenger passenger) {
        if (!passenger.getFlights().contains(this)) {
            passengerSet.add(passenger);
            passenger.addFlight(this);
            return true;
        }
        return passengerSet.add(passenger);
    }

    protected boolean disassociatePassenger(Passenger passenger) {
        if (passenger.getFlights().contains(this)) {
            passengerSet.remove(passenger);
            passenger.removeFlight(this);
            return true;
        }
        return passengerSet.remove(passenger);
    }
}
