package Api;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.liberymutual.goforcode.recipe.models.Ingredient;


public class Ingredient_Test {
	private Ingredient ingredient;     

	public void setUp() {
	ingredient = new Ingredient();
	}

	@Test
	public void test_getid_and_setid() {
		// arrange
		ingredient.setId(22L);

		// Act
		Long actual = ingredient.getId();

		// Assert
		assertThat(ingredient.getId()).isEqualTo(22L);
	}
}
