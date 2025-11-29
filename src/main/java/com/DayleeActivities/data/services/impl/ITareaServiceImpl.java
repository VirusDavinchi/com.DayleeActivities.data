package com.DayleeActivities.data.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DayleeActivities.data.dao.ITarea;
import com.DayleeActivities.data.entity.Tarea;
import com.DayleeActivities.data.services.ITareaService;



@Service
public class ITareaServiceImpl implements ITareaService{
	
	
	
	@Autowired
	ITarea iTarea;

	@Override
	public List<Tarea> findAll() {
		
		return iTarea.findAll();
	}

	@Override
	public Tarea save(Tarea usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Tarea> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
