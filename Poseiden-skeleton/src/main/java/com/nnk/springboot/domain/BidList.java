package com.nnk.springboot.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bidlist")
@Validated

/**
 * entité "bidlist"
 */
public class BidList {
    // TODO: Map columns in data table BIDLIST with corresponding java fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer BidListId;
	@NotBlank(message = "Account is mandatory")
	private String account;
	@NotBlank(message = "Type is mandatory")
	private String type;
	private Double bidQuantity;
	private Double askQuantity;
	private Double bid;
	private Double ask;
	private String benchmark; 
	private Timestamp bidListDate;
	private String commentary;
	private String security;
	private String status;
	private String trader;
	private String book; 
	private String creationName;
	@CreationTimestamp
	private Timestamp creationDate;
	private String revisionName;
	private Timestamp revisionDate;
	private String dealName;
	private String dealType;
	private String sourceListId;
	private String side;
	
}
