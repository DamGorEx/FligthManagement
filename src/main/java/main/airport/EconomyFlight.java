package main.airport;

public class EconomyFlight extends Flight {

    public EconomyFlight(long id, int miles) {
        super(id, miles);
    }

    @Override
    public boolean addPassenger(Passenger passenger) {
        return associatePassenger(passenger);
    }

    @Override
    public boolean removePassenger(Passenger passenger) {
        if (!passenger.isVIP()) {
            return disassociatePassenger(passenger);
        }
        return false;
    }
}
