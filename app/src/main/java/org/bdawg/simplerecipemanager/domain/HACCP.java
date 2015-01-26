package org.bdawg.simplerecipemanager.domain;

import java.io.Serializable;

public class HACCP implements Serializable{

	private String critical_control_point;
	private String control_point;

	public String getCritical_control_point() {
		return critical_control_point;
	}

	public void setCritical_control_point(String critical_control_point) {
		this.critical_control_point = critical_control_point;
	}

	public String getControl_point() {
		return control_point;
	}

	public void setControl_point(String control_point) {
		this.control_point = control_point;
	}
}
