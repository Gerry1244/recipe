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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

}
