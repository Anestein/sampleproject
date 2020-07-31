package com.inventory.originssoft.inventoryserver.rest.payload.education;


import java.sql.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UpdateEducationPayload {

	@NotNull
	@NotEmpty
	private String id;
	
	@NotNull
	@NotEmpty
	private String institution;
	
	@NotNull
	@NotEmpty
	private String degrees_courses;
	
	@NotNull
	@NotEmpty
	private Date graduationDate;
	
	@NotNull
	@NotEmpty
	private String discription;


}
