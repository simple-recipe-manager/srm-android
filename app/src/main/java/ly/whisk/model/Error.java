package ly.whisk.model;


import java.io.Serializable;

public class Error implements Serializable {
  /**
   * required: false
   **/
  private Integer code = null;
  
  //public enum codeEnum {  }; 
  
  /**
   * required: false
   **/
  private String message = null;
  /**
   * required: false
   **/
  private String fields = null;
  
  public Integer getCode() {
    return code;
  }
  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }

  public String getFields() {
    return fields;
  }
  public void setFields(String fields) {
    this.fields = fields;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    sb.append("  code: ").append(code).append("\n");
    sb.append("  message: ").append(message).append("\n");
    sb.append("  fields: ").append(fields).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
