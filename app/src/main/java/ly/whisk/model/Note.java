package ly.whisk.model;


import java.io.Serializable;

public class Note implements Serializable {
  /**
   * required: false
   **/
  private String id = null;
  /**
   * required: false
   **/
  private String note = null;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public String getNote() {
    return note;
  }
  public void setNote(String note) {
    this.note = note;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Note {\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("  note: ").append(note).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
