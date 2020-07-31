package com.inventory.originssoft.inventoryserver.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.originssoft.inventoryserver.model.Customer;

@Repository @Transactional
public interface CustomerDao extends BaseDao<Customer> {

	
}
