package com.liberymutual.goforcode.recipe.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Instruction")

public class IngredientNotFoundException extends Exception { 
	
	private static final long serialVersionUID = 1L;

}
