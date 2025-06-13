package bimatlaptrinh.com.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotAdminNameValidator implements ConstraintValidator<NotAdminName, String> {
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !value.equalsIgnoreCase("admin");
    }
}