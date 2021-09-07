package com.altioracorp.ordenes.models.entites;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="articulos")
public class Articulo implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "no puede estar vacío.")
	@Size(min=4, max = 10, message = "el tamaño tiene que estar entre 4 y 10")
	@Column(nullable = false, length = 10, unique = true)
	private String codigo;
	
	@NotEmpty(message = "no puede estar vacío.")
	@Size(min=4, max = 50, message = "el tamaño tiene que estar entre 4 y 50")
	@Column(nullable = false, length = 50)
	private String nombre;

	@Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2 )
	private BigDecimal precioUnitario;
		
	@Column(name = "cantidad")
	private Long cantidad;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
