package ifrn.projeto.curriculos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.projeto.curriculos.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByEmail(String email);
	

}
