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
@RequestMapping("/recipes/{id}/ingredients")
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

	@GetMapping("{ing_id}")
	public Ingredient getOne(@PathVariable long ing_id) throws IngredientNotFoundException {
		Ingredient ingr = ingredientRepo.findOne(ing_id);
		if (ingr == null) {
			throw new IngredientNotFoundException();
		}
		return ingr;

	}

	@PostMapping("")
	public Ingredient create(@PathVariable Long recipeId, @RequestBody Ingredient ingredient) {
		Recipe recipe = recipeRepository.findOne(recipeId);
		ingredient.setRecipe(recipe);
		return ingredientRepo.save(ingredient);
	}

	@DeleteMapping("{ing_id}")
 public Ingredient delete(@PathVariable long ing_id) { 
     try {
    	 Ingredient ingr = ingredientRepo.findOne(ing_id);
    	 ingredientRepo.delete(ing_id);
          return ingr;            
     } catch (EmptyResultDataAccessException e) {
          return null;
     }
	}

}