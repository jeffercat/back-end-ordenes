package com.altioracorp.ordenes.models.entites;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="ordenes")
public class Orden implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, name = "fecha_creacion")
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;

	@NotEmpty(message = "no puede estar vacío.")
	@Size(min=8, max = 50, message = "el tamaño tiene que estar entre 8 y 50")
	@Column(nullable = false, length = 50)
	private String cliente;
	
	@JsonIgnoreProperties(value={"orden", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orden", cascade = CascadeType.ALL)
	private List<DetalleOrden> detallesOrdenes;
	
	public Long getId() {
		return id;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public List<DetalleOrden> getDetallesOrdenes() {
		return detallesOrdenes;
	}

	public void setDetallesOrdenes(List<DetalleOrden> detallesOrdenes) {
		this.detallesOrdenes = detallesOrdenes;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
