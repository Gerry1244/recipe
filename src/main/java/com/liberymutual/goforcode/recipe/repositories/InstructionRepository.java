package com.liberymutual.goforcode.recipe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liberymutual.goforcode.recipe.models.Instruction;
import com.liberymutual.goforcode.recipe.models.Recipe;


@Repository
public interface InstructionRepository extends JpaRepository<Instruction, Long> {

	
}
