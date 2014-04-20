package com.techcielo.spring4.dao;

import org.springframework.stereotype.Component;

import uy.edu.ort.arqliv.obligatorio.dominio.Ship;

@Component("shipDAO")
// This the id by which it will be auto wired
public class ShipDAO extends BaseDAO<Ship, Long> {


//	public Ship getShip(long id) {
//		System.out.println("In Ship DAO class");
//		try {
//			return  super.get(id);
//		} catch (HibernateException e) {
//			System.out.println("Error occured while getting data");
//		}
//		return null;
//	}
//	
//	  public Long save(Ship ship){
//		  System.out.println("quiere salvaer " +ship );
//          return  super.save(ship);
//      }
//	
}