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

import com.generation.blogPessoal.repository.PublicacaoRepository;
import com.generation.blogPessoal.model.Publicacao;

@RestController
@RequestMapping ("/publicacoes")
@CrossOrigin("*") //vai aceitar requisições de qualquer origem
public class PublicacaoController {

	
	@Autowired //injeção de dependencias
	private PublicacaoRepository repositorio;
	
	@GetMapping
	public ResponseEntity<List<Publicacao>> getAll(){ // ao que parece vai retornar um OK
		
		return ResponseEntity.ok(repositorio.findAll()); //retorna uma lista do que tiver no bd
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Publicacao> buscarPeloId(@PathVariable long id){
		
		return repositorio.findById(id).map(resposta -> ResponseEntity
				.ok(resposta)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping ("/titulo/{titulo}")
	public ResponseEntity<List <Publicacao>> buscarPeloTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repositorio.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Publicacao> publicar(@RequestBody Publicacao publicacao){ // vai pegar o corpo da requisição
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repositorio.save(publicacao));
	}
	
	@PutMapping
	public ResponseEntity<Publicacao> editar(@RequestBody Publicacao publicacao){ // vai pegar o corpo da requisição
		return ResponseEntity.status(HttpStatus.OK)
				.body(repositorio.save(publicacao));
	}
	
	@DeleteMapping ("/{id}")
	public void deletar(@PathVariable long id) {
		repositorio.deleteById(id);
	}
}
