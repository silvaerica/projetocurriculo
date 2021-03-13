package ifrn.projeto.curriculos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.projeto.curriculos.models.Curriculo;

public interface CurriculoRepository extends JpaRepository<Curriculo, Long> {

	
}
