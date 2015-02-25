package ly.whisk.model;


import java.io.Serializable;

public class TemperatureUnit implements Serializable {
  /**
   * required: false
   **/
  private String value = null;
  
  public enum valueEnum {  C,  F,  };
  
  
  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class TemperatureUnit {\n");
    sb.append("  value: ").append(value).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
