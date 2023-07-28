package com.pabloagustin.springbootdi.app.models.domain;

public class ItemFactura {

	// RELACION con PRODUCTO
	private Producto producto;
	private Integer cantidad;

	// Constructor
	public ItemFactura(Producto producto, Integer cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	// Getters and Setters
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double calcularImporte(){
		return cantidad * producto.getPrecio();
	}
}
