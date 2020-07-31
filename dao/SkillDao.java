package com.inventory.originssoft.inventoryserver.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.originssoft.inventoryserver.model.Skill;

@Repository 
@Transactional
public interface SkillDao extends BaseDao<Skill> {

}
