package airconditioning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Given there is a thermometer")
@ExtendWith(MockitoExtension.class)
class ThermometerTest {
    @Mock
    private Sensor sensor;

    @InjectMocks
    private Thermometer thermometer;

    @BeforeEach
    public void init () {

    }

    @DisplayName("When sensor is blocked")
    @Nested
    class SensorBlockedTest {
        @DisplayName("Then thermometer asked for temperature returns error")
        @Test
        public void thermometerSensorBlockedTest () {
            thermometer.setTemperature(20d);
            when(sensor.isBlocked()).thenReturn(true);
            assertThrows(RuntimeException.class, () -> thermometer.getTemperature(),"Sensor is blocked");
        }
    }
    @DisplayName("When sensor is not blocked")
    @Nested
    class SensorNotBlockedTest {
        @DisplayName("Then thermometer returns correct temperature")
        @Test
        public void thermometerSensorBlockedTest () {
            thermometer.setTemperature(20d);
            when(sensor.isBlocked()).thenReturn(false);
            assertEquals(20d, thermometer.getTemperature());
        }
    }
}