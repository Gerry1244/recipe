package com.liberymutual.goforcode.recipe.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.aspectj.apache.bcel.generic.Instruction;

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

	@OneToMany(mappedBy = "recipe")
	private List<Instruction> instruction;
	//
	// private List<Ingredient> ingredient;

	private int num_min;

	// public Recipe(String) {
	//
	// }

	public Recipe(String title, String description, int num_min) {
		this.title = title;
		this.description = description;
		this.num_min = num_min;
		this.num_min = num_min;
		this.title = title;
		this.description= description;
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
		return num_min;
	}

	public void setNum_min(int num_min) {
		this.num_min = num_min;
	}

}
