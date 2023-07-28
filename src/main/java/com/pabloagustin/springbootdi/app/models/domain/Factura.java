package com.pabloagustin.springbootdi.app.models.domain;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;


import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Component
// Dura lo que dura una peticion HTTP de usuario
// Cada usuario que se conecte va a tener una factura distinta y propia
// Si modificamos un valor no se modifica, no se altera al resto
// Vemos si compilamos, actualizamos el nombre se va generando cada actualizacion ya que no es mas singleton
@ApplicationScope
public class Factura implements Serializable {


	@Serial
	private static final long serialVersionUID = 8781707435528057333L;

	@Value("${factura.descripcion}")
	private String descripcion;
	// Factura tiene una relacion con CLIENTE, por lo que es atributo!
	// Lo INYECTAMOS
	@Autowired
	private Cliente cliente;

	// Factura tiene relacion con ITEMFACTURA(muchos -> LIST : Collection), por lo que es atributo tambien!
	// Lo INYECTAMOS cuando lo registremos en la clase CONFIGURACION

	@Autowired
	private List<ItemFactura> items;

	// SE EJECUTA JUSTO DESPUES DE INSTANCIAR EL OBJETO Y DE INYECTAR LA INDEPENDENCIA
	@PostConstruct
	public void inicializar(){
		cliente.setNombre(cliente.getNombre().concat(" ").concat("Agustin").concat(" ").concat(cliente.getApellido()));
		descripcion = descripcion.concat(" del cliente: ").concat(cliente.getNombre());
	}

	// Dura hasta que bajemos la aplicacion, hasta que hagamos el undeploy -> CUIDADO! VER MAS!
	// VEMOS CUANDO HACEMOS EL STOP DE LA APP!
	@PreDestroy
	public void destruir(){
		System.out.println("Factura destruida: ".concat(descripcion));
	}

	// Getters and Setters

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}
}
