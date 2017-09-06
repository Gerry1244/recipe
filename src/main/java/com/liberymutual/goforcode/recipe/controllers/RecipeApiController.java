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
    private RecipeRepository rcpRepo;
    private IngredientRepository   ingrRepo;
    private InstructionRepository   instrRepo;
    
    
    public RecipeApiController(RecipeRepository recipeRepo, IngredientRepository ingredientRepo, InstructionRepository instrRepo) {
        this.rcpRepo  = recipeRepo;
        this.instrRepo = instrRepo;
        this.ingrRepo = ingredientRepo;
        
        List<Recipe> recipes = Arrays.asList(new Recipe[]{
                new Recipe("Food_1", "Description1",  10),
                new Recipe("Food_2", "Description2",  60),
                new Recipe("Food_3", "Description3",  120)
        });
        
        rcpRepo.save(recipes);  
        
        ingrRepo.save(new Ingredient(recipes.get(0), "Meat",   "ounce", 10));  
        ingrRepo.save(new Ingredient(recipes.get(0), "Salt",   "ounce", 10)); 
        ingrRepo.save(new Ingredient(recipes.get(0), "Milk",   "ounce", 10)); 
        ingrRepo.save(new Ingredient(recipes.get(1), "Potatoes", "kilo",  2)); 
        ingrRepo.save(new Ingredient(recipes.get(1), "garlic", "1/3 teaspoon", 2));
        ingrRepo.save(new Ingredient(recipes.get(1), "Cheese", "kilo",  2));
        ingrRepo.save(new Ingredient(recipes.get(1), "Ham", "kilo",  2));
        ingrRepo.save(new Ingredient(recipes.get(1), "Onions", "kilo",  2));
        ingrRepo.save(new Ingredient(recipes.get(2), "Butter",     "pound", 1));
      
        instrRepo.save(new Instruction(recipes.get(0), "description1"));       
        instrRepo.save(new Instruction(recipes.get(1), "descriptions2"));      
        instrRepo.save(new Instruction(recipes.get(2), "descriptions3"));
    }
    
    @GetMapping("")
    public List<Recipe> getAll() {
        return rcpRepo.findAll();
    }
    
    @GetMapping("{id}")
    public Recipe getOne(@PathVariable long id) throws RecipeNotFoundException {
        Recipe recipe = rcpRepo.findOne(id);
        if (recipe == null) {
            throw new RecipeNotFoundException();
        }
        return recipe;
    }
      
    @PostMapping("")
    public Recipe create(@RequestBody Recipe rcp) {
        return rcpRepo.save(rcp);   
    }   
    
    
    @PutMapping("{id}")
    public Recipe update(@RequestBody Recipe rcp, @PathVariable long id) {
        rcp.setId(id);
        return rcpRepo.save(rcp);   
    }   
    
        
    @DeleteMapping("{id}")
    public Recipe delete(@PathVariable long id) {
        try {
             Recipe rcp = rcpRepo.findOne(id);
             rcpRepo.delete(id);
             return rcp;            
        } catch (EmptyResultDataAccessException e) {
             return null;
        }
    }
}



