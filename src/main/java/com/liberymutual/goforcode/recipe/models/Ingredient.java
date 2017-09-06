package com.liberymutual.goforcode.recipe.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 200, nullable = false)
	private String food_name;

	@Column(length = 50, nullable = true)
	private String units;

	private int quantity;  
	
	@JsonIgnore
	@ManyToOne
	private Recipe recipe;

	
	public Ingredient() {
	}

	public Ingredient(Recipe recipe, String food_name, String units, int quantity) {
		this.food_name = food_name;
		this.units = units;
		this.quantity = quantity;
		this.recipe = recipe; 
	}

}
