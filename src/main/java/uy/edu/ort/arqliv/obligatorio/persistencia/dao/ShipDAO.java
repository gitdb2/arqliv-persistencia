package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;

import uy.edu.ort.arqliv.obligatorio.dominio.Ship;

@Component("shipDAO")
public class ShipDAO extends BaseDAO<Ship, Long> {

	public Ship getShip(long id) {
		try {
			return super.get(id);
		} catch (HibernateException e) {
			//TODO loguear
			System.out.println("Error en get ship");
		}
		return null;
	}

	public Long save(Ship ship) {
		try {
			return super.save(ship);
		} catch (HibernateException e) {
			//TODO loguear
			System.out.println("Error en save ship");
		}
		return null;
	}
	
	public void update(Ship ship) {
		try {
			super.update(ship);
		} catch (HibernateException e) {
			//TODO loguear
			System.out.println("Error en update ship");
		}
	}
	
	public void delete(Ship ship) {
		try {
			super.delete(ship);
		} catch (HibernateException e) {
			//TODO loguear
			System.out.println("Error en delete ship");
		}
	}
	
	public List<Ship> getAll() {
		try {
			return super.getAll();
		} catch (HibernateException e) {
			//TODO loguear
			System.out.println("Error en getAll ship");
		}
		return null;
	}

}