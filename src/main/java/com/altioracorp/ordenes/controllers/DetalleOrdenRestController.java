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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.altioracorp.ordenes.dto.DetalleOrdenDto;
import com.altioracorp.ordenes.models.entites.DetalleOrden;
import com.altioracorp.ordenes.models.services.IDetalleOrdenService;

@CrossOrigin(origins = { "http://localhost:4200" }, methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.DELETE, RequestMethod.PUT })
@RestController
@RequestMapping("/api")
public class DetalleOrdenRestController {

	@Autowired
	private IDetalleOrdenService detalleOrdenService;

	@PostMapping("/detalles")
	public ResponseEntity<?> create(@Valid @RequestBody DetalleOrdenDto detalleOrden, BindingResult result) {
		DetalleOrden detalleOrdenNew = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			detalleOrdenNew = detalleOrdenService.save(detalleOrden);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert a la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "El detalle ha sido creado a la orden con éxito.");
		response.put("detalleOrden", detalleOrdenNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/detalles/{idOrden}")
	public List<DetalleOrden> listaDetallePorIdOrden(@PathVariable Long idOrden) {
		return detalleOrdenService.findAllByIdOrden(idOrden);
	}
}
