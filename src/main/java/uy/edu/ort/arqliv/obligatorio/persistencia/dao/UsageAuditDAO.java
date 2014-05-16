package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.Date;
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

import uy.edu.ort.arqliv.obligatorio.dominio.Pair;
import uy.edu.ort.arqliv.obligatorio.dominio.UsageAudit;

@Repository("usageAuditDAO")
public class UsageAuditDAO implements IUsageAuditDAO {

	@PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
	@Override
	public Long store(UsageAudit obj) {
    	UsageAudit stored = entityManager.merge(obj);
    	return stored.getId();
	}

    @Transactional
	@Override
	public List<UsageAudit> findAll() {
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UsageAudit> cq = cb.createQuery(UsageAudit.class);
        Root<UsageAudit> rootEntry = cq.from(UsageAudit.class);
        CriteriaQuery<UsageAudit> all = cq.select(rootEntry);
        TypedQuery<UsageAudit> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
	}

    @Transactional
	@Override
	public List<UsageAudit> executeNamedQuery(String namedQuery, Map<String, String> parameters) {
    	TypedQuery<UsageAudit> query = entityManager.createNamedQuery(namedQuery, UsageAudit.class);
    	UtilsDAO.setParameters(query, parameters);
    	return query.getResultList();
	}
    
    @SuppressWarnings("unchecked")
	@Override
	public List<Pair<String, Double>> avgServiceTime(Date dateFilter) {
		Query query = entityManager.createNamedQuery("UsageAudit.avgServiceTime", Pair.class); 
		query.setParameter("dateFilter", dateFilter);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pair<String, Long>> minServiceTime(Date dateFilter) {
		Query query = entityManager.createNamedQuery("UsageAudit.minServiceTime", Pair.class); 
		query.setParameter("dateFilter", dateFilter);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pair<String, Long>> maxServiceTime(Date dateFilter) {
		Query query = entityManager.createNamedQuery("UsageAudit.maxServiceTime", Pair.class); 
		query.setParameter("dateFilter", dateFilter);
		return query.getResultList();
	}
	
}
