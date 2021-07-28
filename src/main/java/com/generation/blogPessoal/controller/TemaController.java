package com.generation.blogPessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogPessoal.model.Tema;
import com.generation.blogPessoal.repository.TemaRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping ("/temas")
public class TemaController {

	@Autowired
	private TemaRepository repositorio;
	
	@GetMapping
	public ResponseEntity<List<Tema>> pegarTodosOsTemas(){
		return ResponseEntity.ok(repositorio.findAll());
	}

	@GetMapping ("/{id}")
	public ResponseEntity<Tema> buscarPeloId(@PathVariable long id){
		return repositorio.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping ("/descricao/{descricao}")
	public ResponseEntity<List<Tema>> buscarPeloNome(@PathVariable String descricao){
		return ResponseEntity.ok(repositorio.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping
	public ResponseEntity<Tema> criarTema(@RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repositorio.save(tema));
	}
	
	@PutMapping
	public ResponseEntity<Tema> atualizarTema(@RequestBody Tema tema){
		return ResponseEntity.ok(repositorio.save(tema));
	}
	
	@DeleteMapping ("/{id}")
	public void deletarTema(@PathVariable long id) {
		repositorio.deleteById(id);
	}
}
