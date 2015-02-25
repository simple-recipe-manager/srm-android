package ly.whisk.model;

import ly.whisk.model.Note;
import ly.whisk.model.Author;

import java.io.Serializable;
import java.util.*;


public class SourceBook implements Serializable {
  /**
   * required: false
   **/
  private String id = null;
  /**
   * required: false
   **/
  private List<Author> authors = new ArrayList<Author>() ;
  /**
   * required: false
   **/
  private String title = null;
  /**
   * required: false
   **/
  private String ISBN = null;
  /**
   * required: false
   **/
  private Note notes = null;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public List<Author> getAuthors() {
    return authors;
  }
  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }

  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  public String getISBN() {
    return ISBN;
  }
  public void setISBN(String ISBN) {
    this.ISBN = ISBN;
  }

  public Note getNotes() {
    return notes;
  }
  public void setNotes(Note notes) {
    this.notes = notes;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class SourceBook {\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("  authors: ").append(authors).append("\n");
    sb.append("  title: ").append(title).append("\n");
    sb.append("  ISBN: ").append(ISBN).append("\n");
    sb.append("  notes: ").append(notes).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
