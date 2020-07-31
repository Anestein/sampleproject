package com.inventory.originssoft.inventoryserver.dao.implHibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.originssoft.inventoryserver.dao.ResumeInfoDao;
import com.inventory.originssoft.inventoryserver.model.ResumeInfo;

@Transactional
@Repository
public class ResumeInfoDaoHibernateImpl extends BaseDaoHibernateImpl<ResumeInfo> implements ResumeInfoDao {


	public ResumeInfoDaoHibernateImpl() {
		super("com.inventory.originssoft.inventoryserver.model.ResumeInfo");
	}
}