package ly.whisk.model;

import java.io.Serializable;

import ly.whisk.model.UnitTag;


public class Yield implements Serializable {
  /**
   * required: false
   **/
  private String id = null;
  /**
   * required: false
   **/
  private Long serves = null;
  
  //public enum servesEnum {  }; 
  
  /**
   * required: false
   **/
  private UnitTag unit = null;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public Long getServes() {
    return serves;
  }
  public void setServes(Long serves) {
    this.serves = serves;
  }

  public UnitTag getUnit() {
    return unit;
  }
  public void setUnit(UnitTag unit) {
    this.unit = unit;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Yield {\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("  serves: ").append(serves).append("\n");
    sb.append("  unit: ").append(unit).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
