package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import uy.edu.ort.arqliv.obligatorio.dominio.Container;

public interface IContainerDAO {

	public Long store(Container obj);

	public boolean delete(Long id);

	public Container findById(Long id);

	public List<Container> findAll();
	
	public List<Container> executeNamedQuery(String namedQuery, Map<String, Object> parameters);

	/**
	 * Actualiza un contenedor en la base de datos en caso que:
	 * Si se modifica la capacidad, se chequea que no este en uso, en caso de estarlo no se modifica y se retorna null
	 * Si no esta en uso, se permite cualquier modificacion.
	 * @param obj
	 * @return
	 */
	public Long update(Container obj);

	/**
	 * retorna true en caso que el contenedor este usado en otro arribo en la misma fecha
	 * @param id
	 * @param arrivalDate
	 * @return
	 */
	boolean isContainerInUseForArrival(Long id, Date arrivalDate);
	
	/**
	 * retorna true en caso que el contenedor este usado en otra Partida en la misma fecha
	 * @param id
	 * @param arrivalDate
	 * @return
	 */
	boolean isContainerInUseForDeparture(Long id, Date arrivalDate);
	
	/**
	 * retorna si un contenedor esta asociado a un barco en una fecha , para arribos, partidas o ambos
	 * @param id
	 * @param shipId
	 * @param arrivalDate
	 * @param departureOrArrival indica si se va a achequear para llegadas, partidas o ambas
	 * @return
	 */
	boolean isContaineriUseForShip(Long id, Long shipId, Date arrivalDate, EnumDepartureOrArrival departureOrArrival);

}
