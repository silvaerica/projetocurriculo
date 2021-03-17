package ifrn.projeto.curriculos.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ifrn.projeto.curriculos.models.Curriculo;
import ifrn.projeto.curriculos.models.Empresa;
import ifrn.projeto.curriculos.repositories.CurriculoRepository;

@Controller
public class CurriculoController {

	@Autowired
	CurriculoRepository cr;
	
	
	@GetMapping("formCurriculo")
	public String formCurriculo(Curriculo curriculo) {
		return "formCurriculo";
	}
	
	@PostMapping("formCurriculo")
	public String salvarCurriculo(@Valid Curriculo curriculo, BindingResult result, RedirectAttributes atributos) {
	
		if(result.hasErrors()) {
			return formCurriculo(curriculo);
		}
	
		cr.save(curriculo);
		atributos.addFlashAttribute("mensagem", "Currículo cadastrado com sucesso!");
		
		return "redirect:/formCurriculo";
	}
	
	@GetMapping("/listarCurriculo")
	public ModelAndView listarCurriculo() {
		List<Curriculo> curriculos = cr.findAll();
		ModelAndView md = new ModelAndView("/listarCurriculo");
		md.addObject("curriculos", curriculos);
		return md;
	}
	
	@GetMapping("/{id}/selecionarCurriculo")
	public ModelAndView selecionarCurriculo(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Curriculo> opt = cr.findById(id);
		if(opt.isEmpty()) {
			md.setViewName("redirect:/formCurriculo");
			return md;
		}
		
		Curriculo curriculo = opt.get();
		md.setViewName("formCurriculo");
		md.addObject("curriculo", curriculo);
		return md;
	}
	
	@GetMapping("/{id}/removerCurriculo")
	public String apagarCurriculo(@PathVariable Long id, RedirectAttributes attributes) {
		
		Optional<Curriculo> opt = cr.findById(id);
		
		if (!opt.isEmpty()) {
			Curriculo curriculo = opt.get();
			
			cr.delete(curriculo);
			attributes.addFlashAttribute("mensagem", "Curriculo removido com sucesso!");
			
		}
		
		return "redirect:/formCurriculo";
		
	}
	
	
	@GetMapping("/{id}/detalharCurriculo")
	public ModelAndView detalharCurriculo(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Curriculo> opt = cr.findById(id);
		
		if (opt.isEmpty()) {
			md.setViewName("redirect:/index");
			return md;

		}

		md.setViewName("detalhesCurriculo");
		Curriculo curriculo = opt.get();
        md.addObject("curriculo", curriculo);
        
		return md;
	}
}
