package com.liberymutual.goforcode.recipe.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liberymutual.goforcode.recipe.models.Ingredient;
import com.liberymutual.goforcode.recipe.models.Recipe;
import com.liberymutual.goforcode.recipe.repositories.IngredientRepository;
import com.liberymutual.goforcode.recipe.repositories.RecipeRepository;

@RestController
@RequestMapping("/recipes/{recipeId}/ingredients")
public class IngredientApiController {

	private IngredientRepository ingredientRepo;
	private RecipeRepository recipeRepository;

	public IngredientApiController(IngredientRepository ingredientRepo, RecipeRepository recipeRepository) {
		this.ingredientRepo = ingredientRepo;
		this.recipeRepository = recipeRepository; 
	}

	@GetMapping("") 
	public List<Ingredient> getAll() {
		return ingredientRepo.findAll(); 
	}

	@GetMapping("{ingredient_id}") 
	public Ingredient getOne(@PathVariable long ingredientId) throws IngredientNotFoundException {
		Ingredient ingredient = ingredientRepo.findOne(ingredientId); 
		if (ingredient == null) {
			throw new IngredientNotFoundException();
		}
		return ingredient;

	}

	@PostMapping("")
	public Ingredient create(@PathVariable Long recipeId, @RequestBody Ingredient ingredient) {
		Recipe recipe = recipeRepository.findOne(recipeId);
		ingredient.setRecipe(recipe);
		return ingredientRepo.save(ingredient);
	}

	@DeleteMapping("{ingredientId}")
 public Ingredient delete(@PathVariable long ingredientId) { 
     try {
    	 Ingredient ingredient = ingredientRepo.findOne(ingredientId);
    	 ingredientRepo.delete(ingredientId);
          return ingredient;            
     } catch (EmptyResultDataAccessException e) {
          return null;
     }
	}

}