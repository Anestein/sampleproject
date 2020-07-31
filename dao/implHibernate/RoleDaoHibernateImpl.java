package com.inventory.originssoft.inventoryserver.dao.implHibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.originssoft.inventoryserver.dao.RoleDao;
import com.inventory.originssoft.inventoryserver.model.Role;

@Transactional
@Repository
public class RoleDaoHibernateImpl extends BaseDaoHibernateImpl<Role> implements RoleDao {


	public RoleDaoHibernateImpl() {
		super("com.inventory.originssoft.inventoryserver.model.Role");
	}
}