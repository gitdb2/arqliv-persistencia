package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.Date;
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
	
	public List<Ship> executeNamedQuery(String namedQuery, Map<String, Object> parameters);
	
	/**
	 * Retorna true si el barco cumple con la regla de negocios 3:
	 * No se puede modificar la Capacidad de transporte de un Barco, si no arribo a puerto ese día.
	 * Se debe controlar en el Negocio si se modificó el valor o no para invocar esta operacion.
	 * @param id
	 * @param arrivalDate
	 * @return
	 */
	boolean canBeUpdated(Long id, Date arrivalDate);
//	
//	
//	boolean canBeUpdatedByDeparture(Long id, Date departureDate);
//	
	
	/**
	 * Indica si el barco esta en uso en algun departure
	 * @param id
	 * @return
	 */
	boolean shipInUseInDeparture(Long id);
	

}
