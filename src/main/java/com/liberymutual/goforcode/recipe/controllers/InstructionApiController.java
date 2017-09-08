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

	public InstructionApiController(InstructionRepository instructionRepo, RecipeRepository rcpRepo) {
		this.instructionRepo = instructionRepo;
		this.recipeRepository =  rcpRepo;
	}

	@GetMapping("")
	public List<Instruction> getAll() {
		return instructionRepo.findAll(); 
	}

	@GetMapping("{ins_id}")
	public Instruction getOne(@PathVariable long ins_id) throws InstructionNotFoundException {
		Instruction instr = instructionRepo.findOne(ins_id);
		if (instr == null) {
			throw new InstructionNotFoundException();
		}
		return instr;
	
	}
	
	 @PostMapping("")
	    public Instruction create(@PathVariable long recipeId, @RequestBody Instruction instruction) {
		Recipe recipe = recipeRepository.findOne(recipeId);
		instruction.setRecipe(recipe);
	        return instructionRepo.save(instruction);   
	    } 
	
 @DeleteMapping("{ins_id}")
 public Instruction delete(@PathVariable long ins_id) {
     try {
    	 Instruction instr = instructionRepo.findOne(ins_id);
    	 instructionRepo.delete(ins_id);
          return instr;            
     } catch (EmptyResultDataAccessException e) {
          return null;
     }
 }
}
