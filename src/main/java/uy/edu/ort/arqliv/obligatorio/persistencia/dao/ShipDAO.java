package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uy.edu.ort.arqliv.obligatorio.dominio.Ship;

@Repository("shipDAO")
public class ShipDAO implements IShipDAO {

	@PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
	@Override
	public Long store(Ship obj) {
    	Ship stored = entityManager.merge(obj);
    	return stored.getId();
	}

    @Transactional
	@Override
	public boolean delete(Long id) {
    	
    	Query query = entityManager.createNamedQuery("Ship.countUsage", Long.class);
    	query.setParameter("id", id);
    	
    	Long countInUse = (Long) query.getSingleResult();
    	if(countInUse != null && countInUse == 0){
    		Ship obj = entityManager.find(Ship.class, id);
    		entityManager.remove(obj);
    		return true;
    	}
    	return false;	
	}
    
   
    @Transactional(readOnly = true)
	@Override
	public Ship findById(Long id) {
    	return entityManager.find(Ship.class, id);
	}

    @Transactional
	@Override
	public List<Ship> findAll() {
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ship> cq = cb.createQuery(Ship.class);
        Root<Ship> rootEntry = cq.from(Ship.class);
        CriteriaQuery<Ship> all = cq.select(rootEntry);
        TypedQuery<Ship> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
	}
    
    @Transactional
    public List<Ship> executeNamedQuery(String namedQuery, Map<String, String> parameters) {
    	TypedQuery<Ship> query = entityManager.createNamedQuery(namedQuery, Ship.class);
    	UtilsDAO.setParameters(query, parameters);
    	return query.getResultList();
    }

}