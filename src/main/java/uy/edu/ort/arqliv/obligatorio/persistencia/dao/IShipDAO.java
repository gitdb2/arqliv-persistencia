package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.List;
import java.util.Map;

import uy.edu.ort.arqliv.obligatorio.dominio.Ship;

public interface IShipDAO {

	public Long store(Ship obj);
	/**
	 * retorna false en caso que no se pueda elimiar porque esta en uso
	 * @param id
	 * @return
	 */
	public boolean delete(Long id);

	public Ship findById(Long id);

	public List<Ship> findAll();
	
	public List<Ship> executeNamedQuery(String namedQuery, Map<String, String> parameters);
	
	

}
