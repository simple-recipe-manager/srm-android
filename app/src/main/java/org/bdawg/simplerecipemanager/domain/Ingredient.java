package org.bdawg.simplerecipemanager.domain;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public class Ingredient implements Serializable {

	public static final String TABLE_NAME = "Ingredients";

	private UUID ingredientId;
	private String name;
	private Set<Ingredient> substitutions;
	private Set<ProcessingTag> processingTags;
	private Note notes;
	private String usda_num;

	public UUID getId() {
		return ingredientId;
	}

	public void setId(UUID ingredientId) {
		this.ingredientId = ingredientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Ingredient> getSubstitutions() {
		return substitutions;
	}

	public void setSubstitutions(Set<Ingredient> substitutions) {
		this.substitutions = substitutions;
	}

	public Set<ProcessingTag> getProcessingTags() {
		return processingTags;
	}

	public void setProcessingTags(Set<ProcessingTag> processingTags) {
		this.processingTags = processingTags;
	}

	public Note getNotes() {
		return notes;
	}

	public void setNotes(Note notes) {
		this.notes = notes;
	}

	public String getUsda_num() {
		return usda_num;
	}

	public void setUsda_num(String usda_num) {
		this.usda_num = usda_num;
	}

}
