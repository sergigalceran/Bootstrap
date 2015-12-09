package edu.upc.eetac.dsa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.upc.eetac.dsa.Recipe;

public enum RecipeDao {
  instance;

  private Map<String, Recipe> contentProvider = new HashMap<String, Recipe>();
  
  private RecipeDao() {
    
    Recipe recipe = new Recipe("Tortilla de Patatas", "Roc");
    String[] ingredients = {"Huevo", "Patatas", "Aceite"};
    recipe.setIngredients(ingredients);
    recipe.setCreationDate(new Date());
    recipe.setDifficulty(1);
    recipe.setPreparation("Todo a la sarten y luego a darle la vuelta");

    contentProvider.put("Tortilla de Patatas", recipe);

    recipe = new Recipe("Pizza de Callos", "Juan");
    ingredients = new String[] {"Pizza de Casa Tarradellas", "Callos Litoral"};
    recipe.setIngredients(ingredients);
    recipe.setCreationDate(new Date());
    recipe.setDifficulty(3);
    recipe.setPreparation("Vertemos los callos encima de la pizza y al horno");

    contentProvider.put("Pizza de Callos", recipe);

  }
  public Map<String, Recipe> getModel(){
    return contentProvider;
  }
  
} 
