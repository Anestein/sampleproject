package com.inventory.originssoft.inventoryserver.rest.payload.employment;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetEmploymentPayload {

	@NotNull
	private int id;



}
