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
import com.DayleeActivities.data.entity.CategoriaTarea;
import com.DayleeActivities.data.services.ICategoriaTareaService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.v3.oas.annotations.Operation;



@RestController
@RequestMapping("/api")
public class CategoriaTareaController {
	
	
	    private static final Logger logger = LoggerFactory.getLogger(CategoriaTareaController.class);
	
	    @Autowired
	    ICategoriaTareaService iCategoriaTareaService;
	    
	    @Operation(summary = "Obtiene todos las Categorias tarea")
	    @GetMapping("/CategoriaTarea")
	    public ResponseEntity<?> getCategoriaTareaAll() {
	    	 logger.info("Iniciando solicitud para obtener todos las Categorias tarea");

	    	    List<CategoriaTarea> CategoriaTareaList = iCategoriaTareaService.findAll();

	    	    if (CategoriaTareaList.isEmpty()) {
	    	        logger.warn("No se encontraron Categorias tarea en la base de datos");
	    	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	    	                .body("No se encontraron Categorias tarea");
	    	    } else {
	    	        logger.info("Se encontraron {} Categorias tarea en total", CategoriaTareaList.size());
	    	        return ResponseEntity.ok(CategoriaTareaList);
	    	    }
	    }
	    
	    @Operation(summary = "Obtnener detalle por Id")
	    @GetMapping("/CategoriaTarea/{id}")
	    public ResponseEntity<?> getCategoriaTareayId(@PathVariable Long id) {
	        logger.info("Obteniendo Categoria por Id: {}");
	       
	        try {
	            Optional<CategoriaTarea> tipoOptional = iCategoriaTareaService.findById(id);
	            if (tipoOptional.isPresent()) {
	            	 logger.info("Categoria encontrado ID: {}", id);
	            	 CategoriaTarea tipo = tipoOptional.get();
	                return ResponseEntity.ok(tipo);
	            } else {
	            	logger.warn("Categoria no encontrado con ID: {}", id);
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria no encontrado  con ID: " + id);
	            }
	        } catch (Exception e) {
	            logger.error("Error interno al obtener Categoria con ID: {}", id, e);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
	        }
	    }
	    
	    @PostMapping("/CategoriaTarea")
	    public ResponseEntity<?> createCategoriaTarea(@RequestBody CategoriaTarea CategoriaTarea) {
	        logger.info("Creating a new CategoriaTarea");
	        
	        Map<String, Object> response = new HashMap<>();
	        CategoriaTarea CategoriaTareaNew;
	        try {
	           
	        	CategoriaTareaNew = iCategoriaTareaService.save(CategoriaTarea);
	        	response.put("mensaje", "El registro ha sido creado con éxito!");
	 	        response.put("CategoriaTarea", CategoriaTareaNew);
	 	        return new ResponseEntity<>(response, HttpStatus.CREATED);
	        } catch (Exception e) {
	            logger.error("Error creating CategoriaTarea", e);
	            response.put("mensaje", "Error al hacer la inserción a la base de datos");
	            response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
	            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	       
	    }
	    
	    @PutMapping("/CategoriaTarea/{id}")	    
	    public ResponseEntity<?> updateCategoriaTarea(@RequestBody CategoriaTarea CategoriaTarea, @PathVariable Long id) {
	    	logger.info("Updating CategoriaTarea with ID: {}", id);
	       
	        Map<String, Object> response = new HashMap<>();
	        Optional<CategoriaTarea> optionalTareaActual = iCategoriaTareaService.findById(id);
	        if (!optionalTareaActual.isPresent()) {
	            logger.info("CategoriaTarea not found by ID: {}", id);
	            response.put("mensaje", "Error: no se pudo editar, el Tarea Detalle ID: ".concat(id.toString().concat(" no existe en la base de datos")));
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }
	        CategoriaTarea CategoriaTareaActual = optionalTareaActual.get();
	        CategoriaTarea CategoriaTareaActualizado = null;
	        try {
	        	CategoriaTareaActual.setDescripción(CategoriaTarea.getDescripción());	        	
	        	CategoriaTareaActualizado = iCategoriaTareaService.save(CategoriaTareaActual);
	        } catch (Exception e) {
	        	logger.error("Error updating Tarea Detalle", e);
	            response.put("mensaje", "Error al actualizar el Tarea Detalle en la base de datos");
	            response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
	            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        response.put("mensaje", "El Tarea Detalle ha sido actualizado con éxito");
	        response.put("CategoriaTarea", CategoriaTareaActualizado);
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
	    }
	 
	    
	    @Operation(summary = "Eliminar CategoriaTarea")
	    @DeleteMapping("/CategoriaTarea/{id}")	   
	    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
	        logger.info("Eliminando Categoria con ID: {}", id);

	        Map<String, Object> response = new HashMap<>();
	        Optional<CategoriaTarea> optionalUsuario = iCategoriaTareaService.findById(id);
	        if (!optionalUsuario.isPresent()) {
	            logger.info("Categoria  no encontrado por ID: {}", id);
	            response.put("mensaje", "Error: no se pudo eliminar, Categoria ID: ".concat(id.toString().concat(" no existe en la base de datos para el CategoriaTarea actual")));
	            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	        }
	        try {
	        	iCategoriaTareaService.delete(id);
	        } catch (DataAccessException e) {
	            logger.error("Error deleting Categoria ", e);
	            response.put("mensaje", "Error al eliminar el id CategoriaTarea en la base de datos");
	            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        response.put("mensaje", "El Categoria ha sido eliminado con éxito");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	    
	    
	 

}
