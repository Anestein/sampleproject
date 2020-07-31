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

import com.inventory.originssoft.inventoryserver.rest.payload.customer.CreateBulkCustomerPayload;
import com.inventory.originssoft.inventoryserver.rest.payload.customer.CreateCustomerPayload;
import com.inventory.originssoft.inventoryserver.rest.payload.customer.DeleteCustomerPayload;
import com.inventory.originssoft.inventoryserver.rest.payload.customer.GetCustomerPayload;
import com.inventory.originssoft.inventoryserver.rest.payload.customer.UpdateCustomerPayload;
import com.inventory.originssoft.inventoryserver.rest.response.GenStatusResponse;
import com.inventory.originssoft.inventoryserver.service.CustomerService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiError;
import com.wordnik.swagger.annotations.ApiErrors;
import com.wordnik.swagger.annotations.ApiOperation;


@Api(value = "/customer", description = "Manages the customers")
@Controller
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Transactional
	@RequestMapping(value = "/customer/create", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Create customer in the database.")
	@ApiErrors(value = { @ApiError(code = 400, reason = "Bad request."),
			@ApiError(code = 500, reason = "Unhandled exception.") })
	public @ResponseBody GenStatusResponse create(@RequestBody @Valid final CreateCustomerPayload createCustomerPayload)
			throws Exception {
		return customerService.create(createCustomerPayload);
	}

	@Transactional
	@RequestMapping(value = "/customer/update", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update customer in the database.")
	@ApiErrors(value = { @ApiError(code = 400, reason = "Bad request."),
			@ApiError(code = 500, reason = "Unhandled exception.") })
	public @ResponseBody GenStatusResponse update(@RequestBody @Valid final UpdateCustomerPayload updateCustomerPayload)
			throws Exception {
		return customerService.update(updateCustomerPayload);
	}
	
	@Transactional
	@RequestMapping(value = "/customer/getById", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get customer in the database.")
	@ApiErrors(value = { @ApiError(code = 400, reason = "Bad request."),
	@ApiError(code = 500, reason = "Unhandled exception.") })
	public @ResponseBody GenStatusResponse getCustomerById(@RequestBody @Valid final GetCustomerPayload getCustomerPayload)
	throws Exception {
		return customerService.getCustomerById(getCustomerPayload);
	}
	
	@Transactional
	@RequestMapping(value = "/customer/getAll", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get all customers in the database.")
	@ApiErrors(value = { @ApiError(code = 400, reason = "Bad request."),
	@ApiError(code = 500, reason = "Unhandled exception.") })
	public @ResponseBody GenStatusResponse getAll()
	throws Exception {
		return customerService.getAll();
	}
	
	@Transactional
	@RequestMapping(value = "/customer/createBulk", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Create customers in the database.")
	@ApiErrors(value = { @ApiError(code = 400, reason = "Bad request."),
			@ApiError(code = 500, reason = "Unhandled exception.") })
	public @ResponseBody GenStatusResponse createBulk(@RequestBody @Valid final CreateBulkCustomerPayload createCustomerPayload)
			throws Exception {
		return customerService.createBulk(createCustomerPayload);
	}

	@Transactional
	@RequestMapping(value = "/customer/delete", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Delete customer from the Database")
	@ApiErrors(value = { @ApiError(code = 400, reason = "Bad request."),
			@ApiError(code = 500, reason = "Unhandled exception.") })
	public @ResponseBody GenStatusResponse delete(
			@RequestBody @Valid final DeleteCustomerPayload deleteCustomerPayload) throws Exception {
		return customerService.delete(deleteCustomerPayload);
	}
	
}