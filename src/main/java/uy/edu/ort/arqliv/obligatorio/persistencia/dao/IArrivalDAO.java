package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import uy.edu.ort.arqliv.obligatorio.dominio.Arrival;
import uy.edu.ort.arqliv.obligatorio.dominio.Container;

public interface IArrivalDAO {

	public Long store(Arrival obj);

	public boolean delete(Long id);

	public Arrival findById(Long id);

	public List<Arrival> findAll();
	
	public List<Arrival> executeNamedQuery(String namedQuery, Map<String, Object> parameters);
	
	public Arrival initializeAndUnproxy(Arrival obj);

	public Long update(Arrival arrival);

	/**
	 * TODO
	 * @param containerId
	 * @param arrivalDate
	 * @return
	 */
	public List<Arrival> findArrivalUsingContainerForDate(Long containerId, Date arrivalDate);

	public List<Arrival> findArrivalUsingContainerListForDate(List<Container> containers, Date arrivalDate);

	public List<Arrival> arrivalsByMonth(int month);

	public List<Arrival> arrivalsByMonthByShip(int month, Long shipId);

}
