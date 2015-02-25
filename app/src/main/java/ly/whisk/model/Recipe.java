package ly.whisk.model;

import ly.whisk.model.Note;
import ly.whisk.model.OvenFan;
import ly.whisk.model.Author;
import ly.whisk.model.Yield;

import java.io.Serializable;
import java.util.*;
import ly.whisk.model.Step;
import ly.whisk.model.IngredientAndAmount;
import ly.whisk.model.SourceBook;
import ly.whisk.model.OvenTemp;


public class Recipe implements Serializable {
  /**
   * Unique identifier representing a specific recipe.
   * required: false
   **/
  private String recipe_uuid = null;
  /**
   * Description of product.
   * required: false
   **/
  private String recipe_name = null;
  /**
   * required: false
   **/
  private OvenFan oven_fan = null;
  /**
   * required: false
   **/
  private Long oven_time = null;
  
  //public enum oven_timeEnum {  }; 
  
  /**
   * required: false
   **/
  private OvenTemp oven_temp = null;
  /**
   * required: false
   **/
  private List<Yield> yields = new ArrayList<Yield>() ;
  /**
   * required: false
   **/
  private Note notes = null;
  /**
   * required: false
   **/
  private SourceBook source_book = null;
  /**
   * required: false
   **/
  private List<Author> source_authors = new ArrayList<Author>() ;
  /**
   * required: false
   **/
  private String source_url = null;
  /**
   * required: false
   **/
  private List<Step> steps = new ArrayList<Step>() ;
  /**
   * required: false
   **/
  private List<IngredientAndAmount> ingredients = new ArrayList<IngredientAndAmount>() ;
  /**
   * The time in minutes it takes to prep for a recipe.
   * required: false
   **/
  private Long prep_time = null;
  
  //public enum prep_timeEnum {  }; 
  
  /**
   * The time in minutes for this recipe to cook.
   * required: false
   **/
  private Long cooking_time = null;
  
  //public enum cooking_timeEnum {  }; 
  
  /**
   * The timestamp in milliseconds when this was created relative to the unix epoch.
   * required: false
   **/
  private Long added_at = null;
  
  //public enum added_atEnum {  }; 
  
  /**
   * The header image for a recipe.
   * required: false
   **/
  private String default_image_url = null;
  /**
   * required: false
   **/
  private Boolean is_private = null;
  
  public String getRecipe_uuid() {
    return recipe_uuid;
  }
  public void setRecipe_uuid(String recipe_uuid) {
    this.recipe_uuid = recipe_uuid;
  }

  public String getRecipe_name() {
    return recipe_name;
  }
  public void setRecipe_name(String recipe_name) {
    this.recipe_name = recipe_name;
  }

  public OvenFan getOven_fan() {
    return oven_fan;
  }
  public void setOven_fan(OvenFan oven_fan) {
    this.oven_fan = oven_fan;
  }

  public Long getOven_time() {
    return oven_time;
  }
  public void setOven_time(Long oven_time) {
    this.oven_time = oven_time;
  }

  public OvenTemp getOven_temp() {
    return oven_temp;
  }
  public void setOven_temp(OvenTemp oven_temp) {
    this.oven_temp = oven_temp;
  }

  public List<Yield> getYields() {
    return yields;
  }
  public void setYields(List<Yield> yields) {
    this.yields = yields;
  }

  public Note getNotes() {
    return notes;
  }
  public void setNotes(Note notes) {
    this.notes = notes;
  }

  public SourceBook getSource_book() {
    return source_book;
  }
  public void setSource_book(SourceBook source_book) {
    this.source_book = source_book;
  }

  public List<Author> getSource_authors() {
    return source_authors;
  }
  public void setSource_authors(List<Author> source_authors) {
    this.source_authors = source_authors;
  }

  public String getSource_url() {
    return source_url;
  }
  public void setSource_url(String source_url) {
    this.source_url = source_url;
  }

  public List<Step> getSteps() {
    return steps;
  }
  public void setSteps(List<Step> steps) {
    this.steps = steps;
  }

  public List<IngredientAndAmount> getIngredients() {
    return ingredients;
  }
  public void setIngredients(List<IngredientAndAmount> ingredients) {
    this.ingredients = ingredients;
  }

  public Long getPrep_time() {
    return prep_time;
  }
  public void setPrep_time(Long prep_time) {
    this.prep_time = prep_time;
  }

  public Long getCooking_time() {
    return cooking_time;
  }
  public void setCooking_time(Long cooking_time) {
    this.cooking_time = cooking_time;
  }

  public Long getAdded_at() {
    return added_at;
  }
  public void setAdded_at(Long added_at) {
    this.added_at = added_at;
  }

  public String getDefault_image_url() {
    return default_image_url;
  }
  public void setDefault_image_url(String default_image_url) {
    this.default_image_url = default_image_url;
  }

  public Boolean getIs_private() {
    return is_private;
  }
  public void setIs_private(Boolean is_private) {
    this.is_private = is_private;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Recipe {\n");
    sb.append("  recipe_uuid: ").append(recipe_uuid).append("\n");
    sb.append("  recipe_name: ").append(recipe_name).append("\n");
    sb.append("  oven_fan: ").append(oven_fan).append("\n");
    sb.append("  oven_time: ").append(oven_time).append("\n");
    sb.append("  oven_temp: ").append(oven_temp).append("\n");
    sb.append("  yields: ").append(yields).append("\n");
    sb.append("  notes: ").append(notes).append("\n");
    sb.append("  source_book: ").append(source_book).append("\n");
    sb.append("  source_authors: ").append(source_authors).append("\n");
    sb.append("  source_url: ").append(source_url).append("\n");
    sb.append("  steps: ").append(steps).append("\n");
    sb.append("  ingredients: ").append(ingredients).append("\n");
    sb.append("  prep_time: ").append(prep_time).append("\n");
    sb.append("  cooking_time: ").append(cooking_time).append("\n");
    sb.append("  added_at: ").append(added_at).append("\n");
    sb.append("  default_image_url: ").append(default_image_url).append("\n");
    sb.append("  is_private: ").append(is_private).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
