package com.br.alura.financias.controllers;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.alura.financias.controllers.dto.ReceitaDto;
import com.br.alura.financias.controllers.form.ReceitaForm;
import com.br.alura.financias.entities.Receita;
import com.br.alura.financias.repositories.ReceitaRepository;

@Controller
@RequestMapping("/receitas")
public class ReceitaController {

	@Autowired
	ReceitaRepository receitaRepository;
	
	@GetMapping
	public String list() {
		return "";
	}
	
	@PostMapping //O IDEAL É DEVOLVER CODIGO 201 NO RESPONSE ENTITY
	@Transactional
	public ResponseEntity<ReceitaDto> cadastra(@RequestBody @Valid ReceitaForm receitaForm, UriComponentsBuilder uriBuilder){
		
		Receita newReceita = receitaForm.toReceita(receitaRepository);
		Receita receita = receitaRepository.findByDescricaoAndData(newReceita.getDescricao(), newReceita.getData());
		
		if(receita == null) {
			receitaRepository.save(newReceita);
			
			URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(newReceita.getId()).toUri();
			
			return ResponseEntity.created(uri).body(new ReceitaDto(newReceita));
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}
