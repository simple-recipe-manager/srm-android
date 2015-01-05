package org.bdawg.simplerecipemanager.domain;

import java.util.UUID;

public class Note {

	private UUID id;
	private String note;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
