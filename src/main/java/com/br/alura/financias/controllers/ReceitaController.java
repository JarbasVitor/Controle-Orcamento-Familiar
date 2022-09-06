package com.br.alura.financias.controllers;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.alura.financias.controllers.dto.ReceitaDto;
import com.br.alura.financias.controllers.form.ReceitaForm;
import com.br.alura.financias.entities.Receita;
import com.br.alura.financias.repositories.ReceitaRepository;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

	@Autowired
	ReceitaRepository receitaRepository;
	
	@GetMapping
	public Page<ReceitaDto> list(@PageableDefault(page = 0, size = 10) Pageable page) {
		
		Page<Receita> receitas;
		receitas = receitaRepository.findAll(page);
		
		return ReceitaDto.converter(receitas);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ReceitaDto> register(@RequestBody @Valid ReceitaForm receitaForm, UriComponentsBuilder uriBuilder){
		
		Receita newReceita = receitaForm.toReceita(receitaRepository);
		Receita receita = receitaRepository.findByDescricaoAndData(newReceita.getDescricao(), newReceita.getData());
		
		if(receita == null) {
			receitaRepository.save(newReceita);
			
			URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(newReceita.getId()).toUri();
			
			return ResponseEntity.created(uri).body(new ReceitaDto(newReceita));
		}
		
		return ResponseEntity.status(409).build();
	}
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<ReceitaDto> details(@PathVariable Long id){
		
		Optional<Receita> receitaOptional = receitaRepository.findById(id);
		
		if(receitaOptional.isPresent()) {
			return ResponseEntity.ok(new ReceitaDto(receitaOptional.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ReceitaDto> update(@PathVariable Long id, @RequestBody @Valid ReceitaForm receitaForm){
		
		Optional<Receita> receitaOptional = receitaRepository.findById(id);
		
		if(receitaOptional.isPresent()) {
			Receita receita = receitaForm.update(id,receitaRepository);
			return ResponseEntity.ok(new ReceitaDto(receita));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Receita> receitaOptional = receitaRepository.findById(id);
		if(receitaOptional.isPresent()) {
			receitaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
