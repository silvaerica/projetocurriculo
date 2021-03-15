package ifrn.projeto.curriculos.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ifrn.projeto.curriculos.models.Empresa;
import ifrn.projeto.curriculos.repositories.EmpresaRepository;

@Controller
public class EmpresaController {

	@Autowired
	EmpresaRepository er;
	
	@GetMapping ("/formEmpresa")
	public String form(Empresa empresa) {
		return "formEmpresa";
	}
	
	@PostMapping("formEmpresa")
	public String salvar(@Valid Empresa empresa, BindingResult result, RedirectAttributes atributos) {
		if(result.hasErrors()) {
			return form(empresa);
		}
		
		er.save(empresa);
		atributos.addFlashAttribute("mensagem", "Empresa cadastrada com sucesso!");
		
		return "redirect:/formEmpresa";
	}
	
	@GetMapping("/listarEmpresa")
	public ModelAndView listar() {
		List<Empresa> empresas = er.findAll();
		ModelAndView md = new ModelAndView("/listarEmpresa");
		md.addObject("empresas", empresas);
		return md;
	}
}
