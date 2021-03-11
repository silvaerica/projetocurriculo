package ifrn.projeto.curriculos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.projeto.curriculos.models.Empresa;


public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

}
