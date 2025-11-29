package com.DayleeActivities.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.DayleeActivities.data.entity.CategoriaTarea;



@Repository
public interface ICategoriaTarea extends JpaRepository<CategoriaTarea,Long> {

}
