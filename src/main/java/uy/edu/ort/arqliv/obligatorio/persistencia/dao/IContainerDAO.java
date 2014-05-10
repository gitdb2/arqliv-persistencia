package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.List;
import java.util.Map;

import uy.edu.ort.arqliv.obligatorio.dominio.Container;

public interface IContainerDAO {

	public Long store(Container obj);

	public void delete(Long id);

	public Container findById(Long id);

	public List<Container> findAll();
	
	public List<Container> executeNamedQuery(String namedQuery, Map<String, String> parameters);

}
