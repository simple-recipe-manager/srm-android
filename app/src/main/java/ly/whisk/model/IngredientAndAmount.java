package ly.whisk.model;

import java.io.Serializable;

import ly.whisk.model.UnitTag;
import ly.whisk.model.Ingredient;


public class IngredientAndAmount implements Serializable {
  /**
   * required: false
   **/
  private String id = null;
  /**
   * required: false
   **/
  private Ingredient ingredient = null;
  /**
   * required: false
   **/
  private Double value = null;
  
  //public enum valueEnum {  }; 
  
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

  public Ingredient getIngredient() {
    return ingredient;
  }
  public void setIngredient(Ingredient ingredient) {
    this.ingredient = ingredient;
  }

  public Double getValue() {
    return value;
  }
  public void setValue(Double value) {
    this.value = value;
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
    sb.append("class IngredientAndAmount {\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("  ingredient: ").append(ingredient).append("\n");
    sb.append("  value: ").append(value).append("\n");
    sb.append("  unit: ").append(unit).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
