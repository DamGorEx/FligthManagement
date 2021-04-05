package airconditioning;

import lombok.Setter;

public class AirConditioningSystem {
    @Setter
    private Thermometer thermometer;
    @Setter
    private double temperatureThreshold;
    private boolean open;


    public boolean isOpen() {
        return open;
    }

    public void checkAirCondSystem() {
        open = temperatureThreshold < thermometer.getTemperature();
    }
}
