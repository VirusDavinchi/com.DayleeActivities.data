	package com.DayleeActivities.data.dao;
	
	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.stereotype.Repository;
	
	import com.DayleeActivities.data.entity.AgendaTarea;
	
	
	@Repository
	public interface IAgendaTarea extends JpaRepository<AgendaTarea,Long>{
		
		
		
	
	}
