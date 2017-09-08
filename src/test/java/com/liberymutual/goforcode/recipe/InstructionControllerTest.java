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
import com.liberymutual.goforcode.recipe.controllers.InstructionNotFoundException;
import com.liberymutual.goforcode.recipe.controllers.RecipeApiController;
import com.liberymutual.goforcode.recipe.models.Ingredient;
import com.liberymutual.goforcode.recipe.models.Instruction;  
import com.liberymutual.goforcode.recipe.models.Recipe;
import com.liberymutual.goforcode.recipe.repositories.IngredientRepository;
import com.liberymutual.goforcode.recipe.repositories.InstructionRepository;
import com.liberymutual.goforcode.recipe.repositories.RecipeRepository;


public class InstructionControllerTest {
    
    private RecipeRepository    rcpRepo; 
    private IngredientRepository      ingrRepo;
    private InstructionRepository     instrRepo;      
    private InstructionApiController    controller;
    
                            
    @Before
    public void setUp() { 
        rcpRepo = mock(RecipeRepository.class); 
        ingrRepo = mock(IngredientRepository.class); 
        instrRepo = mock(InstructionRepository.class); 
        controller = new InstructionApiController(instrRepo, rcpRepo); 
         
    } 
    
    @Test
	public void test_getOne_throws_instructionNotFound_when_no_instructions_returned_from_instrRepo() {
		try {

			controller.getOne(1);

			// This line of code SHOULD NOT run
			fail("The controller did not throw the StuffNotFOundException");
		} catch (InstructionNotFoundException snfe) {}
	}
    
	@Test
	public void test_getAll_returns_all_Instructions_returned_by_the_instrRepo() {
		// arrange
		ArrayList<Instruction> instruction = new ArrayList<Instruction>();
		instruction.add(new Instruction());
		instruction.add(new Instruction()); 

		when(instrRepo.findAll()).thenReturn(instruction);

		// Act
		List<Instruction> actual = controller.getAll();

		// Assert
		assertThat(actual.size()).isEqualTo(2);
		assertThat(actual.get(0)).isSameAs(instruction.get(0));
		verify(instrRepo).findAll();
		
	}
    
    // Update
    @Test
    public void test_update_saves_modified_instruction() {
            
        // Arrange
        Instruction instruction = new Instruction();               
        when(instrRepo.save(instruction)).thenReturn(instruction); 
            
        // Act 
        Instruction actual = controller.create(11L, instruction);
            
        // Assert
        assertThat(actual).isSameAs(instruction);   
          
    }   
   
    // Delete
    @Test
    public void test_delete_returns_instruction_deleted_when_instruction_is_found() {
        // Arrange
    	Instruction rcp = new Instruction();
        when(instrRepo.findOne(33L)).thenReturn(rcp);
        
        // Act  
        Instruction actual = controller.delete(33L);
        
        // Assert
        assertThat(rcp).isSameAs(actual);  
        verify(instrRepo).findOne(33L);   
    }
    
    @Test
	public void test_that_null_is_returned_when_findOne_throws_EmptyResultDataAccess( ) throws InstructionNotFoundException {
		
		//arrange
		when(instrRepo.findOne(8l)).thenThrow(new EmptyResultDataAccessException(0));
		
		//Act
		Instruction actual = controller.delete(8L);
		
		//assert
		assertThat(actual).isNull();
		verify(instrRepo).findOne(8l);
		
	}
}