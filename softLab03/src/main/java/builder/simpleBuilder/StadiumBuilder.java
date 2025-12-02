package builder.simpleBuilder;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import model.additional_components.Capacity;
import model.additional_components.Characteristics;
import model.Stadium;
import validator.FieldValidator;

import java.util.List;

public class StadiumBuilder implements Builder{

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private  String name;
    private  String city;
    private  String owner;
    private  Characteristics characteristics;
    private Capacity capacity;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public void setCharacteristics(Characteristics characteristics) {
        this.characteristics = characteristics;
    }

    @Override
    public void setCapacity(Capacity capacity) {
        this.capacity = capacity;
    }

    public Stadium getResult(){
        var stadium = new Stadium(name, city, owner, characteristics, capacity);
        List<String> errors = FieldValidator.check(stadium);
        errors.addAll(FieldValidator.check(stadium.getCapacity()));
        errors.addAll(FieldValidator.check(stadium.getCharacteristics()));
        if(!errors.isEmpty()){
            errors.forEach(er -> {
                System.out.println(er);
            });
            throw new IllegalStateException("ERRORS");
        }
        return stadium;
    }

    public static Stadium getResult(String name, String city, String owner, Characteristics characteristics, Capacity capacity){
        Stadium st = new Stadium(name, city, owner, characteristics, capacity);
        var failure = validator.validate(st);
        if(!failure.isEmpty()){
            StringBuilder errors = new StringBuilder("Errors:\n");
            for(var data : failure){
                errors.append(data.getPropertyPath())
                        .append(":")
                        .append(data.getMessage())
                        .append("\n");
            }
            throw new IllegalStateException(errors.toString());
        }
        return st;
    }

}
