package com.br.alura.financias.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.alura.financias.entities.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long>{

}
