package com.DayleeActivities.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DayleeActivities.data.entity.Tarea;
import com.DayleeActivities.data.services.ITareaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.v3.oas.annotations.Operation;



@RestController
@RequestMapping("/api")
public class TareaController {
	
	
	    private static final Logger logger = LoggerFactory.getLogger(TareaController.class);
	    
	    @Autowired
	    ITareaService iTareaService;
	    
	    @Operation(summary = "Obtiene todos las tareas detalle")
	    @GetMapping("/tarea")
	    public ResponseEntity<?> getTareaDetalleAll() {
	    	 logger.info("Iniciando solicitud para obtener todos las tareas detalle");

	    	    List<Tarea> tareaDetalleList = iTareaService.findAll();

	    	    if (tareaDetalleList.isEmpty()) {
	    	        logger.warn("No se encontraron tareas detalle en la base de datos");
	    	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	    	                .body("No se encontraron tareas detalle");
	    	    } else {
	    	        logger.info("Se encontraron {} tareas detalle en total", tareaDetalleList.size());
	    	        return ResponseEntity.ok(tareaDetalleList);
	    	    }
	    }
	    
	    /*
	    @Operation(summary = "Obtnener detalle por Id")
	    @GetMapping("/tareaDetalle/{id}")
	    public ResponseEntity<?> getTareaDetalleyId(@PathVariable Long id) {
	        logger.info("Obteniendo Tarea Detalle por Id: {}");
	       
	        try {
	            Optional<Tarea> tipoOptional = iTareaDetalleService.findById(id);
	            if (tipoOptional.isPresent()) {
	            	 logger.info("Tarea Detalle encontrado ID: {}", id);
	            	 Tarea tipo = tipoOptional.get();
	                return ResponseEntity.ok(tipo);
	            } else {
	            	logger.warn("Detalle no encontrado con ID: {}", id);
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea Detalle no encontrado  con ID: " + id);
	            }
	        } catch (Exception e) {
	            logger.error("Error interno al obtener Tarea Detalle con ID: {}", id, e);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
	        }
	    }
	    
	    @PostMapping("/tareaDetalle")
	    public ResponseEntity<?> createTareaDetalle(@RequestBody Tarea tareaDetalle) {
	        logger.info("Creating a new tareaDetalle");
	        
	        Map<String, Object> response = new HashMap<>();
	        Tarea tareaDetalleNew;
	        try {
	           
	        	tareaDetalleNew = iTareaDetalleService.save(tareaDetalle);
	        } catch (Exception e) {
	            logger.error("Error creating tareaDetalle", e);
	            response.put("mensaje", "Error al hacer la inserción a la base de datos");
	            response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
	            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        response.put("mensaje", "El registro ha sido creado con éxito!");
	        response.put("tareaDetalle", tareaDetalleNew);
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
	    }
	    
	    @PutMapping("/tareaDetalle/{id}")	    
	    public ResponseEntity<?> updateTareaDetalle(@RequestBody Tarea tareaDetalle, @PathVariable Long id) {
	    	logger.info("Updating TareaDetalle with ID: {}", id);
	       
	        Map<String, Object> response = new HashMap<>();
	        Optional<Tarea> optionalTareaActual = iTareaDetalleService.findById(id);
	        if (!optionalTareaActual.isPresent()) {
	            logger.info("TareaDetalle not found by ID: {}", id);
	            response.put("mensaje", "Error: no se pudo editar, el Tarea Detalle ID: ".concat(id.toString().concat(" no existe en la base de datos")));
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }
	        TareaController tareaDetalleActual = optionalTareaActual.get();
	        TareaController tareaDetalleActualizado = null;
	        try {
	        	tareaDetalleActual.setDescripción(tareaDetalle.getDescripción());
	        	
	        	tareaDetalleActualizado = iTareaDetalleService.save(tareaDetalleActual);
	        } catch (Exception e) {
	        	logger.error("Error updating Tarea Detalle", e);
	            response.put("mensaje", "Error al actualizar el Tarea Detalle en la base de datos");
	            response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
	            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        response.put("mensaje", "El Tarea Detalle ha sido actualizado con éxito");
	        response.put("tareaDetalle", tareaDetalleActualizado);
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
	    }
	 
	    
	    @Operation(summary = "Eliminar TareaDetalle")
	    @DeleteMapping("/tareaDetalle/{id}")	   
	    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
	        logger.info("Eliminando Tarea Detalle con ID: {}", id);

	        Map<String, Object> response = new HashMap<>();
	        Optional<Tarea> optionalUsuario = iTareaDetalleService.findById(id);
	        if (!optionalUsuario.isPresent()) {
	            logger.info("Tarea Detalle  no encontrado por ID: {}", id);
	            response.put("mensaje", "Error: no se pudo eliminar, el Usuario ID: ".concat(id.toString().concat(" no existe en la base de datos para el TareaDetalle actual")));
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }
	        try {
	        	iTareaDetalleService.delete(id);
	        } catch (DataAccessException e) {
	            logger.error("Error deleting Tarea Detalle ", e);
	            response.put("mensaje", "Error al eliminar el id TareaDetalle en la base de datos");
	            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        response.put("mensaje", "El Tarea Detalle  ha sido eliminado con éxito");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	    
	    
	 
*/
}
