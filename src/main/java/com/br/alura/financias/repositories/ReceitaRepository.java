package com.br.alura.financias.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.br.alura.financias.entities.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{

	Receita findByDescricaoAndData(String descricao, LocalDate Data);

	Page<Receita> findByDescricaoContains(String descricao, Pageable page);

	Page<Receita> findByAnoAndMesAndDescricaoContains(Integer ano, Integer mes, String descricao, Pageable page);

	Page<Receita> findByAnoAndMes(Integer ano, Integer mes, Pageable page);

}
