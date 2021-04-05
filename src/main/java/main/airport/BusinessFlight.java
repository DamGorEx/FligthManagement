package main.airport;

public class BusinessFlight extends Flight {
    public BusinessFlight(Long id, int miles) {
        super(id, miles);
    }

    @Override
    public boolean addPassenger(Passenger passenger) {
        if (passenger.isVIP()) {
            return associatePassenger(passenger);
        }
        return false;
    }

    @Override
    public boolean removePassenger(Passenger passenger) {
        return false;
    }
}
