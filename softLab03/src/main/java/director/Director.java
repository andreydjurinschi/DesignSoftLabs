package director;

import builder.StadiumBuilder;
import model.additional_components.Capacity;
import model.additional_components.Characteristics;
import model.additional_components.FieldCategory;
import model.additional_components.GrassType;

public class Director {
    public void constructStadiumByBudget
            (StadiumBuilder builder, String name, String city, String owner, double budget)
    {
        builder.setName(name);
        builder.setCity(city);
        builder.setOwner(owner);

        if(budget < 5000.0 && budget > 2500.0){
            builder.setCapacity(new Capacity(
                    3000
            ));
            builder.setCharacteristics(new Characteristics(
                  100, 64, new double[]{1.0, 2.5}, GrassType.ARTIFICIAL, FieldCategory.AMATEUR
            ));
        } else if (budget > 5000 && budget < 15000) {
            builder.setCapacity(new Capacity(
                    15000
            ));
            builder.setCharacteristics(new Characteristics(
                    105, 70, new double[]{4.0, 4.0}, GrassType.NATURAL, FieldCategory.ELITE
            ));
        } else if (budget > 15000) {
            builder.setCapacity(new Capacity(
                    50000
            ));
            builder.setCharacteristics(new Characteristics(
                    110, 75, new double[]{7.0, 7.0}, GrassType.HYBRID, FieldCategory.FIFA
            ));
        }else{
            throw new IllegalStateException("low budget");
        }
    }
}
