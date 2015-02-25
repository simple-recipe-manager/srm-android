package ly.whisk.model;


import java.io.Serializable;

public class UnitTag implements Serializable {
  /**
   * required: false
   **/
  private String id = null;
  /**
   * required: false
   **/
  private String tag = null;
  /**
   * required: false
   **/
  private String multipleTag = null;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public String getTag() {
    return tag;
  }
  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getMultipleTag() {
    return multipleTag;
  }
  public void setMultipleTag(String multipleTag) {
    this.multipleTag = multipleTag;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class UnitTag {\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("  tag: ").append(tag).append("\n");
    sb.append("  multipleTag: ").append(multipleTag).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
