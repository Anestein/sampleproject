package com.inventory.originssoft.inventoryserver.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers.IntegerSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.inventory.originssoft.inventoryserver.serialize.CertificationSerializer;
import com.inventory.originssoft.inventoryserver.serialize.EducationSerializer;
import com.inventory.originssoft.inventoryserver.serialize.EmploymentSerializer;
import com.inventory.originssoft.inventoryserver.serialize.InterestSerializer;
import com.inventory.originssoft.inventoryserver.serialize.LanguageSerializer;
import com.inventory.originssoft.inventoryserver.serialize.RoleSerializer;
import com.inventory.originssoft.inventoryserver.serialize.SkillSerializer;
import com.inventory.originssoft.inventoryserver.serialize.SocialLinksSerializer;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "resumeinfo")
public class ResumeInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "resume_id", updatable = false, unique = true)
	@NotNull
	@JsonSerialize(using=IntegerSerializer.class)
	private int id;
	
	@Column(name = "first_name")
	@NotNull
	@JsonSerialize(using=StringSerializer.class)
	private String firstName;

	@Column(name = "sur_name")
	@NotNull
	@JsonSerialize(using=StringSerializer.class)
	private String surName;
	
	@Column(name = "address")
	@NotNull
	@JsonSerialize(using=StringSerializer.class)
	private String address;
	
	@Column(name = "city")
	@NotNull
	@JsonSerialize(using=StringSerializer.class)
	private String city;
	
	@Column(name = "postal_code")
	@NotNull
	@JsonSerialize(using=IntegerSerializer.class)
	private int postalCode;
	
	@Column(name = "phone_number")
	@NotNull
	@JsonSerialize(using=StringSerializer.class)
	private String phoneNumber;
	
	@Column(name = "email")
	@NotNull
	@JsonSerialize(using=StringSerializer.class)
	private String email;
	
	@Column(name = "id_number")
	@JsonSerialize(using=IntegerSerializer.class)
	private int idNumber;
	
	@Column(name = "gender")
	
	@JsonSerialize(using=StringSerializer.class)
	private String gender;
	
	@Column(name = "nationality")
	
	@JsonSerialize(using=StringSerializer.class)
	private String nationality;
	
	@Column(name = "main_role")
	@JsonSerialize(using=StringSerializer.class)
	private String mainRole;
	
	@Column(name = "date_created")
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
    @Temporal(TemporalType.DATE)
	private Date dateCreated;
	
	@Column(name = "cv_title")
	@JsonSerialize(using=StringSerializer.class)	
	private String cvTitle;
	
	
	@OneToMany(targetEntity = Employment.class, cascade=CascadeType.ALL, fetch= FetchType.EAGER)
	@JsonSerialize(using=EmploymentSerializer.class)
	private Set<Employment> employment;
	
	@OneToMany(targetEntity = Education.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonSerialize(using=EducationSerializer.class)
	private Set<Education> education;
	
	
	@OneToMany(targetEntity = Skill.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonSerialize(using=SkillSerializer.class)
	private Set<Skill> skills;
	
	@Column(name = "profile_summary")	
	@JsonSerialize(using=StringSerializer.class)
	private String profileSummary;
	
	@JsonSerialize(using=SocialLinksSerializer.class)
	@OneToMany(targetEntity = SocialLink.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<SocialLink> socialLinks;
	
	@JsonSerialize(using=CertificationSerializer.class)
	@OneToMany(targetEntity = Certification.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Certification> certifications = new HashSet<Certification>();
	
	@JsonSerialize(using=LanguageSerializer.class)
	@OneToMany(targetEntity = Language.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Language> languages;
	
	@JsonSerialize(using=InterestSerializer.class)
	@OneToMany(targetEntity = Interest.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Interest> interests;
	
	@JsonSerialize(using=RoleSerializer.class)
	@OneToMany(targetEntity = Role.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Role> jobTitle_role = new HashSet<Role>();

	
}
