package br.edu.ifrn.comics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import br.edu.ifrn.comics.dominio.Hq;

/** A classe HqRepository providencia métodos necessários para o repositório da HQ.*/
public interface HqRepository extends JpaRepository<Hq, Integer> {
	
	@Query("select h from Hq h where h.titulo like %:titulo% "+
			" and h.editora.nome like %:editora% ")
	List<Hq> findByTituloAndEditora(@Param("titulo") String titulo, 
								@Param("editora") String editora);
	
}
