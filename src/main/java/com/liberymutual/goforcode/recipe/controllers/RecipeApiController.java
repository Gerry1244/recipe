package com.liberymutual.goforcode.recipe.controllers;

import java.util.Arrays;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.liberymutual.goforcode.recipe.models.Ingredient;
import com.liberymutual.goforcode.recipe.models.Instruction;
import com.liberymutual.goforcode.recipe.models.Recipe;
import com.liberymutual.goforcode.recipe.repositories.IngredientRepository;
import com.liberymutual.goforcode.recipe.repositories.InstructionRepository;
import com.liberymutual.goforcode.recipe.repositories.RecipeRepository;

@RestController
@RequestMapping("/recipes")

public class RecipeApiController { 
	private RecipeRepository recipeRepo;
	private IngredientRepository ingredientRepo;
	private InstructionRepository instructionRepo;
 
	public RecipeApiController(RecipeRepository recipeRepo, IngredientRepository ingredientRepo,
			InstructionRepository instructionRepo) {
		this.recipeRepo = recipeRepo;
		this.instructionRepo = instructionRepo;
		this.ingredientRepo = ingredientRepo;

		List<Recipe> recipes = Arrays.asList(new Recipe[] { new Recipe("Hamburgers", "Who doesn't like hamburgers done on the grill?", 10),
				new Recipe("Mashed Potatoes", "Whipped--Not Lumpy...", 60), new Recipe("Noodles", "Every kid's favorit meal", 120) });

		recipeRepo.save(recipes);

		ingredientRepo.save(new Ingredient(recipes.get(0), "Meat", "ounce", 10)); 
		ingredientRepo.save(new Ingredient(recipes.get(0), "Salt", "ounce", 10));
		ingredientRepo.save(new Ingredient(recipes.get(0), "Gravy", "ounce", 10)); 
		ingredientRepo.save(new Ingredient(recipes.get(0), "Herbs", "ounce", 10));
		ingredientRepo.save(new Ingredient(recipes.get(0), "Butter", "ounce", 10));
		ingredientRepo.save(new Ingredient(recipes.get(0), "Seasonings", "ounce", 10));
		ingredientRepo.save(new Ingredient(recipes.get(1), "Potatoes", "kilo", 2));
		ingredientRepo.save(new Ingredient(recipes.get(1), "garlic", "1/3 teaspoon", 2));
		ingredientRepo.save(new Ingredient(recipes.get(1), "Cheese", "kilo", 2));
		ingredientRepo.save(new Ingredient(recipes.get(1), "Ham", "kilo", 2));
		ingredientRepo.save(new Ingredient(recipes.get(1), "Onions", "kilo", 2));
		ingredientRepo.save(new Ingredient(recipes.get(2), "Butter", "pound", 1));
		ingredientRepo.save(new Ingredient(recipes.get(2), "Eggs", "pound", 1));
		ingredientRepo.save(new Ingredient(recipes.get(2), "Flour", "pound", 1));
		ingredientRepo.save(new Ingredient(recipes.get(2), "Noodles", "pound", 1));

		instructionRepo.save(new Instruction(recipes.get(0), "Who doesn't like hamburgers done on the grill?"));
		instructionRepo.save(new Instruction(recipes.get(1), "Whipped--Not Lumpy..."));
		instructionRepo.save(new Instruction(recipes.get(2), "Every kid's favorit meal"));
	}

	@GetMapping("")
    public List<Recipe> getAll(String title) {        
        List<Recipe> recipe;
        if (title != null) {
        	recipe = recipeRepo.findByTitleContaining(title);
            return recipe;
        }    
        return recipeRepo.findAll();
	}

	@GetMapping("{id}")
	public Recipe getOne(@PathVariable long id) throws RecipeNotFoundException {
		Recipe recipe = recipeRepo.findOne(id);
		if (recipe == null) {
			throw new RecipeNotFoundException();
		}
		return recipe;
	}

	@PostMapping("")
	public Recipe create(@RequestBody Recipe recipe) {
		return recipeRepo.save(recipe);
	}

	@PutMapping("{id}")
	public Recipe update(@RequestBody Recipe recipe, @PathVariable long id) {
		recipe.setId(id);
		return recipeRepo.save(recipe);
	}

	@DeleteMapping("{id}")
	public Recipe delete(@PathVariable long id) {
		try {
			Recipe recipe = recipeRepo.findOne(id);
			recipeRepo.delete(id);
			return recipe;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
