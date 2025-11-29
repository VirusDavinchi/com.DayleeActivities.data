package com.DayleeActivities.data.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tarea_detalle")
public class TareaDetalle {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarea_detalle")
    private Long idTareaDetalle;
	
	 @JsonIgnore
	 @ManyToOne
	 @JoinColumn(name = "id_tarea")
	 private Tarea tarea; 
	  
	 @JsonIgnore
	 @ManyToOne
	 @JoinColumn(name = "id_Agenda_Tarea", nullable = false)
	 private AgendaTarea agendatarea; 


 
    @Column(name = "hora_fin")
    private LocalTime horaFin;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "tiempo_estudio")
    private Long tiempoEstudio;
	
	
	
	

}
