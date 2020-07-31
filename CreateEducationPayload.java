package com.inventory.originssoft.inventoryserver.rest.payload.education;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class CreateEducationPayload {

	private int resumeId;
	
	@NotNull
	@NotEmpty
	private String institution;
	
	@NotNull
	@NotEmpty
	private String degrees_courses;
	
	@NotNull
	private Date graduationDate;
	
	@NotNull
	@NotEmpty
	private String discription;


}
