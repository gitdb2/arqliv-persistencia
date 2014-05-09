package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseDAO<T, PK extends Serializable> {

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.openSession();
	}

	private Class<T> type;

	@SuppressWarnings("unchecked")
	public BaseDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public PK save(T o) {
		return (PK) getSession().save(o);
	}

	@Transactional
	public void update(T o) {
		getSession().update(o);
	}

	@Transactional
	public void delete(T o) {
		getSession().delete(o);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public T get(PK id) {
		return (T) getSession().get(type, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		final Criteria crit = getSession().createCriteria(this.type);
		return crit.list();
	}
	
	public Object executeNamedQuery(String nameQuery, Map<String, Object> params) {
		Query query = getSession().getNamedQuery(nameQuery);
		for (String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		return query.list();
	}
	
}