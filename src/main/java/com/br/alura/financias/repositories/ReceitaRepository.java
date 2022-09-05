package com.br.alura.financias.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.alura.financias.entities.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{

	Receita findByDescricaoAndData(String descricao, LocalDate Data);

}
