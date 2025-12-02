package builder.fluentBuilder;

import model.additional_components.Capacity;
import model.additional_components.Characteristics;

public interface FluentBuilder {
    StadiumFluentBuilder setName(String name);
    StadiumFluentBuilder setCity(String city);
    StadiumFluentBuilder setOwner(String owner);
    StadiumFluentBuilder setCharacteristics(Characteristics characteristics);
    StadiumFluentBuilder setCapacity(Capacity capacity);
}
