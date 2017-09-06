package com.liberymutual.goforcode.recipe.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Instruction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Recipe recipe;

	private int quantity;

	public Instruction() {
	}

	public Instruction(Recipe recipe) {
		this.recipe = recipe;

	}
}