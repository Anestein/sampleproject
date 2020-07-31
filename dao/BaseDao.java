package com.inventory.originssoft.inventoryserver.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.originssoft.inventoryserver.exception.IntegrationDaoException;

import com.inventory.originssoft.inventoryserver.query.Query;

@Repository 
@Transactional
public interface BaseDao<T> {
	
	/**
	 * Creates a new element mapped into the database table
	 * 
	 * @return the created object
	 */
	
	public boolean create(T element) throws IntegrationDaoException;
	
	public boolean createPersist(T element) throws IntegrationDaoException;
	
	/**
	 * Deletes an element from the database
	 * 
	 * @return a copy of deleted object
	 */
	
	boolean delete(String id) throws IntegrationDaoException;
	
	boolean delete(T element) throws IntegrationDaoException;
	
	/**
	 * Retrieves multiple elements from the database by query
	 * 
	 * @return a list of elements
	 */
	
	List<T> query(Query q) throws IntegrationDaoException;
	
	/**
	 * Retrieves a single element from the database by id
	 * 
	 * @return the found element (if any) otherwise null
	 */
	
	T query(String id) throws IntegrationDaoException;
	
	/**
	 * Retrieves a list of ids from the database by id
	 * 
	 * @return the found element (if any) otherwise null
	 */
	
	List<T> list(List <Integer> ids) throws IntegrationDaoException;

	
	
	List<T> search(Query q) throws IntegrationDaoException;
	
	/**
	 * Updates an element from the database
	 * 
	 * @return the updated object
	 */
	
	boolean update(T element) throws IntegrationDaoException;
	
	int save(T element ) throws IntegrationDaoException;
	/**
	 * Updates an element from the database
	 * 
	 */
	List<T> runQuery(String query) throws IntegrationDaoException;
	
	int getTotalMatchResults(Query q) throws IntegrationDaoException;
	
	int getStats(String q) throws IntegrationDaoException;

}
