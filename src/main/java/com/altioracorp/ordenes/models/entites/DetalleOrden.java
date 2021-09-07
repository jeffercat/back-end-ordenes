package com.altioracorp.ordenes.models.entites;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="detalles_ordenes")
public class DetalleOrden implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "no puede estar vacío.")
	@Size(min=4, max = 10, message = "el tamaño tiene que estar entre 4 y 10")
	@Column(nullable = false, length = 10)
	private String codigo;
	 
	@NotEmpty(message = "no puede estar vacío.")
	@Size(min=4, max = 50, message = "el tamaño tiene que estar entre 4 y 50")
	@Column(nullable = false, length = 50)
	private String nombre;

	@Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2 )
	private BigDecimal precioUnitario;

	@Column(name = "cantidad")
	private Long cantidad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="orden_id", nullable = false)
	@JsonIgnore
	private Orden orden;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}



	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}



	public Orden getOrden() {
		return orden;
	}



	public void setOrden(Orden orden) {
		this.orden = orden;
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
