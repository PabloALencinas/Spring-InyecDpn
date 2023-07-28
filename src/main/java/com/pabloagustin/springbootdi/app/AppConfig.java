package com.pabloagustin.springbootdi.app;

import com.pabloagustin.springbootdi.app.models.domain.ItemFactura;
import com.pabloagustin.springbootdi.app.models.domain.Producto;
import com.pabloagustin.springbootdi.app.models.service.IServicio;
import com.pabloagustin.springbootdi.app.models.service.MiServicio;
import com.pabloagustin.springbootdi.app.models.service.MiServicioComplejo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AppConfig {

	@Bean("miServicioSimple")
	public IServicio registrarMiServicio(){
		return new MiServicio();
	}

	@Bean("miServicioComplejo")
	@Primary
	public IServicio registrarMiServicioComplejo(){
		return new MiServicioComplejo();
	}

	// Escenario FACTURAS

	// Para los items de la factura
	@Bean("itemsFactura")
	@Primary
	public List<ItemFactura> registrarItems(){
		Producto producto1 = new Producto("Camara Sony", 100.0);
		Producto producto2 = new Producto("Bici Venzo, rodado 29", 200.0);

		// un ITEMFACTURA esta asociado a un producto y su cantidad

		ItemFactura linea1 = new ItemFactura(producto1, 2);
		ItemFactura linea2 = new ItemFactura(producto2, 40);

		return Arrays.asList(linea1, linea2);
	}

	@Bean("itemsFacturaOficina")
	public List<ItemFactura> registrarItemsOficina(){
		Producto producto1 = new Producto("Monitor LG 4K 29'", 300.0);
		Producto producto2 = new Producto("Notebook ASUS", 1000.0);
		Producto producto3 = new Producto("Silla Oficinas", 100.0);
		Producto producto4 = new Producto("Escritorio Doble", 300.0);

		// un ITEMFACTURA esta asociado a un producto y su cantidad

		ItemFactura linea1 = new ItemFactura(producto1, 3);
		ItemFactura linea2 = new ItemFactura(producto2, 1);
		ItemFactura linea3 = new ItemFactura(producto3, 5);
		ItemFactura linea4 = new ItemFactura(producto4, 2);

		return Arrays.asList(linea1, linea2, linea3, linea4);
	}

}
