package com.DayleeActivities.data.entity;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Categoria_Tarea")
public class CategoriaTarea {
	
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_Categoria_tarea")
	    private Long id_Categoria_tarea;

	    @Column(name = "descripción")
	    private String descripción;  
	     
	    @JsonIgnore  
	    @Column(name = "CREATED_AT", nullable = false, columnDefinition = "TIMESTAMP")
	    private LocalDateTime createdAt;

	    //Con esta anotacion, no es necesario mandar el create at
	    @PrePersist
	    protected void onCreate() {
	        this.createdAt = LocalDateTime.now();
	    }
	

}
