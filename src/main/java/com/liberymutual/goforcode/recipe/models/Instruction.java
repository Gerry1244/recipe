package com.liberymutual.goforcode.recipe.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Instruction {

	public class Instructions {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		
		@ManyToOne
		private List<Instruction> instruction;
		
		


		private int quantity;

		public Instructions() {
		}

		public Instructions(List<Instruction> instruction) {
			this.instruction = instruction;

		}

	}
}