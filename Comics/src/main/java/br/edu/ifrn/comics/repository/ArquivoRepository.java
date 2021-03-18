package br.edu.ifrn.comics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.comics.dominio.Arquivo;

/** A classe ArquivoRepository representa o repositório do arquivo.*/
public interface ArquivoRepository extends JpaRepository<Arquivo, Long>{
}
