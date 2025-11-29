package com.DayleeActivities.data.services;

import java.util.List;
import java.util.Optional;

import com.DayleeActivities.data.entity.CategoriaTarea;

public interface ICategoriaTareaService {
	
	 public List<CategoriaTarea> findAll();
     public CategoriaTarea save(CategoriaTarea usuario);
     public Optional<CategoriaTarea> findById(Long id);
     public void delete(Long id);
	
	

}
