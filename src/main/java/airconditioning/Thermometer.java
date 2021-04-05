package airconditioning;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class Thermometer {
    @Setter
    private double temperature;
    @Getter @Setter
    private Sensor sensor;

    private static String ERROR_WHEN_BLOCKED = "Sensor is blocked";

    public Thermometer(Sensor sensor) {
        this.sensor = sensor;
    }

    public double getTemperature() {
        if (!sensor.isBlocked()) {
            return temperature;
        }
        throw new RuntimeException(ERROR_WHEN_BLOCKED);
    }
}
