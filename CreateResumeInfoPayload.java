package com.inventory.originssoft.inventoryserver.rest.payload.resumeInfo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateResumeInfoPayload {
	
	@NotNull
	@NotEmpty
	private String firstName;

	@NotNull
	@NotEmpty
	private String surName;
	
	@NotNull
	@NotEmpty
	private String address;
	
	@NotNull
	@NotEmpty
	private String city;
	
	@NotNull
	private int postalCode;
	
	@NotNull
	@NotEmpty
	private String phoneNumber;
	
	@NotNull
	@NotEmpty
	private String email;
	
	
	private String idNumber;
	
	
	private String gender;
	

	private String nationality;
	
	
	private String mainRole;
	
	@NotNull
	private Date dateCreated;
	
	private String cvTitle;
	
	//private Set<Employment> employment  = new HashSet<Employment>();
	
	//private Set<Education> education = new HashSet<Education>();
	
	//private Set<Skill> skills = new HashSet<Skill>();
	
	private String profileSummary;
	
	//private Set<SocialLink> socialLinks = new HashSet<SocialLink>();
	
	//private Set<Certification> certifications = new HashSet<Certification>();
	
	//private Set<Language> languages = new HashSet<Language>();
	
	//private Set<Interest> interests = new HashSet<Interest>();
	
	//private Set<Role> jobTitle_role = new HashSet<Role>();

}
