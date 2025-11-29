package com.DayleeActivities.data.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
@Table(name = "Agenda_tarea")
public class AgendaTarea {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_Agenda_Tarea")
	    private Long idAgendaTarea;	  

	    @ManyToOne
	    @JoinColumn(name = "frk_id_Concentracion")
	    private Concentracion concentracion; 

	    @Column(name = "estatus")
	    private String estatus; 
	    
		@OneToMany(mappedBy = "agendatarea", cascade = { CascadeType.ALL, CascadeType.MERGE,
				CascadeType.REMOVE }, fetch = FetchType.LAZY, orphanRemoval = true)
		private List<TareaDetalle> tareaDetalle;
	 
	    
	    @Column(name = "CREATED_AT", nullable = false, columnDefinition = "TIMESTAMP")
	    private LocalDateTime createdAt;

	    @Column(name = "UPDATED_AT", columnDefinition = "TIMESTAMP")
	    private LocalDateTime updatedAt;
	
	

}
