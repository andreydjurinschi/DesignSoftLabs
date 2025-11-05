package builder;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import model.additional_components.Capacity;
import model.additional_components.Characteristics;
import model.Stadium;

public class StadiumBuilder implements Builder{

    private final Validator validator;

    private  String name;
    private  String city;
    private  String owner;
    private  Characteristics characteristics;
    private Capacity capacity;

    public StadiumBuilder() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

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
        var failure = validator.validate(stadium);
        if(failure.isEmpty() == false){
            StringBuilder errors = new StringBuilder("Errors:\n");
            failure.forEach(
                    f ->
                        errors.append(f.getPropertyPath())
                                .append(":")
                                .append(f.getMessage())
                                .append("\n")
            );
            throw new IllegalStateException(errors.toString());
        }
        return stadium;
    }
}
