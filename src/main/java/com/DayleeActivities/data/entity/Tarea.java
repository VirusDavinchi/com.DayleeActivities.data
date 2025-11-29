package com.DayleeActivities.data.entity;


import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tarea")
public class Tarea {
	
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_tarea")
	    private Long idTarea;

	    @Column(name = "descripción")
	    private String descripción;
	    
	    @ManyToOne
	    @JoinColumn(name = "frk_id_categoria_tarea")
	    private CategoriaTarea categoriaTarea; 
	    
		@OneToMany(mappedBy = "tarea", cascade = { CascadeType.ALL, CascadeType.MERGE,
				CascadeType.REMOVE }, fetch = FetchType.LAZY, orphanRemoval = true)
		private List<TareaDetalle> tareaDetalle;
	    
	    
	    @JsonIgnore 
	    @Column(name = "CREATED_AT", nullable = false, columnDefinition = "TIMESTAMP")
	    private LocalDateTime createdAt;


	

}
