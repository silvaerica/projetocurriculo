package ifrn.projeto.curriculos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.projeto.curriculos.models.Curriculo;
import ifrn.projeto.curriculos.models.Solicitacao;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>{

	List<Solicitacao> findByCurriculo(Curriculo curriculo);
}
