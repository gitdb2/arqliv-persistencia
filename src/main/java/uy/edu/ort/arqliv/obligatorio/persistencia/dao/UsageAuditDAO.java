package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.ArrayList;
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

	@Override
	@SuppressWarnings("unchecked")
	public List<Pair<String, Double>> avgServiceTime(Date dateFilter) {
		String queryStr = "SELECT u.service, AVG(UNIX_TIMESTAMP(u.actionEndTime) - UNIX_TIMESTAMP(u.actionStartTime)) AS avgTime"
					   + " FROM UsageAudit u "
					   + " WHERE DATE(u.actionStartTime) = DATE(:dateFilter) "
					   + " GROUP BY u.service "
					   + " ORDER BY avgTime ASC";
		Query query = entityManager.createQuery(queryStr);
		query.setParameter("dateFilter", dateFilter);
		return parsePerformanceQueryList(query.getResultList());
	}

	@Override
	@SuppressWarnings("unchecked")
	public Pair<String, Long> minServiceTime(Date dateFilter) {
		String queryStr = "	SELECT u.service, UNIX_TIMESTAMP(u.actionEndTime) - UNIX_TIMESTAMP(u.actionStartTime) AS minTime "
			+ " FROM UsageAudit u"
			+ " WHERE DATE(u.actionStartTime) = DATE(:dateFilter) "
			+ " AND UNIX_TIMESTAMP(u.actionEndTime) - UNIX_TIMESTAMP(u.actionStartTime) "
			+ " = (SELECT MIN(UNIX_TIMESTAMP(p.actionEndTime) - UNIX_TIMESTAMP(p.actionStartTime)) "
			+ "    FROM UsageAudit p WHERE DATE(p.actionStartTime) = DATE(:dateFilter))";

		Query query = entityManager.createQuery(queryStr);
		query.setParameter("dateFilter", dateFilter);
		return parsePerformanceQueryUniqueValue(query.getResultList());
	}

	@Override
	@SuppressWarnings("unchecked")
	public Pair<String, Long> maxServiceTime(Date dateFilter) {
		String queryStr = "	SELECT u.service, UNIX_TIMESTAMP(u.actionEndTime) - UNIX_TIMESTAMP(u.actionStartTime) AS minTime "
				+ " FROM UsageAudit u"
				+ " WHERE DATE(u.actionStartTime) = DATE(:dateFilter) "
				+ " AND UNIX_TIMESTAMP(u.actionEndTime) - UNIX_TIMESTAMP(u.actionStartTime) "
				+ " = (SELECT MAX(UNIX_TIMESTAMP(p.actionEndTime) - UNIX_TIMESTAMP(p.actionStartTime)) "
				+ "    FROM UsageAudit p WHERE DATE(p.actionStartTime) = DATE(:dateFilter))";
		
		Query query = entityManager.createQuery(queryStr);
		query.setParameter("dateFilter", dateFilter);
		return parsePerformanceQueryUniqueValue(query.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	private <K, V> List <Pair <K, V>> parsePerformanceQueryList (List<Object[]> rawResult) {
		List <Pair <K, V>> ret = new ArrayList<Pair <K, V>>();
		Pair<K, V> tmp = new Pair<K, V>();
		for (Object[] resultElement : rawResult) {
			tmp = new Pair<K, V>();
	        tmp.setKey((K) resultElement[0]);
	        tmp.setValue((V) resultElement[1]);
	        ret.add(tmp);
	    }
		return ret;
	}
	
	private <K, V> Pair<K, V> parsePerformanceQueryUniqueValue (List<Object[]> rawResult) {
		List <Pair <K, V>> tmp = parsePerformanceQueryList(rawResult);
		if (tmp.isEmpty()) {
			return null;
		} else {
			return tmp.get(0);
		}
	}
	
}
