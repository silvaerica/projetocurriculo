package ifrn.projeto.curriculos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.projeto.curriculos.models.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long> {

	Papel findByNome(String nome);
	
}
