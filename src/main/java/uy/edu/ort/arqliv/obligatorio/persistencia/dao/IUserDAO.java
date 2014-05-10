package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.List;
import java.util.Map;

import uy.edu.ort.arqliv.obligatorio.dominio.User;

public interface IUserDAO {

	public Long store(User obj);

	public void delete(Long id);

	public User findById(Long id);

	public List<User> findAll();
	
	public List<User> executeNamedQuery(String namedQuery, Map<String, String> parameters);

}
