package com.DayleeActivities.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DayleeActivities.data.entity.Tarea;


@Repository
public interface ITarea extends JpaRepository<Tarea,Long>{

}
