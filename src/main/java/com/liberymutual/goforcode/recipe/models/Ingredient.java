package com.liberymutual.goforcode.recipe.models;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

	public Ingredient() {
	}

	public Ingredient(String title, String description, int quantity) {
		this.food_name = food_name;
		this.units = units;
		this.quantity = quantity;;

	}

}
