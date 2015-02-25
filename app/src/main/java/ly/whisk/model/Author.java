package ly.whisk.model;


import java.io.Serializable;

public class Author implements Serializable {
  /**
   * required: false
   **/
  private String id = null;
  /**
   * required: false
   **/
  private String name = null;
  
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

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Author {\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
