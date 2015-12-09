package edu.upc.eetac.dsa;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/recipe")
public class RecipeResource {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecipe() {
        Recipe recipe = new Recipe("Test Recipe","Test Author");
        return Response.ok(recipe).build();
    }

    
    @GET @Path("/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecipeByTitle(@PathParam("title") String title) {
        Recipe recipe = RecipeDao.instance.getModel().get(title);
        Response response;
        
        if(recipe==null) {
        	response = Response.status(Response.Status.NOT_FOUND).build();
            
        } else {
        	response = Response.ok(recipe).build();
        }
        return response;
    }
    

    @GET @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodos() {
    	List<Recipe> values = new ArrayList<Recipe>();
        values.addAll(RecipeDao.instance.getModel().values());
        RecipeList recipes = new RecipeList();
        recipes.setRecipes(values);

        return Response.ok(recipes).build();
    }

    @POST 
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newRecipeInJSON(Recipe newrecipe) {

		if (newrecipe.getTitle() == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (newrecipe.getAuthor() == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		Recipe recipe = RecipeDao.instance.getModel().get(newrecipe.getTitle());
		if (recipe != null) {
			return Response.status(Response.Status.CONFLICT).build();
		}

		RecipeDao.instance.getModel().put(newrecipe.getTitle(), newrecipe);

		return Response.ok(newrecipe).build();

    }
    
    @PUT  @Path("/{title}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRecipeInJSON(@PathParam("title") String title, Recipe newrecipe) {
        if (newrecipe.getTitle()==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (newrecipe.getAuthor()==null){
           return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if(!title.equals(newrecipe.getTitle())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Recipe recipe = RecipeDao.instance.getModel().get(title);
        if(recipe==null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        RecipeDao.instance.getModel().put(newrecipe.getTitle(), newrecipe);

        return Response.ok(newrecipe).build();

    }

	@DELETE
	@Path("/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTodoById(@PathParam("title") String title) {
		Recipe recipe = RecipeDao.instance.getModel().get(title);
		if (recipe == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		recipe = RecipeDao.instance.getModel().remove(title);
		return Response.status(Response.Status.ACCEPTED).build();

	}

	@GET @Path("/pagination")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPaginationList(@QueryParam("page") int page) {
		
    	List<Recipe> values = new ArrayList<Recipe>();
        values.addAll(RecipeDao.instance.getModel().values());
        int size = values.size();
        
        if (page >= size) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
        if (page < 0) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

        RecipeList recipes = new RecipeList();
        recipes.setRecipe(values.get((page)));
        
        String link = "";
        if (page == 0){
        	link = "</myapp/recipe/pagination?page=" + (page+1) + ">; rel='next'";
        }
        
        if (page == size-1){
        	link = "</myapp/recipe/pagination?page=" + (page-1) + ">; rel='prev'";
        }
        
        if (page < size-1 && page > 0){
        	link = "</myapp/recipe/pagination?page=" + (page-1) + ">; rel='prev'," + "</myapp/recipe/pagination?page=" + (page+1) + ">; rel='next'";
        }
        
        return Response.ok(recipes).header("Link", link).build();
    }
    
}


