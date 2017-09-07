package com.libertymutual.goforcode.recipe;Api;

public class RecipeTest {


import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.libertymutual.goforcode.recipe.models.Recipe;

	public class Recipe recipe {
		private Recipe recipe;     

		@Before
		public void setUp() {
			recipe = new Recipe();
		}

		@Test
		public void test_getid_and_setid() {
			// arrange
			recipe.setId(22L);

			// Act
			Long actual = recipe.getId();

			// Assert
			assertThat(recipe.getId()).isEqualTo(22L);
		}
	}