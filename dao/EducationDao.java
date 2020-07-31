package com.inventory.originssoft.inventoryserver.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.originssoft.inventoryserver.model.Education;

@Repository 
@Transactional
public interface EducationDao extends BaseDao<Education> {

}
