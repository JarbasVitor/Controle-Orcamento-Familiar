package com.br.alura.financias.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.alura.financias.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	Categoria findByNome(String string);

}
