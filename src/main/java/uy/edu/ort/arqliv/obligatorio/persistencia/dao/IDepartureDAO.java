package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.List;
import java.util.Map;

import uy.edu.ort.arqliv.obligatorio.dominio.Departure;

public interface IDepartureDAO {

	public Long store(Departure departure);

	public boolean delete(Long id);

	public Departure findById(Long id);

	public List<Departure> findAll();
	
	public List<Departure> executeNamedQuery(String namedQuery, Map<String, Object> parameters);
	
	public Long update(Departure departure);

}
