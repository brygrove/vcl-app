package com.vcl.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vcl.util.jpa.HqlParam;

@Repository
@Transactional
public abstract class JpaDao<ENTITY, KEY> implements Dao<ENTITY, KEY> {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	protected Class<ENTITY> persistenceClass;

	public JpaDao() {
		this.persistenceClass = findPersistentClass(); 
	}
	
	@SuppressWarnings("unchecked")
	protected Class<ENTITY> findPersistentClass() {
		Type superclass = getClass().getGenericSuperclass();

		// make sure it's a parameterized type (could be a proxy)
		if (superclass instanceof ParameterizedType) {
			return (Class<ENTITY>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
		}

		return null;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public ENTITY persist(ENTITY entity) {
		entityManager.persist(entity);
		return entity;
	}
	
	public ENTITY persistAndFlush(ENTITY entity) {
		entityManager.persist(entity);
		entityManager.flush();
		return entity;
	}

	@Override
	public void removeById(KEY key) {
		ENTITY entity = findById(key);
		remove(entity);
	}

	@Override
	public void remove(ENTITY entity) {
		entityManager.remove(entity);
	}

	@Override
	public ENTITY merge(ENTITY entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	public ENTITY refresh(ENTITY entity) {
		entityManager.refresh(entity);
		return entity;
	}

	@Override
	public ENTITY findById(KEY key) {
		return (ENTITY) entityManager.find(persistenceClass, key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ENTITY> findAll() {
		return createQuery(getEntityFindAllEjbQl()).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ENTITY> findAllPaged(int firstResult, int maxResult) {
		return createQueryPaged(getEntityFindAllEjbQl(), firstResult, maxResult).getResultList();
	}

	protected String getEntityFindAllEjbQl() {
		return "SELECT e FROM " + persistenceClass.getSimpleName() + " e ";
	}

	protected Query createQuery(String ejbQl) {
		return entityManager.createQuery(ejbQl);
	}

	protected Query createQueryPaged(String ejbQl, int firstResult, int maxResult) {
		Query query = createQuery(ejbQl);
		return decorateQueryWithPaging(query, firstResult, maxResult);
	}

	private Query decorateQueryWithPaging(Query query, int firstResult, int maxResult) {
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}

		if (maxResult != -1) {
			query.setMaxResults(maxResult);
		}

		return query;
	}
	
	/**
	 * prepares a query using hql and hql params 
	 **/
	private Query prepareQuery(String hql, List<HqlParam> hqlParams ){
		Query query = getEntityManager().createQuery(hql);
		if ( hqlParams != null ){
			for ( HqlParam p : hqlParams ){
				Object value = p.getParamValue();
				query.setParameter(p.getParamName(), value );
			}
		}
		return query;
	}
	
	public List<?> findByCustomHqlQuery(String hql, List<HqlParam> hqlParams ){
		Query query = prepareQuery(hql, hqlParams);
		return query.getResultList();
	}
	
	public Object findUniqueResultByCustomHqlQuery(String hql, List<HqlParam> hqlParams ){
		Query query = prepareQuery(hql, hqlParams);
		return query.getSingleResult();
	}
	
	public long countByHqlQuery( String hql, List<HqlParam> hqlParams ){
		Object uniqueResult = findUniqueResultByCustomHqlQuery(hql, hqlParams);
		if ( uniqueResult != null ){
			Long count = (Long) uniqueResult;
			return count.longValue();
		}
		return 0;
	}
	
	/**
	 * used to retrieve custom instances, multiple object aliases, or arrays of fields 
	 * ex: select o.id, o.name from NamedFee o  ( returns a List<Object[]> ) 
	 * ex: select f, fund from NamedFee f inner join Fund fund  ( returns a List<Object[]> )
	 * ex: select new FeeView( o.id, o.name ) from NamedFee o ( returns a List<FeeView> )
	 **/
	public List<?> findByCustomHqlQueryPaged(String hql, List<HqlParam> hqlParams, int firstResult, int maxResults ){
		Query query = prepareQuery(hql, hqlParams);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<ENTITY> findByHqlQuery(String hql, List<HqlParam> hqlParams){
		Query query = prepareQuery(hql, hqlParams);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<ENTITY> findByHqlQueryPaged(String hql, List<HqlParam> hqlParams, int firstResult, int maxResults){
		Query query = prepareQuery(hql, hqlParams);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}
	

	protected Query createNamedQuery(String queryName) {
		return entityManager.createQuery(queryName);
	}

	protected Query createNativeQuery(String sqlQuery) {
		return entityManager.createNativeQuery(sqlQuery);
	}

	protected Query createNativeQuery(String sqlQuery, Class<?> resultClass) {
		return entityManager.createNativeQuery(sqlQuery, resultClass);
	}

	protected void flush() {
		entityManager.flush();
	}

	protected void clear() {
		entityManager.clear();
	}
}
