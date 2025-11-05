package builder;

import model.additional_components.Capacity;
import model.additional_components.Characteristics;

/**
 * builder interface
 */
public interface Builder {
    void setName(String name);
    void setCity(String city);
    void setOwner(String owner);
    void setCharacteristics(Characteristics characteristics);
    void setCapacity(Capacity capacity);
}
