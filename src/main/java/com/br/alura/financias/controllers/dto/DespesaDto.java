package com.br.alura.financias.controllers.dto;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import com.br.alura.financias.entities.Categoria;
import com.br.alura.financias.entities.Despesa;

public class DespesaDto {

	private Long id;
	private Integer valor;
	private String descricao;
	private LocalDate data;
	private Categoria categoria;
	
	public DespesaDto(Despesa despesa) {
		this.id = despesa.getId();
		this.valor = despesa.getValor();
		this.descricao = despesa.getDescricao();
		this.data = despesa.getData();
		this.categoria = despesa.getCategoria();
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
	
	public Categoria getCategoria() {
		return categoria;
	}

	public static Page<DespesaDto> convert(Page<Despesa> despesas) {
		return despesas.map(DespesaDto::new);
	}	
}
