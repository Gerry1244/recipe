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
import com.liberymutual.goforcode.recipe.models.Instruction;
import com.liberymutual.goforcode.recipe.models.Recipe;
import com.liberymutual.goforcode.recipe.repositories.IngredientRepository;
import com.liberymutual.goforcode.recipe.repositories.InstructionRepository;
import com.liberymutual.goforcode.recipe.repositories.RecipeRepository;

@RestController
@RequestMapping("/recipes/{id}/instructions")
public class InstructionApiController {

	private InstructionRepository instructionRepo;
	private RecipeRepository recipeRepository;

	public InstructionApiController(InstructionRepository instructionRepo, RecipeRepository recipeRepo) {
		this.instructionRepo = instructionRepo;
		this.recipeRepository =  recipeRepo;
	}

	@GetMapping("") 
	public List<Instruction> getAll() {
		return instructionRepo.findAll(); 
	}

	@GetMapping("{instructionId}")
	public Instruction getOne(@PathVariable long instructionId) throws InstructionNotFoundException {
		Instruction instruction = instructionRepo.findOne(instructionId);
		if (instruction == null) {
			throw new InstructionNotFoundException();
		}
		return instruction;
	
	}
	
	 @PostMapping("")
	    public Instruction create(@PathVariable long recipeId, @RequestBody Instruction instruction) {
		Recipe recipe = recipeRepository.findOne(recipeId);
		instruction.setRecipe(recipe);
	        return instructionRepo.save(instruction);   
	    } 
	
 @DeleteMapping("{instructionId}")
 public Instruction delete(@PathVariable long instructionId) {
     try {
    	 Instruction instruction = instructionRepo.findOne(instructionId);
    	 instructionRepo.delete(instructionId);
          return instruction;            
     } catch (EmptyResultDataAccessException e) {
          return null;
     }
 }
}
