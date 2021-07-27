package com.generation.blogPessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
}
