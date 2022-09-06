package com.br.alura.financias.controllers.dto;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import com.br.alura.financias.entities.Receita;

public class ReceitaDto {

	private Long id;
	private Integer valor;
	private String descricao;
	private LocalDate data;
	
	public ReceitaDto(Receita receita) {
		this.id = receita.getId();
		this.valor = receita.getValor();
		this.descricao = receita.getDescricao();
		this.data = receita.getData();
	}

	public Long getId() {
		return id;
	}

	public Integer getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDate getData() {
		return data;
	}
	
	public static Page<ReceitaDto> converter(Page<Receita> receitas) {
		return receitas.map(ReceitaDto::new);
	}	
}
