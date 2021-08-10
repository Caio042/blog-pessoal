package com.generation.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
		
		usuarioAdmin = new Usuario(0L, "Administrador", "admin@admin.com.br", "admin123", dataAdmin);
		
		if(!repositorio.findByUsuario(usuarioAdmin.getUsuario()).isPresent()) {
			
			HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioAdmin);       
			testRestTemplate.exchange("/usuarios/cadastrar", 
					HttpMethod.POST, request, Usuario.class);
		}
		
		LocalDate dataUsuario = LocalDate.parse("1985-11-09", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        usuario = new Usuario(0L, "José da Arimateia", "jose@email.com.br", "12345678", dataUsuario); 
		
        LocalDate dataUsuarioUpdate = LocalDate.parse("2000-07-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        usuarioUpdate = new Usuario(2L, "Maria das Graças", "maria@email.com.br", "maria123", dataUsuarioUpdate);
		
	}
	
	@Test
	@DisplayName ("Cadastra o usuario")
	@Order(1)
	public void deveCadastrarUsuario() {
		
		HttpEntity<Usuario> requisicao = new HttpEntity<>(usuario);
		
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuarios/cadastrar",
				HttpMethod.POST, requisicao, Usuario.class);
		
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		
	}
	
	@Test
	@DisplayName ("Mostrar todos os usuários")
	@Order(2)
	public void deveMostrarTodosOsUsuarios() {
		
		ResponseEntity<String> resposta = testRestTemplate
				.withBasicAuth("admin@admin.com.br", "admin123")
				.exchange("/usuarios", HttpMethod.GET, null, String.class);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		
	}
	
	@Test
	@DisplayName("Atualizar um usuário")
	@Order(3)
	public void deveAtualizarUsuario() {
		
		HttpEntity<Usuario> requisicao = new HttpEntity<>(usuarioUpdate);
		
		ResponseEntity<Usuario> resposta = testRestTemplate
				.withBasicAuth("admin@admin.com.br", "admin123")
				.exchange("/usuarios/alterar", HttpMethod.PUT, requisicao, Usuario.class);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
}
