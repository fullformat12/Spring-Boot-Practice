package bimatlaptrinh.com.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotAdminNameValidator.class)
public @interface NotAdminName {
    String message() default "Name cannot be 'Admin'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}