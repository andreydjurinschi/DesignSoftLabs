package builder.fluentBuilder;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import model.Stadium;
import model.additional_components.Capacity;
import model.additional_components.Characteristics;

public class StadiumFluentBuilder implements FluentBuilder{

    private final Validator validator;
    private  String name;
    private  String city;
    private  String owner;
    private  Characteristics characteristics;
    private Capacity capacity;

    public StadiumFluentBuilder() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public StadiumFluentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public StadiumFluentBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    @Override
    public StadiumFluentBuilder setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    @Override
    public StadiumFluentBuilder setCharacteristics(Characteristics characteristics) {
        this.characteristics = characteristics;
        return this;
    }

    @Override
    public StadiumFluentBuilder setCapacity(Capacity capacity) {
        this.capacity = capacity;
        return this;
    }

    public Stadium build(){
        return new Stadium(name, city, owner, characteristics, capacity);
    }


}
