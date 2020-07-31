package com.inventory.originssoft.inventoryserver.rest.payload.resumeInfo;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetResumeInfoPayload {

	@NotNull
	private String id;

}
