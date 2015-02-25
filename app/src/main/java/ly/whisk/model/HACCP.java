package ly.whisk.model;


import java.io.Serializable;

public class HACCP implements Serializable {
  /**
   * required: false
   **/
  private String critical_control_point = null;
  /**
   * required: false
   **/
  private String control_point = null;
  
  public String getCritical_control_point() {
    return critical_control_point;
  }
  public void setCritical_control_point(String critical_control_point) {
    this.critical_control_point = critical_control_point;
  }

  public String getControl_point() {
    return control_point;
  }
  public void setControl_point(String control_point) {
    this.control_point = control_point;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class HACCP {\n");
    sb.append("  critical_control_point: ").append(critical_control_point).append("\n");
    sb.append("  control_point: ").append(control_point).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
