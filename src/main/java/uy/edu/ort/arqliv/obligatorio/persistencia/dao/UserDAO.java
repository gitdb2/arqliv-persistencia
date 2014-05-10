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

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uy.edu.ort.arqliv.obligatorio.dominio.User;

@Repository("userDAO")
public class UserDAO implements IUserDAO {

	@PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
	@Override
	public Long store(User obj) {
    	User stored = entityManager.merge(obj);
    	return stored.getId();
	}

    @Transactional
	@Override
	public void delete(Long id) {
    	User obj = entityManager.find(User.class, id);
		entityManager.remove(obj);
	}

    @Transactional(readOnly = true)
	@Override
	public User findById(Long id) {
    	return entityManager.find(User.class, id);
	}

    @Transactional
	@Override
	public List<User> findAll() {
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(rootEntry);
        TypedQuery<User> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
	}
    
    @Transactional
    public List<User> executeNamedQuery(String namedQuery, Map<String, String> parameters) {
    	TypedQuery<User> query = entityManager.createNamedQuery(namedQuery, User.class);
    	UtilsDAO.setParameters(query, parameters);
    	return query.getResultList();
    }

}