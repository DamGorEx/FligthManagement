package main;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneAndCountryCodeBothExistValidator implements ConstraintValidator<ValidPhoneAndCountryCode, Passenger> {
    @Override
    public boolean isValid(Passenger passenger, ConstraintValidatorContext constraintValidatorContext) {
        if (passenger.getPhoneNumber() == null && passenger.getCountryCode() != null) {
            return false;
        } else if (passenger.getPhoneNumber() != null && passenger.getCountryCode() == null) {
            return false;
        }
        return true;
    }
}
