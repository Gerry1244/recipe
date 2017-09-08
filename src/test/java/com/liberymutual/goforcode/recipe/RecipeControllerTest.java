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

	private RecipeRepository rcpRepo;
	private IngredientRepository ingrRepo;
	private InstructionRepository instrRepo;
	private RecipeApiController controller;

	@Before
	public void setUp() {
		rcpRepo = mock(RecipeRepository.class);
		ingrRepo = mock(IngredientRepository.class);
		instrRepo = mock(InstructionRepository.class);
		controller = new RecipeApiController(rcpRepo, ingrRepo, instrRepo);

	} 

	@Test
	public void test_getOne_returns_a_recipe_from_Recipe_repo() throws RecipeNotFoundException {

		// arrange
		Recipe Description = new Recipe();
		when(rcpRepo.findOne(23L)).thenReturn(Description); 

		// Act
		Recipe actual = controller.getOne(23L);
 
		// Assert
		assertThat(actual).isSameAs(Description);
		verify(rcpRepo).findOne(23L);

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
		ArrayList<Recipe> rcpList = new ArrayList<Recipe>();
		rcpList.add(recipe);
		when(rcpRepo.findByTitleContaining(description)).thenReturn(rcpList);
		
		// Act
		List <Recipe> actual = controller.getAll(description);

		// Assert
		assertThat(description).isEqualTo(recipe.getTitle());
		assertThat(actual).isSameAs(rcpList);

	}
	 
	// Update
	@Test
	public void test_update_saves_modified_recipe() {

		// Arrange
		Recipe rcp = new Recipe();
		when(rcpRepo.save(rcp)).thenReturn(rcp);

		// Act
		Recipe actual = controller.update(rcp, 11L);

		// Assert
		assertThat(actual).isSameAs(rcp);
		assertThat(actual.getId()).isEqualTo(11L);

	}

	// Delete
	@Test
	public void test_delete_returns_recipe_deleted_when_recipe_is_found() {
		// Arrange
		Recipe rcp = new Recipe();
		when(rcpRepo.findOne(33L)).thenReturn(rcp);

		// Act
		Recipe actual = controller.delete(33L);

		// Assert
		assertThat(rcp).isSameAs(actual);
		verify(rcpRepo).delete(33L);
		verify(rcpRepo).findOne(33L);
	}
	
    @Test
	public void test_that_null_is_returned_when_findOne_throws_EmptyResultDataAccess( ) throws RecipeNotFoundException {
		
		//arrange
		when(rcpRepo.findOne(8l)).thenThrow(new EmptyResultDataAccessException(0));
		
		//Act
		Recipe actual = controller.delete(8L);
		
		//assert
		assertThat(actual).isNull();
		verify(rcpRepo).findOne(8l);
		
	}
}
