package com.pabloagustin.springbootdi.app.models.service;

// @Component("miServicioComplejo")

public class MiServicioComplejo implements IServicio{

	@Override
	public String operacion(){
		return "Ejecutando algun proceso importante complejo...";
	}
}
