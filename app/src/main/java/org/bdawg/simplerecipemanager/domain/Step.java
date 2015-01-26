package org.bdawg.simplerecipemanager.domain;

import java.io.Serializable;
import java.util.UUID;

public class Step implements Serializable {

	private UUID id;
	private String stepDetails;
	private HACCP haccp;
	private Note notes;
	private int order;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getStepDetails() {
		return stepDetails;
	}

	public void setStepDetails(String stepDetails) {
		this.stepDetails = stepDetails;
	}

	public HACCP getHaccp() {
		return haccp;
	}

	public void setHaccp(HACCP haccp) {
		this.haccp = haccp;
	}

	public Note getNotes() {
		return notes;
	}

	public void setNotes(Note notes) {
		this.notes = notes;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
