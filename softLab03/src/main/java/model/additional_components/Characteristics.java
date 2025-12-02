package model.additional_components;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.*;

import java.util.Arrays;

public final class Characteristics {
    @DecimalMin("100.0")
    @DecimalMax("110.0")
    private final double length;

    @DecimalMin("64.0")
    @DecimalMax("75.0")
    private final double width;

    @Size(min = 2, max = 2)
    private final double[] safeArea;
    @NotNull(message = "grass type field is required")
    private final GrassType grassType;
    @NotNull(message = "category type field is required")
    private final FieldCategory fieldCategory;

    public Characteristics(double length, double width, double[] safeArea, GrassType grassType, FieldCategory fieldCategory) {
        this.length = length;
        this.width = width;
        this.safeArea = safeArea;
        this.grassType = grassType;
        this.fieldCategory = fieldCategory;
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        var failure = validator.validate(this);
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
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double[] getSafeArea() {
        return safeArea;
    }

    public GrassType getGrassType() {
        return grassType;
    }

    public FieldCategory getFieldCategory() {
        return fieldCategory;
    }

    @Override
    public String toString() {
        return "Characteristics{" +
                "length=" + length +
                ", width=" + width +
                ", safeArea=" + Arrays.toString(safeArea) +
                ", grassType=" + grassType +
                ", fieldCategory=" + fieldCategory +
                '}';
    }
}

