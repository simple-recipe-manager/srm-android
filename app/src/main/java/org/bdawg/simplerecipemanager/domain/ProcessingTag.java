package org.bdawg.simplerecipemanager.domain;

import java.util.UUID;

public class ProcessingTag {
	private UUID id;
	private String tag;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
