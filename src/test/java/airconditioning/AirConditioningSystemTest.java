package airconditioning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("Having air conditioning system")
@ExtendWith(MockitoExtension.class)
class AirConditioningSystemTest {

    @Mock
    Thermometer thermometer;

    @InjectMocks
    AirConditioningSystem airConditioningSystem;

    @DisplayName("When temp higher than threshold")
    @Nested
    class HigherTempTest {
        @DisplayName("Then air conditioning is open")
        @Test
        public void openAirConditioningTest () {
            airConditioningSystem.setTemperatureThreshold(50);
            when(thermometer.getTemperature()).thenReturn(100d);
            airConditioningSystem.checkAirCondSystem();
            assertTrue(airConditioningSystem.isOpen());
        }
    }
    @DisplayName("When temp lower than threshold")
    @Nested
    class LowerTempTest {
        @DisplayName("Then air conditioning is closed")
        @Test
        public void closeAirConditioningTest () {
            airConditioningSystem.setTemperatureThreshold(120d);
            when(thermometer.getTemperature()).thenReturn(100d);
            airConditioningSystem.checkAirCondSystem();
            assertFalse(airConditioningSystem.isOpen());
        }
    }

}