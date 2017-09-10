package com.liberymutual.goforcode.recipe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.liberymutual.goforcode.recipe.controllers.IngredientApiController;
import com.liberymutual.goforcode.recipe.controllers.InstructionNotFoundException;
import com.liberymutual.goforcode.recipe.controllers.RecipeApiController;
import com.liberymutual.goforcode.recipe.controllers.RecipeNotFoundException;
import com.liberymutual.goforcode.recipe.models.Ingredient;
import com.liberymutual.goforcode.recipe.models.Instruction;
import com.liberymutual.goforcode.recipe.models.Recipe;
import com.liberymutual.goforcode.recipe.repositories.IngredientRepository;
import com.liberymutual.goforcode.recipe.repositories.InstructionRepository;
import com.liberymutual.goforcode.recipe.repositories.RecipeRepository;

public class RecipeControllerTest {

	private RecipeRepository recipeRepo;
	private IngredientRepository ingredientRepo;
	private InstructionRepository instructionRepo;
	private RecipeApiController controller;

	@Before
	public void setUp() {
		recipeRepo = mock(RecipeRepository.class);
		ingredientRepo = mock(IngredientRepository.class);
		instructionRepo = mock(InstructionRepository.class);
		controller = new RecipeApiController(recipeRepo, ingredientRepo, instructionRepo);

	}  
 
	@Test
	public void test_getOne_returns_a_recipe_from_recipeRepo() throws RecipeNotFoundException {

		// arrange
		Recipe Description = new Recipe();
		when(recipeRepo.findOne(23L)).thenReturn(Description); 

		// Act
		Recipe actual = controller.getOne(23L);
 
		// Assert
		assertThat(actual).isSameAs(Description);
		verify(recipeRepo).findOne(23L);

	}

	@Test
	public void test_the_GetOne_method_throws_exception_not_found_when_recipe_cannot_be_found()
			throws RecipeNotFoundException {
		try {
			controller.getOne(23L);
			fail("controller throws RecipeNotFoundException");

		} catch (RecipeNotFoundException rnf) {

		}

	}

	@Test
	public void test_getAll_returns_all_Recipes_returned_by_the_repo() { 
		// arrange
		Recipe recipe = new Recipe();
		String description = "Hamburger";
		recipe.setTitle(description);
		ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
		recipeList.add(recipe);
		when(recipeRepo.findByTitleContaining(description)).thenReturn(recipeList);
		
		// Act
		List <Recipe> actual = controller.getAll(description);

		// Assert
		assertThat(description).isEqualTo(recipe.getTitle());
		assertThat(actual).isSameAs(recipeList);

	}
	 
	// Update
	@Test
	public void test_update_saves_modified_recipe() {

		// Arrange
		Recipe recipe = new Recipe();
		when(recipeRepo.save(recipe)).thenReturn(recipe);

		// Act
		Recipe actual = controller.update(recipe, 11L);

		// Assert
		assertThat(actual).isSameAs(recipe);
		assertThat(actual.getId()).isEqualTo(11L);  

	}

	// Delete
	@Test
	public void test_delete_returns_recipe_deleted_when_recipe_is_found() {
		// Arrange
		Recipe recipe = new Recipe();
		when(recipeRepo.findOne(33L)).thenReturn(recipe);

		// Act
		Recipe actual = controller.delete(33L);

		// Assert
		assertThat(recipe).isSameAs(actual);
		verify(recipeRepo).delete(33L);
		verify(recipeRepo).findOne(33L);
	}
	
    @Test
	public void test_that_null_is_returned_when_findOne_throws_EmptyResultDataAccess( ) throws RecipeNotFoundException {
		
		//arrange
		when(recipeRepo.findOne(8l)).thenThrow(new EmptyResultDataAccessException(0));
		
		//Act
		Recipe actual = controller.delete(8L);
		
		//assert
		assertThat(actual).isNull();
		verify(recipeRepo).findOne(8l);
		
	}
}
