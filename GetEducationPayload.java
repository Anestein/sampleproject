package com.inventory.originssoft.inventoryserver.rest.payload.education;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetEducationPayload {

	@NotNull
	@NotEmpty
	private String id;

}
