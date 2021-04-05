package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PhoneAndCountryCodeBothExistValidatorTest {

    @Test
    public void passangerValidationTest() {
        var validator = new PhoneAndCountryCodeBothExistValidator();
        var passenger1 = new Passenger(1l, "a", "a", null, null, false);
        assertTrue(validator.isValid(passenger1, null));
        var passenger2 = new Passenger(1l, "a", "a", null, 123, false);
        assertFalse(validator.isValid(passenger2, null));
        var passenger3 = new Passenger(1l, "a", "a", 123, null, false);
        assertFalse(validator.isValid(passenger3, null));
        var passenger4 = new Passenger(1l, "a", "a", 123, 123, false);
        assertTrue(validator.isValid(passenger4, null));
    }
}