package com.generation.blogPessoal.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import com.generation.blogPessoal.model.Usuario;
import com.generation.blogPessoal.repository.UsuarioRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance (TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {

	@Autowired
	private UsuarioRepository repositorio;
	
	@Autowired   
	private TestRestTemplate testRestTemplate;

	private Usuario usuario;
	private Usuario usuarioUpdate;
	private Usuario usuarioAdmin;
	
	
	@BeforeAll
	void start() {
		
		LocalDate dataAdmin = LocalDate.parse("2004-04-16", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		usuarioAdmin = new Usuario(0L, "Administrador", "admin@admin.com.br", null, dataAdmin);
		
		if(!repositorio.findByUsuario(usuarioAdmin.getUsuario()).isPresent()) {
			
			HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioAdmin);       
			testRestTemplate.exchange("/usuarios/cadastrar", 
					HttpMethod.POST, request, Usuario.class);
		}
		
		
		
	}
	
	@Test
	@DisplayName ("Retorna o nome")
	public void PostRetornaUsuarioCadastrado() {
		
	}
	
}
