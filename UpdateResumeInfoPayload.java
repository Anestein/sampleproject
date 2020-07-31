package com.inventory.originssoft.inventoryserver.rest.payload.resumeInfo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.inventory.originssoft.inventoryserver.rest.payload.certification.CreateCertificationPayload;
import com.inventory.originssoft.inventoryserver.rest.payload.education.CreateEducationPayload;
import com.inventory.originssoft.inventoryserver.rest.payload.employment.CreateEmploymentPayload;
import com.inventory.originssoft.inventoryserver.rest.payload.interest.CreateInterestPayload;
import com.inventory.originssoft.inventoryserver.rest.payload.language.CreateLanguagePayload;
import com.inventory.originssoft.inventoryserver.rest.payload.role.CreateRolePayload;
import com.inventory.originssoft.inventoryserver.rest.payload.skill.CreateSkillPayload;
import com.inventory.originssoft.inventoryserver.rest.payload.sociallink.CreateSocialLinkPayload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateResumeInfoPayload {
	

	private String id;
	

	private String firstName;

	
	private String surName;
	
	
	private String address;
	
	
	private String city;
	
	
	private int postalCode;
	
	
	private String phoneNumber;
	

	private String email;
	

	private String idNumber;
	

	private String gender;
	

	private String nationality;

	private String mainRole;
	
	
	private Date dateCreated;
	

	private String cvTitle;
	
	private CreateEmploymentPayload[] employments;
	
	private CreateEducationPayload[] educations;
	
	//private Set<Education> education = new HashSet<Education>();
	
	private CreateSkillPayload[] skills;
	
	@NotNull
	@NotEmpty
	private String profileSummary;
	
	private CreateSocialLinkPayload[] socialLinks;
	
	private CreateCertificationPayload[] certification;
	
	private CreateLanguagePayload[] languages;
	
	private CreateInterestPayload[] interests;
	
	private CreateRolePayload[] jobTitle_role;

}
