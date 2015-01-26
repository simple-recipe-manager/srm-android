package org.bdawg.simplerecipemanager.domain;

import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.bdawg.simplerecipemanager.domain.Author;
import org.bdawg.simplerecipemanager.domain.IngredientAndAmount;
import org.bdawg.simplerecipemanager.domain.Note;
import org.bdawg.simplerecipemanager.domain.OvenFan;
import org.bdawg.simplerecipemanager.domain.OvenTemp;
import org.bdawg.simplerecipemanager.domain.SourceBook;
import org.bdawg.simplerecipemanager.domain.Step;
import org.bdawg.simplerecipemanager.domain.Yield;

public class Recipe implements Serializable{

    private UUID id;
    private String recipe_name;
    private OvenFan oven_fan; // Off, Low, High
    private OvenTemp oven_temp;
    private long oven_time;
    private Note notes;
    private SourceBook source_book;
    private Set<Author> source_authors;
    private URL source_url;
    private Set<Step> steps;
    private Set<URL> imageURLs;
    private URL defaultImageURL;
    private Map<String, Set<IngredientAndAmount>> ingredients;
    private Set<Yield> yields;
    private long addedAt;

    public Recipe() {
    }

    public String getId() {
        return id.toString();
    }

    public void setId(String id) {
        this.id = UUID.fromString(id);
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

    public long getOven_time() {
        return oven_time;
    }

    public void setOven_time(long oven_time) {
        this.oven_time = oven_time;
    }

    public SourceBook getSource_book() {
        return source_book;
    }

    public void setSource_book(SourceBook source_book) {
        this.source_book = source_book;
    }

    public Set<URL> getImageURLs() {
        return imageURLs;
    }

    public void setImageURLs(Set<URL> imageURLs) {
        this.imageURLs = imageURLs;
    }

    public URL getDefaultImageURL() {
        return defaultImageURL;
    }

    public void setDefaultImageURL(URL defaultImageURL) {
        this.defaultImageURL = defaultImageURL;
    }

    public OvenTemp getOven_temp() {
        return oven_temp;
    }

    public void setOven_temp(OvenTemp oven_temp) {
        this.oven_temp = oven_temp;
    }

    public Set<Author> getSource_authors() {
        return source_authors;
    }

    public void setSource_authors(Set<Author> source_authors) {
        this.source_authors = source_authors;
    }

    public URL getSource_url() {
        return source_url;
    }

    public void setSource_url(URL source_url) {
        this.source_url = source_url;
    }

    public void setSteps(Set<Step> steps) {
        this.steps = steps;
    }

    public Set<Step> getSteps() {
        return steps;
    }

    public Note getNotes() {
        return notes;
    }

    public void setNotes(Note notes) {
        this.notes = notes;
    }

    public void setIngredients(Map<String, Set<IngredientAndAmount>> ingredients){
        this.ingredients = ingredients;
    }

    public Map<String, Set<IngredientAndAmount>> getIngredients(){
        return this.ingredients;
    }

    public Set<Yield> getYields(){
        return this.yields;
    }

    public void setYields(Set<Yield> yields){
        this.yields = yields;
    }

    public long getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(long addedAt) {
        this.addedAt = addedAt;
    }
}
