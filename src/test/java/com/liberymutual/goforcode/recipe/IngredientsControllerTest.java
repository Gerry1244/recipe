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
import com.liberymutual.goforcode.recipe.controllers.IngredientNotFoundException;
import com.liberymutual.goforcode.recipe.controllers.InstructionApiController;
import com.liberymutual.goforcode.recipe.controllers.RecipeApiController;
import com.liberymutual.goforcode.recipe.models.Ingredient;
import com.liberymutual.goforcode.recipe.models.Recipe;
import com.liberymutual.goforcode.recipe.repositories.IngredientRepository;
import com.liberymutual.goforcode.recipe.repositories.InstructionRepository;
import com.liberymutual.goforcode.recipe.repositories.RecipeRepository;

public class IngredientsControllerTest {

	private RecipeRepository recipeRepo;
	private IngredientRepository ingredientRepo;
	private InstructionRepository instructionRepo;
	private IngredientApiController controller;

	@Before
	public void setUp() {
		recipeRepo = mock(RecipeRepository.class);
		ingredientRepo = mock(IngredientRepository.class);
		instructionRepo = mock(InstructionRepository.class);
		controller = new IngredientApiController(ingredientRepo, recipeRepo);

	}

	@Test
	public void test_getOne_throws_ingredientNotFound_when_no_ingredient_returned_from_ingredientRepo() {
		try {

			controller.getOne(1);

			fail("The controller did not throw the IngredientNotFOundException");
		} catch (IngredientNotFoundException inf) {
		}
	}

	@Test
	public void test_getAll_returns_all_Ingredients_returned_by_the_ingredientRepo() {
		// arrange
		ArrayList<Ingredient> ingredient = new ArrayList<Ingredient>();
		ingredient.add(new Ingredient());
		ingredient.add(new Ingredient());

		when(ingredientRepo.findAll()).thenReturn(ingredient);

		// Act
		List<Ingredient> actual = controller.getAll();

		// Assert
		assertThat(actual.size()).isEqualTo(2);
		assertThat(actual.get(0)).isSameAs(ingredient.get(0));
		verify(ingredientRepo).findAll();

	}

	// Update
	@Test
	public void test_update_saves_modified_ingredient() {

		// Arrange
		Ingredient ingredient = new Ingredient();
		when(ingredientRepo.save(ingredient)).thenReturn(ingredient);

		// Act
		Ingredient actual = controller.create(11L, ingredient);

		// Assert
		assertThat(actual).isSameAs(ingredient);

	}

	// Delete
	@Test
	public void test_delete_returns_ingredient_deleted_when_ingredient_is_found() {
		// Arrange
		Ingredient ingredient = new Ingredient();
		when(ingredientRepo.findOne(33L)).thenReturn(ingredient);

		// Act
		Ingredient actual = controller.delete(33L);

		// Assert
		assertThat(ingredient).isSameAs(actual);
		verify(ingredientRepo).delete(33L);
		verify(ingredientRepo).findOne(33L);
	}

	@Test
	public void test_that_null_is_returned_when_findOne_throws_EmptyResultDataAccess()
			throws IngredientNotFoundException {

		// arrange
		when(ingredientRepo.findOne(8L)).thenThrow(new EmptyResultDataAccessException(0));

		// Act
		Ingredient actual = controller.delete(8L);

		// assert
		assertThat(actual).isNull();
		verify(ingredientRepo).findOne(8L);

	}

}