package com.inventory.originssoft.inventoryserver.dao.implHibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.originssoft.inventoryserver.dao.SkillDao;
import com.inventory.originssoft.inventoryserver.model.Skill;

@Transactional
@Repository
public class SkillDaoHibernateImpl extends BaseDaoHibernateImpl<Skill> implements SkillDao {


	public SkillDaoHibernateImpl() {
		super("com.inventory.originssoft.inventoryserver.model.Skill");
	}
}