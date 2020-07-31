package com.inventory.originssoft.inventoryserver.dao.implHibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.originssoft.inventoryserver.dao.InterestDao;
import com.inventory.originssoft.inventoryserver.model.Interest;

@Transactional
@Repository
public class InterestDaoHibernateImpl extends BaseDaoHibernateImpl<Interest> implements InterestDao {


	public InterestDaoHibernateImpl() {
		super("com.inventory.originssoft.inventoryserver.model.Interest");
	}
}