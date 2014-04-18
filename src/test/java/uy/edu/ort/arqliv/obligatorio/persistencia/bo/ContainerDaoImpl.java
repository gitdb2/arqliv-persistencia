package uy.edu.ort.arqliv.obligatorio.persistencia.bo;

import org.hibernate.SessionFactory;

import uy.edu.ort.arqliv.obligatorio.dominio.Container;

public class ContainerDaoImpl implements ContainerDao {
	
	private SessionFactory sessionFactory;
	
	@Override
	public void save(Container c) {
		getSessionFactory().getCurrentSession().save(c);
	}

	@Override
	public void update(Container c) {
		getSessionFactory().getCurrentSession().update(c);
	}

	@Override
	public void delete(Container c) {
		getSessionFactory().getCurrentSession().delete(c);
	}

	@Override
	public Container find(String code) {
		return null;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
