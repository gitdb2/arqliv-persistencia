package com.techcielo.spring4.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.edu.ort.arqliv.obligatorio.dominio.Ship;

import com.techcielo.spring4.dao.ShipDAO;

@Service("shipSvc")
public class ShipService {

	@Autowired
	private ShipDAO shipDAO;

	public void setShipDAO(ShipDAO shipDAO) {
		this.shipDAO = shipDAO;
	}

	public Ship getShip(long id) {
		System.out.println("In Service class...will call DAO");
		return shipDAO.get(id);
	}

	public Serializable save(Ship ship) {
		return shipDAO.save(ship);
	}

}
