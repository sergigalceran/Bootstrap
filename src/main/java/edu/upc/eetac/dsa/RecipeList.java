package edu.upc.eetac.dsa;

import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RecipeList {
	
	private List<Recipe> recipes;
	
	public RecipeList() {
		super();
		recipes = new ArrayList<>();
	}
	
	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}
	
	public void setRecipe(Recipe recipe) {
		this.recipes.add(recipe);
	}

		
}

