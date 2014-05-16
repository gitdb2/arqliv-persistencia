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

import uy.edu.ort.arqliv.obligatorio.dominio.Container;
import uy.edu.ort.arqliv.obligatorio.dominio.Ship;

@Repository("containerDAO")
public class ContainerDAO implements IContainerDAO {

	@PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
	@Override
	public Long store(Container obj) {
    	Container stored = entityManager.merge(obj);
    	return stored.getId();
	}
    
    @Transactional
	@Override
	public Long update(Container obj) {
    	Container stored = entityManager.find(Container.class, obj.getId());
		if(stored.getCapacity() != obj.getCapacity()){//si cambia la capacidad chequeo que no este en uso
			
			Query query = entityManager.createNamedQuery("Container.countUsage", Long.class);
	    	query.setParameter("id", obj.getId());
	    	
	    	long countInUse = (Long) query.getSingleResult();
	    	if(countInUse != 0){
	    		return null;
	    	}
		}
		
		stored = entityManager.merge(obj);
    	return stored.getId();
	}
    

    @Transactional
	@Override
	public boolean delete(Long id) {
    	
    	
    	Query query = entityManager.createNamedQuery("Container.countUsage", Long.class);
    	query.setParameter("id", id);
    	
    	Long countInUse = (Long) query.getSingleResult();
    	if(countInUse != null && countInUse == 0){
    		Container obj = entityManager.find(Container.class, id);
    		entityManager.remove(obj);
    		return true;
    	}
    	return false;		
	}
    
    

    @Transactional(readOnly = true)
	@Override
	public Container findById(Long id) {
    	return entityManager.find(Container.class, id);
	}

    @Transactional
	@Override
	public List<Container> findAll() {
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Container> cq = cb.createQuery(Container.class);
        Root<Container> rootEntry = cq.from(Container.class);
        CriteriaQuery<Container> all = cq.select(rootEntry);
        TypedQuery<Container> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
	}
    
    @Transactional
    public List<Container> executeNamedQuery(String namedQuery, Map<String, String> parameters) {
    	TypedQuery<Container> query = entityManager.createNamedQuery(namedQuery, Container.class);
    	UtilsDAO.setParameters(query, parameters);
    	return query.getResultList();
    }

}