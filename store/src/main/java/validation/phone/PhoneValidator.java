package validation.phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    private String phoneRegex;
    private boolean nullale;

    @Override
    public void initialize(Phone constraintAnnotation) {
        phoneRegex = "[0-9]{10,11}";
        nullale = constraintAnnotation.nullable();
    }

    @Override
    public boolean isValid(String inputValue, ConstraintValidatorContext context) {
        return (nullale && inputValue == null) || inputValue.matches(phoneRegex);
    }
}
