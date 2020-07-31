package com.inventory.originssoft.inventoryserver.dao.implHibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.originssoft.inventoryserver.dao.UserDao;
import com.inventory.originssoft.inventoryserver.model.User;


@Repository
@Transactional
public class UserDaoHibernateImpl extends BaseDaoHibernateImpl<User> implements UserDao {

	public UserDaoHibernateImpl() {
		super("com.inventory.originssoft.inventoryserver.model.User");
	}
}
