package com.inventory.originssoft.inventoryserver.dao.implHibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.inventory.originssoft.inventoryserver.dao.CustomerDao;
import com.inventory.originssoft.inventoryserver.model.Customer;


@Repository
@Transactional
public class CustomerDaoHibernateImpl extends BaseDaoHibernateImpl<Customer> implements CustomerDao {

	public CustomerDaoHibernateImpl() {
		super("com.inventory.originssoft.inventoryserver.model.Customer");
	}
}
