package ifrn.projeto.curriculos.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ifrn.projeto.curriculos.models.Papel;
import ifrn.projeto.curriculos.models.Usuario;
import ifrn.projeto.curriculos.repositories.PapelRepository;
import ifrn.projeto.curriculos.repositories.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository ur;

	@Autowired
	private PapelRepository pr;

	@GetMapping("/cadastrar")
	public String form(Usuario usuario) {
		return "usuarios/form";
	}
	
	@PostMapping("/cadastrar")
	public String salvarUsuario(@Valid Usuario usuario, BindingResult result , RedirectAttributes atributos) {
		if (result.hasErrors()) {
			return form(usuario);
		}
		ArrayList<Papel> papeis =new ArrayList<Papel>();
		
		Papel papel =pr.findByNome("ROLE_USU_CADASTRADO");
		papeis.add(papel);
		
		usuario.setPapeis(papeis);
		
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		
		ur.save(usuario);
		atributos.addFlashAttribute("mensagem", "usuario cadastrado");
		return "redirect:/login";
	}
}
