package edu.upc.eetac.dsa;

import java.util.Date;

public class Recipe {
  private String title;
  private Date creationDate;
  private String author;
  private int difficulty;
  private String preparation;
  private String[] ingredients;

  public Recipe(){

  }

  public Recipe (String title, String author){
    this.title = title;
    this.author = author;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public int getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  public String getPreparation() {
    return preparation;
  }

  public void setPreparation(String preparation) {
    this.preparation = preparation;
  }

  public String[] getIngredients() {
    return ingredients;
  }

  public void setIngredients(String[] ingredients) {
    this.ingredients = ingredients;
  }

} 
