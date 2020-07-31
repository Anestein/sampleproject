package com.inventory.originssoft.inventoryserver.dao.implHibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.originssoft.inventoryserver.dao.LanguageDao;
import com.inventory.originssoft.inventoryserver.model.Language;

@Transactional
@Repository
public class LanguageDaoHibernateImpl extends BaseDaoHibernateImpl<Language> implements LanguageDao {


	public LanguageDaoHibernateImpl() {
		super("com.inventory.originssoft.inventoryserver.model.Language");
	}
}