package com.inventory.originssoft.inventoryserver.rest.payload.employment;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateEmploymentPayload {

	@NotNull
	private int id;
	
	@NotNull
	@NotEmpty
	private String employer;
	
	@NotNull
	@NotEmpty
	private String client;
	
	@NotNull
	@NotEmpty
	private String project;
	
	@NotNull
	@NotEmpty
	private String industry;
	
	@NotNull
	@NotEmpty
	private String technology;
	
	@NotNull
	@NotEmpty
	private String jobRole_Title; //job role or job title
	
	@NotNull
	private Date startDate;
	
	@NotNull
	private Date endDate;
	
	@NotNull
	private int projectValue;
	

	@NotNull
	private boolean status; //currently work here
	
	public boolean getStatus () {
		return status;
	}

}
