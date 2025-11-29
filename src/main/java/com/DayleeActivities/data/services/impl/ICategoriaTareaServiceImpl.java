package com.DayleeActivities.data.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DayleeActivities.data.dao.ICategoriaTarea;
import com.DayleeActivities.data.entity.CategoriaTarea;
import com.DayleeActivities.data.services.ICategoriaTareaService;
import org.springframework.transaction.annotation.Transactional;




@Service
public class ICategoriaTareaServiceImpl implements ICategoriaTareaService{
	
	
	
	@Autowired
	ICategoriaTarea iCategoriaTarea;

	@Override
	@Transactional(readOnly = true)
	public List<CategoriaTarea> findAll() {		
		return  (List<CategoriaTarea>) iCategoriaTarea.findAll();
	}

	@Override
	@Transactional
	public CategoriaTarea save(CategoriaTarea usuario) {		
		return iCategoriaTarea.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<CategoriaTarea> findById(Long id) {		
		return iCategoriaTarea.findById(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		iCategoriaTarea.deleteById(id);
		
	}
	


}
