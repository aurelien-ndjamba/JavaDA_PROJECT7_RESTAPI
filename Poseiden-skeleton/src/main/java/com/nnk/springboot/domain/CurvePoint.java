package com.nnk.springboot.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "curvepoint")
/**
 * entité "curvepoint"
 */
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotNull(message = "must not be null") 
	private Integer curveId;
	private Timestamp asOfDate;
	private Double term;
	private Double value;  
	@CreationTimestamp
	private Timestamp creationDate;
	
}
