package com.inventory.originssoft.inventoryserver.dao.implHibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.originssoft.inventoryserver.dao.EmploymentDao;
import com.inventory.originssoft.inventoryserver.model.Employment;

@Transactional
@Repository
public class EmploymentDaoHibernateImpl extends BaseDaoHibernateImpl<Employment> implements EmploymentDao {


	public EmploymentDaoHibernateImpl() {
		super("com.inventory.originssoft.inventoryserver.model.Employment");
	}
}