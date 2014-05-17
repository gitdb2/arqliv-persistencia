package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.List;
import java.util.Map;

import uy.edu.ort.arqliv.obligatorio.dominio.Arrival;

public interface IArrivalDAO {

	public Long store(Arrival obj);

	public void delete(Long id);

	public Arrival findById(Long id);

	public List<Arrival> findAll();
	
	public List<Arrival> executeNamedQuery(String namedQuery, Map<String, String> parameters);
	
	public Arrival initializeAndUnproxy(Arrival obj);

	public List<Arrival> arrivalsByMonth(int month);

	public List<Arrival> arrivalsByMonthByShip(int month, Long shipId);

}
