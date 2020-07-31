package com.inventory.originssoft.inventoryserver.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.originssoft.inventoryserver.dao.ResumeInfoDao;
import com.inventory.originssoft.inventoryserver.exception.ExceptionType;
import com.inventory.originssoft.inventoryserver.exception.IntegrationDaoException;
import com.inventory.originssoft.inventoryserver.exception.IntegrationServiceException;
import com.inventory.originssoft.inventoryserver.exception.StatusCode;
import com.inventory.originssoft.inventoryserver.model.ResumeInfo;
import com.inventory.originssoft.inventoryserver.query.Param;
import com.inventory.originssoft.inventoryserver.query.Query;
import com.inventory.originssoft.inventoryserver.rest.payload.resumeInfo.CreateResumeInfoPayload;
import com.inventory.originssoft.inventoryserver.rest.payload.resumeInfo.GetResumeInfoPayload;
import com.inventory.originssoft.inventoryserver.rest.payload.resumeInfo.UpdateResumeInfoPayload;
import com.inventory.originssoft.inventoryserver.rest.response.GenStatusResponse;
import com.inventory.originssoft.inventoryserver.service.ResumeInfoService;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@Transactional
public class ResumeInfoServiceImpl implements ResumeInfoService {
	
	@Autowired
	ResumeInfoDao resumeInfoDao;

	
	
	private static final Logger logger = LoggerFactory.getLogger(ResumeInfoServiceImpl.class);

	@Override
	public GenStatusResponse create(CreateResumeInfoPayload createResumeInfoPayload) throws IntegrationServiceException, Exception {

		ResumeInfo resumeInfo = new ResumeInfo();
		resumeInfo.setAddress(createResumeInfoPayload.getAddress());
		resumeInfo.setCity(createResumeInfoPayload.getCity());
		
		if(createResumeInfoPayload.getCvTitle() != null && !createResumeInfoPayload.getCvTitle().isEmpty())
			resumeInfo.setCvTitle(createResumeInfoPayload.getCvTitle());
		
		resumeInfo.setDateCreated(createResumeInfoPayload.getDateCreated());
		//resumeInfo.setEducation(createResumeInfoPayload.getEducation());
		resumeInfo.setEmail(createResumeInfoPayload.getEmail());
		resumeInfo.setFirstName(createResumeInfoPayload.getFirstName());
		if(createResumeInfoPayload.getGender() != null && !createResumeInfoPayload.getGender().isEmpty())
			resumeInfo.setGender(createResumeInfoPayload.getGender());
		if(createResumeInfoPayload.getIdNumber() != null && !createResumeInfoPayload.getIdNumber().isEmpty())
			resumeInfo.setIdNumber(Integer.parseInt(createResumeInfoPayload.getIdNumber()));
		if(createResumeInfoPayload.getMainRole() != null && !createResumeInfoPayload.getMainRole().isEmpty())
			resumeInfo.setMainRole(createResumeInfoPayload.getMainRole());
		if(createResumeInfoPayload.getNationality() != null && !createResumeInfoPayload.getNationality().isEmpty())
			resumeInfo.setNationality(createResumeInfoPayload.getNationality());
		
		resumeInfo.setPhoneNumber(createResumeInfoPayload.getPhoneNumber());
		
		resumeInfo.setPostalCode(createResumeInfoPayload.getPostalCode());
		
		if(createResumeInfoPayload.getProfileSummary() != null && !createResumeInfoPayload.getProfileSummary().isEmpty()) 
			resumeInfo.setProfileSummary(createResumeInfoPayload.getProfileSummary());
		
		resumeInfo.setSurName(createResumeInfoPayload.getSurName());
		
		resumeInfoDao.create(resumeInfo);
		
		GenStatusResponse genericStatusResponse = new GenStatusResponse();				
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("applicationStatusCode", StatusCode.SUCCESS);
		properties.put("statusResponse_en", "Successfully Created.");
		genericStatusResponse.setProperties(properties);
		
		return genericStatusResponse;
	}
	
	@Override
	public GenStatusResponse update(UpdateResumeInfoPayload updateResumeInfoPayload) throws IntegrationServiceException, Exception {

		ResumeInfo resumeInfo = getResumeInfoById(updateResumeInfoPayload.getId());
		
		if(resumeInfo != null){
	
			
			//resumeInfo.setEmployment(employmentSet);
			resumeInfo.setAddress(updateResumeInfoPayload.getAddress());
			resumeInfo.setCity(updateResumeInfoPayload.getCity());
			resumeInfo.setCvTitle(updateResumeInfoPayload.getCvTitle());
			resumeInfo.setDateCreated(updateResumeInfoPayload.getDateCreated());
			//resumeInfo.setEducation(educationSet);
			resumeInfo.setEmail(updateResumeInfoPayload.getEmail());
			resumeInfo.setFirstName(updateResumeInfoPayload.getFirstName());
			resumeInfo.setGender(updateResumeInfoPayload.getGender());
			resumeInfo.setIdNumber(Integer.parseInt(updateResumeInfoPayload.getIdNumber()));
			resumeInfo.setMainRole(updateResumeInfoPayload.getMainRole());
			resumeInfo.setNationality(updateResumeInfoPayload.getNationality());
			resumeInfo.setPhoneNumber(updateResumeInfoPayload.getPhoneNumber());
			resumeInfo.setPostalCode(updateResumeInfoPayload.getPostalCode());
			resumeInfo.setProfileSummary(updateResumeInfoPayload.getProfileSummary());
			resumeInfo.setSurName(updateResumeInfoPayload.getSurName());
			//resumeInfo.setInterests(interestSet);
			//resumeInfo.setLanguages(languageSet);
			//resumeInfo.setSocialLinks(socialLinkSet);
			//resumeInfo.setJobTitle_role(roleSet);
			//resumeInfo.setSkills(skillSet);
			
			
			resumeInfoDao.update(resumeInfo);
			
			GenStatusResponse genericStatusResponse = new GenStatusResponse();				
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("applicationStatusCode", StatusCode.SUCCESS);
			properties.put("statusResponse_en", "Successfully Updated.");
			genericStatusResponse.setProperties(properties);
			
			return genericStatusResponse;
		}
		else
		{
			throw new IntegrationServiceException(new Throwable(ExceptionType.LOGICAL_ERROR_CODE + ""+ StatusCode.USER_NOT_EXISTS + "::user doesn't exists."));
		}

	}
	
	@Override
	public GenStatusResponse delete(GetResumeInfoPayload deleteResumeInfoPayload) throws IntegrationServiceException, Exception {
		
		ResumeInfo resumeInfo = getResumeInfoById(deleteResumeInfoPayload.getId());
		
		if(resumeInfo != null){
			
			resumeInfoDao.delete(resumeInfo);
			
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
	
	public ResumeInfo getResumeInfoById(String resumeInfoId) throws IntegrationDaoException{
		
		Query query = new Query();
		query.addParameter("id", new Param("id", "=", Integer.parseInt(resumeInfoId)));
		List<ResumeInfo> resumeInfoList = resumeInfoDao.query(query);
		if(resumeInfoList != null )
			if(resumeInfoList.size() == 0)
				return null;
		
		ResumeInfo resumeInfo = resumeInfoList.get(0);
		
		return resumeInfo;
	}
	
	public GenStatusResponse getResumeInfoById(GetResumeInfoPayload getResumeInfoPayload) throws IntegrationServiceException, Exception{
		
		ResumeInfo resumeInfo = getResumeInfoById(getResumeInfoPayload.getId());

		if(resumeInfo == null)
		{
			GenStatusResponse genericStatusResponse = new GenStatusResponse();				
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("applicationStatusCode", StatusCode.SUCCESS);
			properties.put("resumeInfo", resumeInfo);
			properties.put("statusResponse_en", "ResumeInfo not found");
			genericStatusResponse.setProperties(properties);
			
			return genericStatusResponse;
		}
		
		GenStatusResponse genericStatusResponse = new GenStatusResponse();				
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("applicationStatusCode", StatusCode.SUCCESS);
		properties.put("resumeInfo", resumeInfo);
		properties.put("statusResponse_en", "Successfully Retrieved.");
		genericStatusResponse.setProperties(properties);
		
		return genericStatusResponse;
		
	}
	
	public GenStatusResponse getAll() throws IntegrationServiceException, Exception {
		
		Query query = new Query();
		query.addParameter("name", new Param("name", "like", "%"+"%"));
		List<ResumeInfo> resumeInfoList = resumeInfoDao.query(query);
		
		if(resumeInfoList.size() == 0)
		{
			GenStatusResponse genericStatusResponse = new GenStatusResponse();				
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("applicationStatusCode", StatusCode.SUCCESS);
			properties.put("resumeInfos", resumeInfoList);
			properties.put("statusResponse_en", "ResumeInfos not found");
			genericStatusResponse.setProperties(properties);
			
			return genericStatusResponse;
		}
		
		GenStatusResponse genericStatusResponse = new GenStatusResponse();				
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("applicationStatusCode", StatusCode.SUCCESS);
		properties.put("resumeInfos", resumeInfoList);
		properties.put("statusResponse_en", "Successfully Retrieved.");
		genericStatusResponse.setProperties(properties);
		
		return genericStatusResponse;
	}

}
