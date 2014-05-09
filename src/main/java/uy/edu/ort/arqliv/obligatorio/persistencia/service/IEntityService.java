package uy.edu.ort.arqliv.obligatorio.persistencia.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IEntityService<T, PK extends Serializable> {

	public T get(PK id);
	
	public List<T> getAll();

	public PK save(T o);

	public void update(T o);

	public void delete(T o);
	
	public Object executeNamedQuery(String nameQuery, Map<String, Object> params);

}
