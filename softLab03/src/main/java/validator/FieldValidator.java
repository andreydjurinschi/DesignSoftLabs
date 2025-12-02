package validator;

import jakarta.validation.Validation;

import java.util.ArrayList;
import java.util.List;

public class FieldValidator {
    public static List<String> check(Object object){
        var failure = Validation.buildDefaultValidatorFactory().getValidator().validate(object);
        List<String> res = new ArrayList<>();
        if(!failure.isEmpty()){
            StringBuilder errors = new StringBuilder("Errors:\n");
            for(var data : failure){
                errors.append(data.getPropertyPath())
                        .append(":")
                        .append(data.getMessage())
                        .append("\n");
            }
            res.add(errors.toString());
        }
        return res;
    }
}
