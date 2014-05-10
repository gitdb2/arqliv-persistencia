package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.List;

import uy.edu.ort.arqliv.obligatorio.dominio.Ship;

public interface IShipDAO {

	public void store(Ship obj);

	public void delete(Long id);

	public Ship findById(Long id);

	public List<Ship> findAll();

}
