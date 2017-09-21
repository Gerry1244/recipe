package com.liberymutual.goforcode.recipe.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 100, nullable = false)
	private String title;
	

	@Column(length = 1000, nullable = true)
	private String description;

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	private List<Instruction> instructions;
	

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	private List<Ingredient> ingredients;

	private int numberMinutes; 

	public Recipe() {}  

	public Recipe(String title, String description, int numberMinutes) { 
		this.title = title;
		this.description = description;
		this.numberMinutes = numberMinutes;
		ingredients = new ArrayList<Ingredient>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNum_min() {
		return numberMinutes;
	}

	public void setNum_min(int num_min) {
		this.numberMinutes = numberMinutes;
	}

	public List<Instruction> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

}
