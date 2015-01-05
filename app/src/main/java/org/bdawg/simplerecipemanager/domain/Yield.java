package org.bdawg.simplerecipemanager.domain;

public class Yield {
	private long serves;
	private UnitTag unit;

	public Yield() {

	}

	public long getServes() {
		return serves;
	}

	public void setServes(long serves) {
		this.serves = serves;
	}

	public UnitTag getUnit() {
		return unit;
	}

	public void setUnit(UnitTag unit) {
		this.unit = unit;
	}
}
