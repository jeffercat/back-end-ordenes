package com.altioracorp.ordenes.models.entites;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="clientes")
public class Cliente implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "no puede estar vacío.")
	@Size(min=4, max = 25, message = "el tamaño tiene que estar entre 4 y 25")
	@Column(nullable = false, length = 25)
	private String nombre;
	
	@NotEmpty(message = "no puede estar vacío.")
	@Size(min=4, max = 25, message = "el tamaño tiene que estar entre 4 y 25")
	@Column(nullable = false, length = 25)
	private String apellido;
		
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
