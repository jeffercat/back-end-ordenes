package com.altioracorp.ordenes.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.altioracorp.ordenes.models.entites.Orden;
import com.altioracorp.ordenes.models.services.IOrdenService;


@CrossOrigin(origins = { "http://localhost:4200" }, methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.DELETE, RequestMethod.PUT })
@RestController
@RequestMapping("/api")
public class OrdenRestController {

	@Autowired
	private IOrdenService ordenService;

	@GetMapping("/ordenes")
	public List<Orden> index() {
		return ordenService.findAll();
	}

	@GetMapping("/ordenes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Orden orden = null;
		Map<String, Object> response = new HashMap<>();
		try {
			orden = ordenService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (orden == null) {
			response.put("mensaje", "La Orden ID: ".concat(id.toString().concat(" no existe en la base de datos.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Orden>(orden, HttpStatus.OK);
	}

	@PostMapping("/ordenes") 
	public ResponseEntity<?> create(@Valid @RequestBody Orden orden, BindingResult result) {
		Orden ordenNew = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		try {
			ordenNew = ordenService.save(orden);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert a la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "La Orden ha sido creado con éxito.");
		response.put("orden", ordenNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/ordenes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Orden orden, BindingResult result, @PathVariable Long id) {
		Orden ordenActual = ordenService.findById(id);
		Orden ordenUpdated = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (ordenActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la orden ID: "
					.concat(id.toString().concat(" no existe en la base de datos.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			//ordenActual.setNombre(orden.getNombre());
			ordenUpdated = ordenService.save(ordenActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la orden en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "La orden ha sido actualizada con éxito.");
		response.put("orden", ordenUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/ordenes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			ordenService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la orden en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La orden ha sido eliminado con éxito.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
