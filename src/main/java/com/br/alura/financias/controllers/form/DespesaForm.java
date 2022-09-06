package com.br.alura.financias.controllers.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.br.alura.financias.entities.Despesa;
import com.br.alura.financias.repositories.DespesaRepository;


public class DespesaForm {

	@NotNull
	private Integer valor;
	@NotNull @NotEmpty @Length(min = 5)
	private String descricao;
	@NotNull
	private LocalDate data;
	
	
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Despesa todespesa(DespesaRepository despesaRepository) {
		return new Despesa(this.valor, this.descricao, this.data);	
	}
	
	public Despesa update(Long id, DespesaRepository despesaRepository) {
		Despesa despesa = despesaRepository.getReferenceById(id);
		
		despesa.setValor(valor);
		despesa.setData(data);
		despesa.setDescricao(descricao);
		
		return despesa;
	}
	
}
