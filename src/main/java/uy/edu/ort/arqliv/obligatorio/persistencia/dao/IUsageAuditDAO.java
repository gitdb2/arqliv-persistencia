package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import uy.edu.ort.arqliv.obligatorio.dominio.Pair;
import uy.edu.ort.arqliv.obligatorio.dominio.UsageAudit;

public interface IUsageAuditDAO {

	public Long store(UsageAudit obj);

	public List<UsageAudit> findAll();
	
	public List<UsageAudit> executeNamedQuery(String namedQuery, Map<String, String> parameters);
	
	public List<Pair<String, Double>> avgServiceTime(Date dateFilter);
	
	public Pair<String, Long> minServiceTime(Date dateFilter);
	
	public Pair<String, Long> maxServiceTime(Date dateFilter);
	
}
