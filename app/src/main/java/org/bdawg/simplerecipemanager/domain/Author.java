package org.bdawg.simplerecipemanager.domain;

import java.util.UUID;

public class Author {
	public static final String TABLE_NAME = "Authors";
	private UUID id;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}
