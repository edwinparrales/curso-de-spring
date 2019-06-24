package com.parrales.project.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.parrales.project.models.entity.Cliente;
import com.parrales.project.models.services.IClienteService;

@Controller
//@Qualifier("ClienteDaoJpa") em caso que varias clases implemente la misma interfaz
@SessionAttributes("cliente")
public class ClienteController {
	@Autowired
	private IClienteService clienteService;
	@RequestMapping(value ="/listar",method=RequestMethod.GET)
	public String listar(@RequestParam(name="page",defaultValue = "0")int page, Model model) {
	  
		Pageable pageable = PageRequest.of(page, 4);
		Page<Cliente> clientes=clienteService.findAll(pageable);
		model.addAttribute("titulo","Listado de clientes");
		model.addAttribute("clientes",clientes);
		
		return "listar";
	}
	
	@RequestMapping(value="/form")
	public String crear(Map<String,Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo","Formulario registrar cliente");
		return "form";
		
	}
	
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente = null;

		if (id <= 0) {
			flash.addFlashAttribute("error", "El id Cliente no puede ser cero");
		
			return "redirect:/listar";

		} else {
			cliente = clienteService.finOne(id);
			if (cliente == null) {
				flash.addFlashAttribute("error", "El registro del cliente no existe");
				return "redirect:/listar";
			}
			model.put("cliente", cliente);
			model.put("titulo", "Editar cliente");
			return "form";

		}
	}
	
	
	@RequestMapping(value="/form",method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente,BindingResult result,Model model,RedirectAttributes flash, SessionStatus seStatus) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo","Formulario registrar cliente");
			return "form";
		}
		
		String msm = (cliente.getId()==null?"Cliente creado con exito":"El registo id:"+ cliente.getId()+" fue actualizado con exito");
		clienteService.save(cliente);
		seStatus.setComplete();
		flash.addFlashAttribute("success",msm );
		return "redirect:listar";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id,RedirectAttributes flash) {
		if(id>0) {
			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con exito");
		}
		
		return "redirect:/listar";
	}
	

}
