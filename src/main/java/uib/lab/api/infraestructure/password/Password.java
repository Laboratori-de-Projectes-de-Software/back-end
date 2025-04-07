package uib.lab.api.infraestructure.password;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=PasswordValidator.class)
@SuppressWarnings("unused")
public @interface Password {
    String message() default "{uib.lab.api.validation.Password.message}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
