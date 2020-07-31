package com.inventory.originssoft.inventoryserver.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.originssoft.inventoryserver.dao.EmploymentDao;
import com.inventory.originssoft.inventoryserver.dao.ResumeInfoDao;
import com.inventory.originssoft.inventoryserver.exception.ExceptionType;
import com.inventory.originssoft.inventoryserver.exception.IntegrationDaoException;
import com.inventory.originssoft.inventoryserver.exception.IntegrationServiceException;
import com.inventory.originssoft.inventoryserver.exception.StatusCode;
import com.inventory.originssoft.inventoryserver.model.Employment;
import com.inventory.originssoft.inventoryserver.model.ResumeInfo;
import com.inventory.originssoft.inventoryserver.query.Param;
import com.inventory.originssoft.inventoryserver.query.Query;
import com.inventory.originssoft.inventoryserver.rest.payload.employment.CreateEmploymentPayload;
import com.inventory.originssoft.inventoryserver.rest.payload.employment.GetEmploymentPayload;
import com.inventory.originssoft.inventoryserver.rest.payload.employment.UpdateEmploymentPayload;
import com.inventory.originssoft.inventoryserver.rest.response.GenStatusResponse;
import com.inventory.originssoft.inventoryserver.service.EmploymentService;
import com.inventory.originssoft.inventoryserver.service.ResumeInfoService;

@Service
@Transactional
public class EmploymentServiceImpl implements EmploymentService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmploymentServiceImpl.class);
	
	private EmploymentDao employmentDao;

	private ResumeInfoDao resumeInfoDao;
	
	private ResumeInfoService resumeInfoService;

	@Autowired
	public EmploymentServiceImpl(EmploymentDao employmentDao, ResumeInfoDao resumeInfoDao,
			ResumeInfoService resumeInfoService) {
		this.employmentDao = employmentDao;
		this.resumeInfoDao = resumeInfoDao;
		this.resumeInfoService = resumeInfoService;
	}


	@Override
	public GenStatusResponse create(CreateEmploymentPayload createEmploymentPayload)
			throws IntegrationServiceException, Exception {
		// TODO Auto-generated method stub

		ResumeInfo resume = resumeInfoService.getResumeInfoById(String.valueOf(createEmploymentPayload.getResumeId()));
		
		if(resume != null) {
		
			Employment employment = new Employment();
			employment.setClient(createEmploymentPayload.getClient());
			employment.setEmployer(createEmploymentPayload.getEmployer());
			employment.setEndDate(createEmploymentPayload.getEndDate());
			employment.setIndustry(createEmploymentPayload.getIndustry());
			employment.setJobRole_Title(createEmploymentPayload.getJobRole_Title());
			employment.setProject(createEmploymentPayload.getProject());
			employment.setProjectValue(createEmploymentPayload.getProjectValue());
			employment.setStartDate(createEmploymentPayload.getStartDate());
			employment.setStatus(createEmploymentPayload.getStatus());
			employment.setTechnology(createEmploymentPayload.getTechnology());
			
			Set<Employment> employments = resume.getEmployment();
			employments.add(employment);
			
			resume.setEmployment(employments);
			
			employmentDao.create(employment);
			
			resumeInfoDao.update(resume);
		}
		
		
		GenStatusResponse genericStatusResponse = new GenStatusResponse();				
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("applicationStatusCode", StatusCode.SUCCESS);
		properties.put("statusResponse_en", "Successfully Created.");
		genericStatusResponse.setProperties(properties);
		
		return genericStatusResponse;
	}

	@Override
	public GenStatusResponse update(UpdateEmploymentPayload updateEmploymentPayload)
			throws IntegrationServiceException, Exception {
		// TODO Auto-generated method stub
		
		Employment employment = getEmploymentById(updateEmploymentPayload.getId());
		
		if(employment != null){
			employment.setId(updateEmploymentPayload.getId());
			employment.setClient(updateEmploymentPayload.getClient());
			employment.setEmployer(updateEmploymentPayload.getEmployer());
			employment.setEndDate(updateEmploymentPayload.getEndDate());
			employment.setIndustry(updateEmploymentPayload.getIndustry());
			employment.setJobRole_Title(updateEmploymentPayload.getJobRole_Title());
			employment.setProject(updateEmploymentPayload.getProject());
			employment.setProjectValue(updateEmploymentPayload.getProjectValue());
			employment.setStartDate(updateEmploymentPayload.getStartDate());
			employment.setStatus(updateEmploymentPayload.getStatus());
			employment.setTechnology(updateEmploymentPayload.getTechnology());
			
			
			employmentDao.update(employment);
			
			GenStatusResponse genericStatusResponse = new GenStatusResponse();				
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("applicationStatusCode", StatusCode.SUCCESS);
			properties.put("statusResponse_en", "Successfully Updated.");
			genericStatusResponse.setProperties(properties);
			
			return genericStatusResponse;
		}
		else
		{
			throw new IntegrationServiceException(new Throwable(ExceptionType.LOGICAL_ERROR_CODE + ""+ StatusCode.USER_NOT_EXISTS + "::employment doesn't exists."));
		}
	}

	@Override
	public GenStatusResponse delete(GetEmploymentPayload getEmploymentPayload)
			throws IntegrationServiceException, Exception {
		// TODO Auto-generated method stub
		
		Employment employment = getEmploymentById(getEmploymentPayload.getId());
		
		if(employment != null){
			
			employmentDao.delete(employment);
			
			GenStatusResponse genericStatusResponse = new GenStatusResponse();				
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("applicationStatusCode", StatusCode.SUCCESS);
			properties.put("statusResponse_en", "Successfully Deleted.");
			genericStatusResponse.setProperties(properties);
			
			return genericStatusResponse;
		}
		
		GenStatusResponse genericStatusResponse = new GenStatusResponse();				
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("applicationStatusCode", StatusCode.SUCCESS);
		properties.put("statusResponse_en", "Education not found");
		genericStatusResponse.setProperties(properties);
		
		return genericStatusResponse;
	}

	@Override
	public Employment getEmploymentById(int i) throws IntegrationDaoException {
		// TODO Auto-generated method stub
		
		Query query = new Query();
		query.addParameter("id", new Param("id", "=", i));
		List<Employment> employmentList = employmentDao.query(query);
		
		if(employmentList.size() == 0)
			return null;
		
		Employment employment = employmentList.get(0);
		
		return employment;
	}
	
	@Override
	public GenStatusResponse getEmploymentById(GetEmploymentPayload getEmploymentPayload)
			throws IntegrationServiceException, Exception {
		// TODO Auto-generated method stub
	
		Employment employment = getEmploymentById(getEmploymentPayload.getId());

		if(employment == null)
		{
			GenStatusResponse genericStatusResponse = new GenStatusResponse();				
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("applicationStatusCode", StatusCode.SUCCESS);
			properties.put("employment", employment);
			properties.put("statusResponse_en", "Employment not found");
			genericStatusResponse.setProperties(properties);
			
			return genericStatusResponse;
		}
		
		GenStatusResponse genericStatusResponse = new GenStatusResponse();				
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("applicationStatusCode", StatusCode.SUCCESS);
		properties.put("employment", employment);
		properties.put("statusResponse_en", "Successfully Retrieved.");
		genericStatusResponse.setProperties(properties);
		
		return genericStatusResponse;
	}

	@Override
	public GenStatusResponse getAll() throws IntegrationServiceException, Exception {
		// TODO Auto-generated method stub
		
		Query query = new Query();
		query.addParameter("name", new Param("name", "like", "%"+"%"));
		List<Employment> employmentList = employmentDao.query(query);
		
		if(employmentList.size() == 0)
		{
			GenStatusResponse genericStatusResponse = new GenStatusResponse();				
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("applicationStatusCode", StatusCode.SUCCESS);
			properties.put("employments", employmentList);
			properties.put("statusResponse_en", "Employments not found");
			genericStatusResponse.setProperties(properties);
			
			return genericStatusResponse;
		}
		
		GenStatusResponse genericStatusResponse = new GenStatusResponse();				
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("applicationStatusCode", StatusCode.SUCCESS);
		properties.put("employments", employmentList);
		properties.put("statusResponse_en", "Successfully Retrieved.");
		genericStatusResponse.setProperties(properties);
		
		return genericStatusResponse;
	}


}
