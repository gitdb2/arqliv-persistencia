package com.techcielo.spring4.service;

import java.io.Serializable;
import java.util.List;

public interface IEntityService<T, PK extends Serializable> {


	public T get(PK id);
	
	public List<T> getAll();

	public PK save(T o);

	public void update(T o);

	public void delete(T o);

	
}
