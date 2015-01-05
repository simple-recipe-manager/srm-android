package org.bdawg.simplerecipemanager.domain;

public class OvenTemp {
	private TemperatureUnit unit;
	private long amount;

	public TemperatureUnit getUnit() {
		return unit;
	}

	public void setUnit(TemperatureUnit unit) {
		this.unit = unit;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

}
