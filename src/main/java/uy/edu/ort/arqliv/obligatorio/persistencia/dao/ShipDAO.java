package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

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
	public void store(Ship obj) {
    	entityManager.merge(obj);
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

    @SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Ship> findAll() {
    	  Query query = entityManager.createQuery("from Test");
          return (List<Ship>) query.getResultList();
	}

}