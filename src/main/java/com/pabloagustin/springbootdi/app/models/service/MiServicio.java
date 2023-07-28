package com.pabloagustin.springbootdi.app.models.service;

// De momento no vamos a usar inyeccion de dependencia -> @Controller o @Service
// Solo una clase con un metodo

// Componente -> Registrar en el contenedor, queda registrado como un Bean en Spring
// Se puede Inyectar en otros componentes de nuestra aplicacion
// GRACIAS A ESTO PODREMOS USAR EL @AUTOWIRED PARA 'INYECTAR' EN EL CONTROLADOR
// @Component("miServicioSimple")
// @Primary
// Service tambien: Son iguales, ambas representan una clase de servicio dentro de spring. Diferencia -> Semantica UNICAMENTE.
// Funcionalidad = a component
// @Service
public class MiServicio implements IServicio {

	@Override
	public String operacion(){
		return "Ejecutando algun proceso importante simple...";
	}

}
