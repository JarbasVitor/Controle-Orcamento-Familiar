package com.br.alura.financias.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.alura.financias.entities.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{

}
