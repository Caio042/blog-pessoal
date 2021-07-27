package com.generation.blogPessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogPessoal.model.Publicacao;


@Repository
public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {

	public List<Publicacao> findAllByTituloContainingIgnoreCase (String titulo);
}