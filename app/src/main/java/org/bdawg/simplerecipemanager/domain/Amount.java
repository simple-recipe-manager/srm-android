package org.bdawg.simplerecipemanager.domain;

public class Amount {

	private double value;
	private UnitTag unit;

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
