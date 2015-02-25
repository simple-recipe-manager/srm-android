package ly.whisk.model;

import java.io.Serializable;

import ly.whisk.model.TemperatureUnit;


public class OvenTemp implements Serializable {
  /**
   * required: false
   **/
  private Long value = null;
  
  //public enum valueEnum {  }; 
  
  /**
   * required: false
   **/
  private TemperatureUnit unit = null;
  
  public Long getValue() {
    return value;
  }
  public void setValue(Long value) {
    this.value = value;
  }

  public TemperatureUnit getUnit() {
    return unit;
  }
  public void setUnit(TemperatureUnit unit) {
    this.unit = unit;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class OvenTemp {\n");
    sb.append("  value: ").append(value).append("\n");
    sb.append("  unit: ").append(unit).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
