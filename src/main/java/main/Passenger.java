package main;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.executable.ValidateOnExecution;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@ValidPhoneAndCountryCode
@EqualsAndHashCode(exclude="flights")
@ToString(exclude="flights")
public class Passenger {
    private final Long id;
    private final String name, surname;

    private final Integer phoneNumber;
    private final Integer countryCode;
    private final Boolean isVIP;
    private final Set<Flight> flights = new HashSet<>();
    private static final Map<Boolean, Integer> pointDividerValueMap;

    static {
        pointDividerValueMap = new HashMap<>();
        pointDividerValueMap.put(false, 20);
        pointDividerValueMap.put(true, 10);
    }


    public Passenger(Long id, String name, String surname, Integer phoneNumber, Integer countryCode, boolean isVIP) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.countryCode = countryCode;
        this.isVIP = isVIP;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public int getPoints() {
        return
                flights.stream()
                        .reduce(0, (i, f) -> i + f.getMiles(), Integer::compareTo)
                / pointDividerValueMap.get(this.isVIP);
    }

    public boolean addFlight(Flight flight) {
        if (!flight.getPassengerSet().contains(this)) {
            if (flight.addPassenger(this)) {
                flights.add(flight);
                return true;
            }
            return false;
        }
        return flights.add(flight);
    }

    public boolean removeFlight(Flight flight) {
        if (flight.getPassengerSet().contains(this)) {
            if (flight.removePassenger(this)) {
                flights.remove(flight);
                return true;
            }
            return false;
        }
        return flights.remove(flight);
    }
}
