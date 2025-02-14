package com.nnk.springboot.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trade")
/**
 * entité "trade"
 */
public class Trade {
    // TODO: Map columns in data table TRADE with corresponding java fields -> OK
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tradeId;
	private String account;
	private String type;
	private Double buyQuantity;
	private Double sellQuantity;
	private Double buyPrice;
	private Double sellPrice;
	private String benchmark;
	private Timestamp tradeDate;
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
