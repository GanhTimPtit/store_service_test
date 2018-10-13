package validation.value_in;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class IntegerInValidator implements ConstraintValidator<IntegerIn, Integer> {
    private Set<Integer> values;

    @Override
    public void initialize(IntegerIn constraintAnnotation) {
        int[] intValues = constraintAnnotation.value();
        values = new HashSet<>();
        for (int intValue : intValues) {
            values.add(intValue);
        }
        if(constraintAnnotation.acceptNull()) {
            values.add(null);
        }
    }

    @Override
    public boolean isValid(Integer inputValue, ConstraintValidatorContext context) {
        return values.contains(inputValue);
    }
}
