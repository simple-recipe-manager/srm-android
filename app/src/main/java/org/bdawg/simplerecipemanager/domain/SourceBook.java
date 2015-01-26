package org.bdawg.simplerecipemanager.domain;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class SourceBook implements Serializable {

	public static final String TABLE_NAME = "Sources";

	private UUID id;
	private List<Author> authors;
	private String title;
	private String ISBN;
	private Note notes;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public Note getNotes() {
		return notes;
	}

	public void setNotes(Note notes) {
		this.notes = notes;
	}
}
