package com.generation.blogPessoal.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogPessoal.model.Usuario;

@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT) // abre uma porta aleatória
@TestInstance (TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository repositorio;
	
	@BeforeAll // acontece antes de cada test
	void start() {
		
		LocalDate data = LocalDate.parse("2004-04-16", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		Usuario usuario = new Usuario(1, "Maria Desgraça", "semEmail@email.com", "12345678", data);
		if (repositorio.findByUsuario(usuario.getUsuario()).isEmpty()) {
			repositorio.save(usuario);
		}
		
		usuario = new Usuario(0, "Frederico da Silva", "frederico@email.com.br", "13465278", data); 
		if(!repositorio.findByUsuario(usuario.getUsuario()).isPresent()) {
			repositorio.save(usuario);
		}
		
		usuario = new Usuario(0, "Paulo Antunes Silva", "paulo@email.com.br", "13465278", data); 
		if(!repositorio.findByUsuario(usuario.getUsuario()).isPresent()) {
			repositorio.save(usuario);
		}
	}
	
	@Test
	@DisplayName ("Retorna o nome")
	public void findByNomeRetornaNome() {
		Usuario usuario = repositorio.findByNome("Maria Desgraça");
		
		assertTrue(usuario.getNome().equals("Maria Desgraça"));
	}
	
	@Test
	@DisplayName("Retorna 2 usuarios")
	public void findAllByNomeContainingIgnoreCaseRetornaTresUsuarios() {

		List<Usuario> listaDeUsuarios = repositorio.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(2, listaDeUsuarios.size());
	}
	
	@AfterAll
	public void end() {
		System.out.println("Fim dos testes");
	}
}