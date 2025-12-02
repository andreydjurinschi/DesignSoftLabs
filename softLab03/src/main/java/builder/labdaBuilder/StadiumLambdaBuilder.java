package builder.labdaBuilder;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import model.Stadium;
import model.additional_components.Capacity;
import model.additional_components.Characteristics;

public class StadiumLambdaBuilder implements LambdaBuilder {

    private static  Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private  String name;
    private  String city;
    private  String owner;
    private  Characteristics characteristics;
    private  Capacity capacity;


    public StadiumLambdaBuilder name(String name){
        this.name = name;
        return this;
    }

    public StadiumLambdaBuilder city(String city){
        this.city = city;
        return this;
    }

    public StadiumLambdaBuilder owner(String owner){
        this.owner = owner;
        return this;
    }

    public StadiumLambdaBuilder characteristics(Characteristics characteristics){
        this.characteristics = characteristics;
        return this;
    }

    public StadiumLambdaBuilder capacity(Capacity capacity){
        this.capacity = capacity;
        return this;
    }

    @Override
    public Stadium build() {
        return null;
    }

/*    @Override
    public Stadium build() {
        return new Stadium(this);
    }*/
}
