package ly.whisk.model;

import ly.whisk.model.Ingredient;
import ly.whisk.model.Note;

import java.io.Serializable;
import java.util.*;
import ly.whisk.model.ProcessingTag;


public class Ingredient implements Serializable {
  /**
   * required: false
   **/
  private String id = null;
  /**
   * required: false
   **/
  private String name = null;
  /**
   * required: false
   **/
  private String usda_num = null;
  /**
   * required: false
   **/
  private List<Ingredient> substitutions = new ArrayList<Ingredient>() ;
  /**
   * required: false
   **/
  private Note note = null;
  /**
   * required: false
   **/
  private List<ProcessingTag> processingTags = new ArrayList<ProcessingTag>() ;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getUsda_num() {
    return usda_num;
  }
  public void setUsda_num(String usda_num) {
    this.usda_num = usda_num;
  }

  public List<Ingredient> getSubstitutions() {
    return substitutions;
  }
  public void setSubstitutions(List<Ingredient> substitutions) {
    this.substitutions = substitutions;
  }

  public Note getNote() {
    return note;
  }
  public void setNote(Note note) {
    this.note = note;
  }

  public List<ProcessingTag> getProcessingTags() {
    return processingTags;
  }
  public void setProcessingTags(List<ProcessingTag> processingTags) {
    this.processingTags = processingTags;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ingredient {\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  usda_num: ").append(usda_num).append("\n");
    sb.append("  substitutions: ").append(substitutions).append("\n");
    sb.append("  note: ").append(note).append("\n");
    sb.append("  processingTags: ").append(processingTags).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
