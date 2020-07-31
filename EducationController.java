package com.inventory.originssoft.inventoryserver.rest.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.inventory.originssoft.inventoryserver.model.Education;
import com.inventory.originssoft.inventoryserver.rest.payload.education.CreateEducationPayload;
import com.inventory.originssoft.inventoryserver.rest.payload.education.GetEducationPayload;
import com.inventory.originssoft.inventoryserver.rest.payload.education.UpdateEducationPayload;
import com.inventory.originssoft.inventoryserver.rest.response.GenStatusResponse;
import com.inventory.originssoft.inventoryserver.service.EducationService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiError;
import com.wordnik.swagger.annotations.ApiErrors;
import com.wordnik.swagger.annotations.ApiOperation;

@Api(value = "/education", description = "Manages the educations")
@Controller
public class EducationController {

	@Autowired
	EducationService educationService;
	
	private static final Logger logger = LoggerFactory.getLogger(EducationController.class);

	@Transactional
	@RequestMapping(value = "/education/create", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Create education in the database.")
	@ApiErrors(value = { @ApiError(code = 400, reason = "Bad request."),
			@ApiError(code = 500, reason = "Unhandled exception.") })
	public @ResponseBody GenStatusResponse create(@RequestBody @Valid final CreateEducationPayload createEducationPayload)
			throws Exception {
		return educationService.create(createEducationPayload);
	}

	@Transactional
	@RequestMapping(value = "/education/update", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update education in the database.")
	@ApiErrors(value = { @ApiError(code = 400, reason = "Bad request."),
			@ApiError(code = 500, reason = "Unhandled exception.") })
	public @ResponseBody GenStatusResponse update(@RequestBody @Valid final UpdateEducationPayload updateEducationPayload)
			throws Exception {
		return educationService.update(updateEducationPayload);
	}
	
	@Transactional
	@RequestMapping(value = "/education/getById", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get education in the database.")
	@ApiErrors(value = { @ApiError(code = 400, reason = "Bad request."),
	@ApiError(code = 500, reason = "Unhandled exception.") })
	public @ResponseBody GenStatusResponse getEducationById(@RequestBody @Valid final GetEducationPayload getEducationPayload)
	throws Exception {
		return educationService.getEducationById(getEducationPayload);
	}
	
	@Transactional
	@RequestMapping(value = "/education/getAll", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get all educations in the database.")
	@ApiErrors(value = { @ApiError(code = 400, reason = "Bad request."),
	@ApiError(code = 500, reason = "Unhandled exception.") })
	public @ResponseBody GenStatusResponse getAll()
	throws Exception {
		return educationService.getAll();
	}
	
	/*@Transactional
	@RequestMapping(value = "/education/createBulk", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Create educations in the database.")
	@ApiErrors(value = { @ApiError(code = 400, reason = "Bad request."),
			@ApiError(code = 500, reason = "Unhandled exception.") })
	public @ResponseBody GenStatusResponse createBulk(@RequestBody @Valid final CreateBulkCustomerPayload createCustomerPayload)
			throws Exception {
		return customerService.createBulk(createCustomerPayload);
	}*/

	@Transactional
	@RequestMapping(value = "/education/delete", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Delete education from the Database")
	@ApiErrors(value = { @ApiError(code = 400, reason = "Bad request."),
			@ApiError(code = 500, reason = "Unhandled exception.") })	
	public @ResponseBody GenStatusResponse delete(
			@RequestBody @Valid final GetEducationPayload getEducationPayload) throws Exception {
		return educationService.delete(getEducationPayload);
	}
}
