package org.bdawg.simplerecipemanager.domain;

import java.util.UUID;

public class IngredientAndAmount {
    private UUID id;
    private Ingredient ingredient;
    private double value;
    private UnitTag unit;

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public String getId() {
        return this.id.toString();
    }

    public void setId(String id) {
        this.id = UUID.fromString(id);
    }

    @Override
    public String toString() {
        return this.getId();
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public UnitTag getUnit() {
        return unit;
    }

    public void setUnit(UnitTag unit) {
        this.unit = unit;
    }


}
