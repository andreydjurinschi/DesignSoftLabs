package model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import model.additional_components.Capacity;
import model.additional_components.Characteristics;


public final class Stadium {
    @NotBlank(message = "stadium name field is required")
    @Size(min = 2, max = 50)
    private final String name;
    @NotBlank(message = "stadium city field is required")
    private final String city;
    @NotBlank(message = "stadium owner field is required")
    private final String owner;
    @NotNull(message = "stadium characteristics are required")
    private final Characteristics characteristics;
    @NotNull
    private final Capacity capacity;

    public Stadium(String name, String city, String owner, Characteristics characteristics, Capacity capacity) {
        this.name = name;
        this.city = city;
        this.owner = owner;
        this.characteristics = characteristics;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getOwner() {
        return owner;
    }

    public Characteristics getCharacteristics() {
        return characteristics;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Stadium{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", owner='" + owner + '\'' +
                ",\ncharacteristics=" + this.getCharacteristics().toString() +
                ",\ncapacity=" + this.getCapacity().toString() +
                '}';
    }
}
