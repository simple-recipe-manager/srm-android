package org.bdawg.simplerecipemanager.domain;

import java.io.Serializable;

public class OvenTemp implements Serializable {
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
