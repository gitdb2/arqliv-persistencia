package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.Date;
import java.util.HashMap;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import uy.edu.ort.arqliv.obligatorio.dominio.Arrival;
import uy.edu.ort.arqliv.obligatorio.dominio.Container;

@Repository("arrivalDAO")
public class ArrivalDAO implements IArrivalDAO {

	@PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Long store(Arrival obj) {
    	Arrival stored = entityManager.merge(obj);
    	return stored.getId();
	}

    @Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delete(Long id) {
    	Arrival obj = entityManager.find(Arrival.class, id);
    	obj.setShip(null);
    	obj.setContainers(null);
		entityManager.remove(obj);
		return true;
	}

    @Transactional(readOnly = true, propagation=Propagation.REQUIRED)
	@Override
	public Arrival findById(Long id) {
    	return entityManager.find(Arrival.class, id);
	}
    
    /**
     * metodo de testing
     */
    @Transactional(readOnly = true, propagation=Propagation.REQUIRED)
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

    @Transactional(propagation=Propagation.REQUIRED)
	@Override
	public List<Arrival> findAll() {
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Arrival> cq = cb.createQuery(Arrival.class);
        Root<Arrival> rootEntry = cq.from(Arrival.class);
        CriteriaQuery<Arrival> all = cq.select(rootEntry);
        TypedQuery<Arrival> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
	}
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public List<Arrival> executeNamedQuery(String namedQuery, Map<String, Object> parameters) {
    	TypedQuery<Arrival> query = entityManager.createNamedQuery(namedQuery, Arrival.class);
    	UtilsDAO.setParameters(query, parameters);
    	return query.getResultList();
    }

    @Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Long update(Arrival obj) {
		Arrival stored = entityManager.merge(obj);
    	return stored.getId();
	}
    
    @Transactional(propagation=Propagation.REQUIRED)
	@Override
	public List<Arrival> findArrivalUsingContainerForDate(Long containerId,
			Date arrivalDate) {
	
    	TypedQuery<Arrival> query = entityManager.createNamedQuery(
				"Arrival.findArrivalUsingContainerForDate", Arrival.class);
		query.setParameter("id", containerId);
		query.setParameter("arrivalDate", arrivalDate);	
		
		return query.getResultList();
    }
    
    @Transactional(propagation=Propagation.REQUIRED)
   	@Override
   	public List<Arrival> findArrivalUsingContainerListForDate(List<Container> containerList,
   			Date arrivalDate) {
   	
       	TypedQuery<Arrival> query = entityManager.createNamedQuery(
   				"Arrival.findArrivalUsingContainerListForDate", Arrival.class);
   		query.setParameter("containerList", containerList);
   		query.setParameter("arrivalDate", arrivalDate);	
   		
   		return query.getResultList();
    }

	@Override
	public List<Arrival> arrivalsByMonth(int month) {
		Map<String, Object> params = new HashMap<String, Object>(1);
		params.put("month", month);
		List<Arrival> ret = executeNamedQuery("Arrival.arrivalsByMonth", params); 
		return ret;
	}

	@Override
	public List<Arrival> arrivalsByMonthByShip(int month, Long shipId) {
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("month", month);
		params.put("shipId", shipId);
		return executeNamedQuery("Arrival.arrivalsByMonthByShip", params);
	}
    
}

