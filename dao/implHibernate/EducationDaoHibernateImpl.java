package com.inventory.originssoft.inventoryserver.dao.implHibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.originssoft.inventoryserver.dao.EducationDao;
import com.inventory.originssoft.inventoryserver.model.Education;

@Transactional
@Repository
public class EducationDaoHibernateImpl extends BaseDaoHibernateImpl<Education> implements EducationDao {


	public EducationDaoHibernateImpl() {
		super("com.inventory.originssoft.inventoryserver.model.Education");
	}
}