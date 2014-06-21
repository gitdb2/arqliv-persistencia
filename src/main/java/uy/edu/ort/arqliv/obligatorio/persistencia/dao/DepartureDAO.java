package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.Date;
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

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import uy.edu.ort.arqliv.obligatorio.dominio.Container;
import uy.edu.ort.arqliv.obligatorio.dominio.Departure;

@Repository("departureDAO")
public class DepartureDAO implements IDepartureDAO {

	@PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Long store(Departure obj) {
    	Departure stored = entityManager.merge(obj);
    	return stored.getId();
	}

    @Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delete(Long id) {
    	Departure obj = entityManager.find(Departure.class, id);
    	obj.setShip(null);
    	obj.setContainers(null);
		entityManager.remove(obj);
		return true;
	}

    @Transactional(readOnly = true, propagation=Propagation.REQUIRED)
	@Override
	public Departure findById(Long id) {
    	return entityManager.find(Departure.class, id);
	}
    
    @Transactional(propagation=Propagation.REQUIRED)
	@Override
	public List<Departure> findAll() {
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Departure> cq = cb.createQuery(Departure.class);
        Root<Departure> rootEntry = cq.from(Departure.class);
        CriteriaQuery<Departure> all = cq.select(rootEntry);
        TypedQuery<Departure> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
	}
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public List<Departure> executeNamedQuery(String namedQuery, Map<String, Object> parameters) {
    	TypedQuery<Departure> query = entityManager.createNamedQuery(namedQuery, Departure.class);
    	UtilsDAO.setParameters(query, parameters);
    	return query.getResultList();
    }

    @Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Long update(Departure obj) {
		Departure stored = entityManager.merge(obj);
    	return stored.getId();
	}

	@Override
	public List<Departure> findDepartureUsingContainerListForDate(List<Container> containerList, Date departureDate) {
		TypedQuery<Departure> query = entityManager.createNamedQuery("Departure.findDepartureUsingContainerListForDate", Departure.class);
   		query.setParameter("containerList", containerList);
   		query.setParameter("departureDate", departureDate);	
   		return query.getResultList();
	}
    
}
