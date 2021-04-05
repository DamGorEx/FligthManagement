package main.airport;

public class PremiumFlight extends Flight{

    public PremiumFlight(Long id, int miles) {
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
        if (passenger.isVIP()) {
            return disassociatePassenger(passenger);
        }
        return false;
    }
}
