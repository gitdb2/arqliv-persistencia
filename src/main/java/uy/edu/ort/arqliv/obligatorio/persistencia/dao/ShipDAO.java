package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
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
    	return stored.getJpaid();
	}

    @Transactional
	@Override
	public void delete(Long id) {
    	Ship obj = entityManager.find(Ship.class, id);
		entityManager.remove(obj);
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
    	setParameters(query, parameters);
    	return query.getResultList();
    }
    
	private void setParameters(TypedQuery<Ship> query, Map<String, String> parameters) {
		Iterator<Entry<String, String>> it = parameters.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> pairs = (Map.Entry<String, String>) it.next();
			query.setParameter(pairs.getKey(), pairs.getValue());
		}
    }

}