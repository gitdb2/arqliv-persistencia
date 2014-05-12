package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uy.edu.ort.arqliv.obligatorio.dominio.Arrival;

@Repository("arrivalDAO")
public class ArrivalDAO implements IArrivalDAO {

	@PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
	@Override
	public Long store(Arrival obj) {
    	Arrival stored = entityManager.merge(obj);
    	return stored.getId();
	}

    @Transactional
	@Override
	public void delete(Long id) {
    	Arrival obj = entityManager.find(Arrival.class, id);
		entityManager.remove(obj);
	}

    @Transactional(readOnly = true)
	@Override
	public Arrival findById(Long id) {
    	return entityManager.find(Arrival.class, id);
	}
    
    @Transactional(readOnly = true)
	@Override
	public Arrival initializeAndUnproxy(Arrival obj) {
        if (obj == null) {
            throw new 
               NullPointerException("Entity passed for initialization is null");
        }
        Hibernate.initialize(obj);
        Hibernate.initialize(obj.getContainers());
        if (obj instanceof HibernateProxy) {
            obj = (Arrival) ((HibernateProxy) obj).getHibernateLazyInitializer().getImplementation();
        }
        return obj;
    }

    @Transactional
	@Override
	public List<Arrival> findAll() {
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Arrival> cq = cb.createQuery(Arrival.class);
        Root<Arrival> rootEntry = cq.from(Arrival.class);
        CriteriaQuery<Arrival> all = cq.select(rootEntry);
        TypedQuery<Arrival> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
	}
    
    @Transactional
    public List<Arrival> executeNamedQuery(String namedQuery, Map<String, String> parameters) {
    	TypedQuery<Arrival> query = entityManager.createNamedQuery(namedQuery, Arrival.class);
    	UtilsDAO.setParameters(query, parameters);
    	return query.getResultList();
    }

}