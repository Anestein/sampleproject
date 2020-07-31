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

import com.inventory.originssoft.inventoryserver.dao.EducationDao;
import com.inventory.originssoft.inventoryserver.dao.ResumeInfoDao;
import com.inventory.originssoft.inventoryserver.exception.ExceptionType;
import com.inventory.originssoft.inventoryserver.exception.IntegrationDaoException;
import com.inventory.originssoft.inventoryserver.exception.IntegrationServiceException;
import com.inventory.originssoft.inventoryserver.exception.StatusCode;
import com.inventory.originssoft.inventoryserver.model.Education;
import com.inventory.originssoft.inventoryserver.model.Employment;
import com.inventory.originssoft.inventoryserver.model.ResumeInfo;
import com.inventory.originssoft.inventoryserver.query.Param;
import com.inventory.originssoft.inventoryserver.query.Query;
import com.inventory.originssoft.inventoryserver.rest.payload.education.CreateEducationPayload;
import com.inventory.originssoft.inventoryserver.rest.payload.education.GetEducationPayload;
import com.inventory.originssoft.inventoryserver.rest.payload.education.UpdateEducationPayload;
import com.inventory.originssoft.inventoryserver.rest.response.GenStatusResponse;
import com.inventory.originssoft.inventoryserver.service.EducationService;
import com.inventory.originssoft.inventoryserver.service.ResumeInfoService;

@Service
@Transactional
public class EducationServiceImpl implements EducationService {
	
	private static final Logger logger = LoggerFactory.getLogger(EducationServiceImpl.class);
	
	private EducationDao educationDao;

	private ResumeInfoDao resumeInfoDao;
	
	private ResumeInfoService resumeInfoService;
	
	@Autowired
	public EducationServiceImpl(EducationDao educationDao, ResumeInfoDao resumeInfoDao,
			ResumeInfoService resumeInfoService) {
		this.educationDao = educationDao;
		this.resumeInfoDao = resumeInfoDao;
		this.resumeInfoService = resumeInfoService;
	}

	@Override
	public GenStatusResponse create(CreateEducationPayload createEducationPayload)
			throws IntegrationServiceException, Exception {
		// TODO Auto-generated method stub
		
		ResumeInfo resume = resumeInfoService.getResumeInfoById(String.valueOf(createEducationPayload.getResumeId()));
		
		if(resume != null) {
		
		Education education = new Education();
		education.setDegrees_courses(createEducationPayload.getDegrees_courses());
		education.setDiscription(createEducationPayload.getDiscription());
		education.setGraduationDate(createEducationPayload.getGraduationDate());
		education.setInstitution(createEducationPayload.getInstitution());
		
		Set<Education> educations = resume.getEducation();
		educations.add(education);
		
		resume.setEducation(educations);
		
		educationDao.create(education);
		
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
	public GenStatusResponse update(UpdateEducationPayload updateEducationPayload)
			throws IntegrationServiceException, Exception {
		// TODO Auto-generated method stub
		
		Education education = getEducationById(updateEducationPayload.getId());
		
		if(education != null){
			
			education.setDegrees_courses(updateEducationPayload.getDegrees_courses());
			education.setDiscription(updateEducationPayload.getDiscription());
			education.setGraduationDate(updateEducationPayload.getGraduationDate());
			education.setInstitution(updateEducationPayload.getInstitution());	
			
			educationDao.update(education);
			
			GenStatusResponse genericStatusResponse = new GenStatusResponse();				
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("applicationStatusCode", StatusCode.SUCCESS);
			properties.put("statusResponse_en", "Successfully Updated.");
			genericStatusResponse.setProperties(properties);
			
			return genericStatusResponse;
		}
		else
		{
			throw new IntegrationServiceException(new Throwable(ExceptionType.LOGICAL_ERROR_CODE + ""+ StatusCode.USER_NOT_EXISTS + "::Education doesn't exists."));
		}
	}

	@Override
	public GenStatusResponse delete(GetEducationPayload getEducationPayload) throws IntegrationServiceException, Exception {
		// TODO Auto-generated method stub
		
		Education education = getEducationById(getEducationPayload.getId());
		
		if(education != null){
			
			educationDao.delete(education);
			
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
	public Education getEducationById(String string) throws IntegrationDaoException {
		// TODO Auto-generated method stub
		
		Query query = new Query();
		query.addParameter("id", new Param("id", "=", Integer.parseInt(string)));
		List<Education> educationList = educationDao.query(query);
		
		if(educationList.size() == 0)
			return null;
		
		Education education = educationList.get(0);
		
		return education;
	}
	@Override
	public GenStatusResponse getEducationById(GetEducationPayload getEducationPayload) throws IntegrationServiceException, Exception {
		// TODO Auto-generated method stub
		
		Education education = getEducationById(getEducationPayload.getId());

		if(education == null)
		{
			GenStatusResponse genericStatusResponse = new GenStatusResponse();				
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("applicationStatusCode", StatusCode.SUCCESS);
			properties.put("education", education);
			properties.put("statusResponse_en", "Education not found");
			genericStatusResponse.setProperties(properties);
			
			return genericStatusResponse;
		}
		
		GenStatusResponse genericStatusResponse = new GenStatusResponse();				
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("applicationStatusCode", StatusCode.SUCCESS);
		properties.put("education", education);
		properties.put("statusResponse_en", "Successfully Retrieved.");
		genericStatusResponse.setProperties(properties);
		
		return genericStatusResponse;
		
	}

	@Override
	public GenStatusResponse getAll() throws IntegrationServiceException, Exception {
		// TODO Auto-generated method stub
		
		Query query = new Query();
		query.addParameter("name", new Param("name", "like", "%"+"%"));
		List<Education> educationList = educationDao.query(query);
		
		if(educationList.size() == 0)
		{
			GenStatusResponse genericStatusResponse = new GenStatusResponse();				
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("applicationStatusCode", StatusCode.SUCCESS);
			properties.put("education", educationList);
			properties.put("statusResponse_en", "Education not found");
			genericStatusResponse.setProperties(properties);
			
			return genericStatusResponse;
		}
		
		GenStatusResponse genericStatusResponse = new GenStatusResponse();				
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("applicationStatusCode", StatusCode.SUCCESS);
		properties.put("education", educationList);
		properties.put("statusResponse_en", "Successfully Retrieved.");
		genericStatusResponse.setProperties(properties);
		
		return genericStatusResponse;
	}
	
}
