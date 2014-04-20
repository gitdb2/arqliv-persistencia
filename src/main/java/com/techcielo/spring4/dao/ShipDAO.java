package com.techcielo.spring4.dao;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import uy.edu.ort.arqliv.obligatorio.dominio.Ship;

@Service("shipDAO")
// This the id by which it will be auto wired
public class ShipDAO extends BaseDAO {


	public Ship getShip(long id) {
		System.out.println("In Ship DAO class");
		try {
			return (Ship) getSession().get(Ship.class, id);
		} catch (HibernateException e) {
			System.out.println("Error occured while getting data");
		}
		return null;
	}
	
	  public Serializable save(Ship ship){
		  System.out.println("quiere salvaer " +ship );
          return (Serializable) getSession().save(ship);
      }
	
}