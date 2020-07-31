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

import com.inventory.originssoft.inventoryserver.rest.payload.resumeInfo.CreateResumeInfoPayload;
import com.inventory.originssoft.inventoryserver.rest.payload.resumeInfo.GetResumeInfoPayload;
import com.inventory.originssoft.inventoryserver.rest.payload.resumeInfo.UpdateResumeInfoPayload;
import com.inventory.originssoft.inventoryserver.rest.response.GenStatusResponse;
import com.inventory.originssoft.inventoryserver.service.ResumeInfoService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiError;
import com.wordnik.swagger.annotations.ApiErrors;
import com.wordnik.swagger.annotations.ApiOperation;

@Api(value = "/resumeInfo", description = "Manages the resumeInfos")
@Controller
public class ResumeInfoController {

	@Autowired
	ResumeInfoService resumeInfoService;
	
	private static final Logger logger = LoggerFactory.getLogger(ResumeInfoController.class);

	@Transactional
	@RequestMapping(value = "/resumeInfo/create", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Create resumeInfo in the database.")
	@ApiErrors(value = { @ApiError(code = 400, reason = "Bad request."),
			@ApiError(code = 500, reason = "Unhandled exception.") })
	public @ResponseBody GenStatusResponse create(@RequestBody @Valid final CreateResumeInfoPayload createResumeInfoPayload)
			throws Exception {
		return resumeInfoService.create(createResumeInfoPayload);
	}
	
	

	@Transactional
	@RequestMapping(value = "/resumeInfo/update", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update resumeInfo in the database.")
	@ApiErrors(value = { @ApiError(code = 400, reason = "Bad request."),
			@ApiError(code = 500, reason = "Unhandled exception.") })
	public @ResponseBody GenStatusResponse update(@RequestBody @Valid final UpdateResumeInfoPayload updateResumeInfoPayload)
			throws Exception {
		return resumeInfoService.update(updateResumeInfoPayload);
	}
	
	@Transactional
	@RequestMapping(value = "/resumeInfo/getById", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get resumeInfo in the database.")
	@ApiErrors(value = { @ApiError(code = 400, reason = "Bad request."),
	@ApiError(code = 500, reason = "Unhandled exception.") })
	public @ResponseBody GenStatusResponse getResumeInfoById(@RequestBody @Valid final GetResumeInfoPayload getResumeInfoPayload)
	throws Exception {
		return resumeInfoService.getResumeInfoById(getResumeInfoPayload);
	}
	
	@Transactional
	@RequestMapping(value = "/resumeInfo/getAll", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get all resumeInfos in the database.")
	@ApiErrors(value = { @ApiError(code = 400, reason = "Bad request."),
	@ApiError(code = 500, reason = "Unhandled exception.") })
	public @ResponseBody GenStatusResponse getAll()
	throws Exception {
		return resumeInfoService.getAll();
	}
	
	/*@Transactional
	@RequestMapping(value = "/resumeInfo/createBulk", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Create resumeInfos in the database.")
	@ApiErrors(value = { @ApiError(code = 400, reason = "Bad request."),
			@ApiError(code = 500, reason = "Unhandled exception.") })
	public @ResponseBody GenStatusResponse createBulk(@RequestBody @Valid final CreateBulkCustomerPayload createCustomerPayload)
			throws Exception {
		return customerService.createBulk(createCustomerPayload);
	}*/

	@Transactional
	@RequestMapping(value = "/resumeInfo/delete", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Delete resumeInfo from the Database")
	@ApiErrors(value = { @ApiError(code = 400, reason = "Bad request."),
			@ApiError(code = 500, reason = "Unhandled exception.") })	
	public @ResponseBody GenStatusResponse delete(
			@RequestBody @Valid final GetResumeInfoPayload getResumeInfoPayload) throws Exception {
		return resumeInfoService.delete(getResumeInfoPayload);
	}
}
