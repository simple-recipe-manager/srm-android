package ly.whisk.model;

import java.io.Serializable;

import ly.whisk.model.HACCP;
import ly.whisk.model.Note;


public class Step implements Serializable {
  /**
   * required: false
   **/
  private String id = null;
  /**
   * required: false
   **/
  private String step_details = null;
  /**
   * required: false
   **/
  private HACCP haccp = null;
  /**
   * required: false
   **/
  private Note notes = null;
  /**
   * required: false
   **/
  private Integer order = null;
  
  //public enum orderEnum {  }; 
  
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public String getStep_details() {
    return step_details;
  }
  public void setStep_details(String step_details) {
    this.step_details = step_details;
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

  public Integer getOrder() {
    return order;
  }
  public void setOrder(Integer order) {
    this.order = order;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Step {\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("  step_details: ").append(step_details).append("\n");
    sb.append("  haccp: ").append(haccp).append("\n");
    sb.append("  notes: ").append(notes).append("\n");
    sb.append("  order: ").append(order).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
