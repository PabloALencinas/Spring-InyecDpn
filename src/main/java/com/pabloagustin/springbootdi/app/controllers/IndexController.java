package com.pabloagustin.springbootdi.app.controllers;

import com.pabloagustin.springbootdi.app.models.service.IServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	// Principio de Hollywood! No lo llames, nosotros te llamamos -> Para inyeccion de dependencia.


	// @AutoWired para INYECTAR la dependencia de miservicio desde la clase MiServicio -> Objeto que esta registrado
	// en el contenedor Spring.
	// Basicamente BUSCA algun objeto que este guardado en el contenedor de SPRING que sea de ese dato justamente: MiServicio
	// y lo INYECTA. SI no lo encuentra, error, no esta registrado

	// CONCLUSION -> INYECTANDO el objeto MiServicio utilizando el principio de HOLLYWOOD (No nos llames, Nosotros te llamamos)

	// Inyectando desde la interfaz
	@Autowired
	private IServicio miServicio;

	// Metodo Handler, retorna una vista. Models para pasar datos a la vista
	@GetMapping({"/", "/index", ""})
	public String index(Model model){
		model.addAttribute("objeto", miServicio.operacion());
		return "index";
	}


















	// INYECTAMOS desde el SETTER
	// Generamos el setter de servicio en este caso!
	// Movemos el @AutoWired hasta este Setter de miServicio

	//	public void setMiServicio(IServicio miServicio) {
	//		this.miServicio = miServicio;
	//	}

	// INYECTANDO desde el CONSTRUCTOR generado

	//	@Autowired
	//	public IndexController(IServicio miServicio) {
	//		this.miServicio = miServicio;
	//	}
}
