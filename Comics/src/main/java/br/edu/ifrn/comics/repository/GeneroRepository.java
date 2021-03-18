package br.edu.ifrn.comics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import br.edu.ifrn.comics.dominio.Genero;

/** A classe GeneroRepository providencia métodos necessários para o repositório do gênero.*/
public interface GeneroRepository extends JpaRepository<Genero, Integer> {
	@Query("select g from Genero g where g.nome like %:nome%")
	List<Genero> findByNome(@Param("nome") String nome);
	
}
