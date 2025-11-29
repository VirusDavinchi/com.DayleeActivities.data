package com.DayleeActivities.data.services;

import java.util.List;
import java.util.Optional;
import com.DayleeActivities.data.entity.Tarea;



public interface ITareaService {

	
	 public List<Tarea> findAll();
     public Tarea save(Tarea usuario);
     public Optional<Tarea> findById(Long id);
     public void delete(Long id);
	
}
