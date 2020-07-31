package com.inventory.originssoft.inventoryserver.dao.implHibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.originssoft.inventoryserver.dao.CertificationDao;
import com.inventory.originssoft.inventoryserver.model.Certification;

@Transactional
@Repository
public class CertificationDaoHibernateImpl extends BaseDaoHibernateImpl<Certification> implements CertificationDao {


	public CertificationDaoHibernateImpl() {
		super("com.inventory.originssoft.inventoryserver.model.Certification");
	}
}