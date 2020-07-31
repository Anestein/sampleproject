package com.inventory.originssoft.inventoryserver.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers.IntegerSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "education")
public class Education implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;

	@Id
	@Column(name = "education_id", updatable = false, unique=true)
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonSerialize(using=IntegerSerializer.class)
	private int id;
	
	@Column(name = "instition_name")
	@NotNull
	@JsonSerialize(using=StringSerializer.class)
	private String institution;
	
	@Column(name = "degrees_course_name")
	@NotNull
	@JsonSerialize(using=StringSerializer.class)
	private String degrees_courses;
	
	@Column(name = "graduation_date")
	private Date graduationDate;
	
	@Column(name = "discription")
	@NotNull
	@JsonSerialize(using=StringSerializer.class)
	private String discription;
	
}
