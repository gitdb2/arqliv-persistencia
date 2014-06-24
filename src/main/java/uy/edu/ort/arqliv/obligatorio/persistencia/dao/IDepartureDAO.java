package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import uy.edu.ort.arqliv.obligatorio.dominio.Container;
import uy.edu.ort.arqliv.obligatorio.dominio.Departure;

public interface IDepartureDAO {

	public Long store(Departure departure);

	public boolean delete(Long id);

	public Departure findById(Long id);

	public List<Departure> findAll();
	
	public List<Departure> executeNamedQuery(String namedQuery, Map<String, Object> parameters);
	
	public Long update(Departure departure);

	public List<Departure> findDepartureUsingContainerListForDate(List<Container> containersToCheck, Date departureDate);

	public List<Departure> departuresByMonth(int month);
	
	public List<Departure> departuresByMonthByShip(int month, Long shipId);

	public boolean isArrivalDeparted(Long arrivalId);
	
	public boolean isArrivalDepartedDifferentDeparture(Long arrivalId, Long departureId);
	
	/**
	 * Indica si el contenedor con id esta en algun arrivo que no haya partido aún
	 * @param id
	 * @param departureDate
	 * @return
	 */
	public boolean isContainerAvailableForDeparture(Long id, Date departureDate);

}
