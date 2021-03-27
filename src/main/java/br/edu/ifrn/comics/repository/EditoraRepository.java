package br.edu.ifrn.comics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.comics.dominio.Editora;

/** A classe EditoraRepository providencia métodos necessários para o repositório da editora.*/
public interface EditoraRepository extends JpaRepository < Editora, Integer > {
  @Query("select e from Editora e where e.nome like %:nome%")
  List < Editora > findByNome(@Param("nome") String nome);
}