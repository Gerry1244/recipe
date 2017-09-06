package com.liberymutual.goforcode.recipe.controllers;

import java.util.List;

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


@RestController
@RequestMapping("/recipes/{id}/ingredients")
public class IngredientApiController {

	private IngredientRepository ingredientRepo;

	public IngredientApiController(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
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
	    public Ingredient create(@RequestBody Ingredient ing) {
	        return ingredientRepo.save(ing);   
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