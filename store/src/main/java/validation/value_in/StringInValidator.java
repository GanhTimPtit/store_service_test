package validation.value_in;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class StringInValidator implements ConstraintValidator<StringIn, String> {
    private Set<String> values;

    @Override
    public void initialize(StringIn constraintAnnotation) {
        values = new HashSet<>();
        String[] stringValues = constraintAnnotation.value();
        Collections.addAll(values, stringValues);

        if (constraintAnnotation.acceptNull()) {
            values.add(null);
        }
    }

    @Override
    public boolean isValid(String inputValue, ConstraintValidatorContext context) {
        return values.contains(inputValue);
    }
}
