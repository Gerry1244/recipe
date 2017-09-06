package com.liberymutual.goforcode.recipe.controllers;

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

import com.liberymutual.goforcode.recipe.models.Recipe;
import com.liberymutual.goforcode.recipe.repositories.RecipeRepository;

@RestController
@RequestMapping("/recipes")

public class RecipeApiController {

	private RecipeRepository recipeRepo;

	public RecipeApiController(RecipeRepository recipeRepo) {
		this.recipeRepo = recipeRepo; 

		recipeRepo.save(new Recipe("FoodName1", "ounces", 20));
		recipeRepo.save(new Recipe("FoodName2", "tbsp", 10));
		recipeRepo.save(new Recipe("FoodName3", "tps", 10));
		recipeRepo.save(new Recipe("FoodName4", "lbs", 15)); 

	}
	
//	@PostMapping("{movieId}/actors")
//	public Movie associateAnActor(@PathVariable long movieId, @RequestBody Actor actor) {
//		Movie movie = movieRepo.findOne(movieId);
//		
//		return movie;
//		
//	}

	@GetMapping("")
	public List<Recipe> getAll() {
		return recipeRepo.findAll();
	}
}

//	@GetMapping("{id}")
//	public Actor getOne(@PathVariable long id) throws StuffNotFoundException {
//		Actor actor = actorRepo.findOne(id);
//		if (actor == null) {
//			throw new StuffNotFoundException();
//		}
//	
//	ActorsWithMovies newActor = new ActorsWithMovies();
//	newActor.setActiveSinceYear(actor.getActiveSinceYear());
//	newActor.setBirthDate(actor.getBirthDate());
//	newActor.setMovies(actor.getMovies());
//	newActor.setFirstName(actor.getFirstName());
//	newActor.setLastName(actor.getLastName());


//	return newActor;
//	return actor;
//}
//
//
//	@DeleteMapping("{id}")
//	public Actor delete(@PathVariable long id) {
//		try {
//			Actor actor = actorRepo.findOne(id); 
//			actorRepo.delete(id);
//			return actor;
//		} catch (EmptyResultDataAccessException erd) {
//			return null;  
//
//		}
//
//	}
//
//	@PostMapping("")
//	public Actor create(@RequestBody Actor actor) {
//		return actorRepo.save(actor);
//	}
//
//	@PutMapping("{id}")
//	public Actor update(@RequestBody Actor actor, @PathVariable long id) {
//		actor.setId(id);
//		return actorRepo.save(actor);
//	}
//
//}
