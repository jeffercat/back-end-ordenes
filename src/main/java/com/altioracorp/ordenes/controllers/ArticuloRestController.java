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

import com.altioracorp.ordenes.models.entites.Articulo;
import com.altioracorp.ordenes.models.services.IArticuloService;

@CrossOrigin(origins = { "http://localhost:4200" }, methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.DELETE, RequestMethod.PUT })
@RestController
@RequestMapping("/api")
public class ArticuloRestController {

	@Autowired
	private IArticuloService articuloService;

	@GetMapping("/articulos")
	public List<Articulo> index() {
		return articuloService.findAll();
	}

	@GetMapping("/articulos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Articulo articulo = null;
		Map<String, Object> response = new HashMap<>();
		try {
			articulo = articuloService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (articulo == null) {
			response.put("mensaje", "El artículo ID: ".concat(id.toString().concat(" no existe en la base de datos.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Articulo>(articulo, HttpStatus.OK);
	}

	@PostMapping("/articulos")
	public ResponseEntity<?> create(@Valid @RequestBody Articulo articulo, BindingResult result) {
		Articulo articuloNew = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}
		try {
			articuloNew = articuloService.save(articulo);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert a la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "El artículo ha sido creado con éxito.");
		response.put("articulo", articuloNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/articulos/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Articulo articulo, BindingResult result,
			@PathVariable Long id) {
		Articulo articuloActual = articuloService.findById(id);
		Articulo articuloUpdated = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (articuloActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el artículo ID: "
					.concat(id.toString().concat(" no existe en la base de datos.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			articuloActual.setCodigo(articulo.getCodigo());
			articuloActual.setNombre(articulo.getNombre());
			articuloActual.setPrecioUnitario(articulo.getPrecioUnitario());
			articuloUpdated = articuloService.save(articuloActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el artículo en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "El artículo ha sido actualizado con éxito.");
		response.put("articulo", articuloUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/articulos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			articuloService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el artículo en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El artículo ha sido eliminado con éxito.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
