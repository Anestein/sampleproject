package com.inventory.originssoft.inventoryserver.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers.IntegerSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employment_experience")
@Getter
@Setter
public class Employment implements Serializable {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
		
	@Id
	@Column(name = "employment_id", updatable = false, unique=true)
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonSerialize(using=IntegerSerializer.class)
	private int id;
	
	//@ManyToOne(fetch= FetchType.LAZY)
	//@JoinColumn(name = "resume_id")
	//private ResumeInfo resume;
	
	@Column(name = "employer")
	@NotNull
	@JsonSerialize(using=StringSerializer.class)
	private String employer;
	
	@Column(name = "client")
	@NotNull
	@JsonSerialize(using=StringSerializer.class)
	private String client;
	
	@Column(name = "project")
	@NotNull
	@JsonSerialize(using=StringSerializer.class)
	private String project;
	
	@Column(name = "industry")
	@NotNull
	@JsonSerialize(using=StringSerializer.class)
	private String industry;
	
	@Column(name = "technology")
	@NotNull
	@JsonSerialize(using=StringSerializer.class)
	private String technology;
	
	@Column(name = "job_title")
	@NotNull
	@JsonSerialize(using=StringSerializer.class)
	private String jobRole_Title; //job role or job title
	
	@JsonSerialize(using=DateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
    @Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;
	
	@JsonSerialize(using=DateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
    @Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "project_value")
	@NotNull
	@JsonSerialize(using=IntegerSerializer.class)
	private int projectValue;
	
	@Column(name = "status")
	@NotNull
	private boolean status; //currently work here

}
