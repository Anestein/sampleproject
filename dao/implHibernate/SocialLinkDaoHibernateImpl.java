package com.inventory.originssoft.inventoryserver.dao.implHibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.originssoft.inventoryserver.dao.SocialLinkDao;
import com.inventory.originssoft.inventoryserver.model.SocialLink;

@Transactional
@Repository
public class SocialLinkDaoHibernateImpl extends BaseDaoHibernateImpl<SocialLink> implements SocialLinkDao {


	public SocialLinkDaoHibernateImpl() {
		super("com.inventory.originssoft.inventoryserver.model.SocialLink");
	}
}