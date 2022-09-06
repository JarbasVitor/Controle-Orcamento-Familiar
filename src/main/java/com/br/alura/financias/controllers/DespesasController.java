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

import com.br.alura.financias.controllers.dto.DespesaDto;
import com.br.alura.financias.controllers.form.DespesaForm;
import com.br.alura.financias.entities.Despesa;
import com.br.alura.financias.repositories.DespesaRepository;

@RestController
@RequestMapping("/despesas")
public class DespesasController {

	@Autowired
	DespesaRepository despesaRepository;
	
	@GetMapping
	public Page<DespesaDto> list(@PageableDefault(page = 0, size = 10) Pageable page) {
		
		Page<Despesa> despesas;
		despesas = despesaRepository.findAll(page);
		
		return DespesaDto.convert(despesas);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<DespesaDto> register(@RequestBody @Valid DespesaForm despesaForm, UriComponentsBuilder uriBuilder){
		
		Despesa newdespesa = despesaForm.todespesa(despesaRepository);
		Despesa despesa = despesaRepository.findByDescricaoAndData(newdespesa.getDescricao(), newdespesa.getData());
		
		if(despesa == null) {
			despesaRepository.save(newdespesa);
			
			URI uri = uriBuilder.path("/despesas/{id}").buildAndExpand(newdespesa.getId()).toUri();
			
			return ResponseEntity.created(uri).body(new DespesaDto(newdespesa));
		}
		
		return ResponseEntity.status(409).build();
	}
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<DespesaDto> details(@PathVariable Long id){
		
		Optional<Despesa> despesaOptional = despesaRepository.findById(id);
		
		if(despesaOptional.isPresent()) {
			return ResponseEntity.ok(new DespesaDto(despesaOptional.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DespesaDto> update(@PathVariable Long id, @RequestBody @Valid DespesaForm despesaForm){
		
		Optional<Despesa> despesaOptional = despesaRepository.findById(id);
		
		if(despesaOptional.isPresent()) {
			Despesa despesa = despesaForm.update(id, despesaRepository);
			return ResponseEntity.ok(new DespesaDto(despesa));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Despesa> despesaOptional = despesaRepository.findById(id);
		if(despesaOptional.isPresent()) {
			despesaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
