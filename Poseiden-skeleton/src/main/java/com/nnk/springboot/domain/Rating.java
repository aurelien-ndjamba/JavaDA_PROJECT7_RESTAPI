package com.nnk.springboot.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rating")
/**
 * entité "rating"
 */
public class Rating {
	// TODO: Map columns in data table RATING with corresponding java fields -> OK
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	String moodysRating;
	String sandPRating;
	String fitchRating;
	Integer orderNumber;
	
}
