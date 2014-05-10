package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.List;
import java.util.Map;

import uy.edu.ort.arqliv.obligatorio.dominio.Ship;

public interface IShipDAO {

	public Long store(Ship obj);

	public void delete(Long id);

	public Ship findById(Long id);

	public List<Ship> findAll();
	
	public List<Ship> executeNamedQuery(String namedQuery, Map<String, String> parameters);

}
