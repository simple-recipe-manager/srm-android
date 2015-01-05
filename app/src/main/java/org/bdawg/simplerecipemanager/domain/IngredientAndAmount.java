package org.bdawg.simplerecipemanager.domain;

public class IngredientAndAmount {
	private Ingredient ingredient;
	private Amount amount;

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

}
