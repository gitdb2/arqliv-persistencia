package com.techcielo.spring4.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.edu.ort.arqliv.obligatorio.dominio.Ship;

import com.techcielo.spring4.dao.ShipDAO;

@Service("shipSvc")
public class ShipService implements IEntityService<Ship, Long>{

	@Autowired
	private ShipDAO dao;

	public void setShipDAO(ShipDAO shipDAO) {
		this.dao = shipDAO;
	}

	@Override
	public Ship get(Long id) {
		
		return dao.get(id);
	}

	@Override
	public List<Ship> getAll() {
		
		return dao.getAll();
	}

	@Override
	public Long save(Ship o) {
		return dao.save(o);
	}

	@Override
	public void update(Ship o) {
		dao.update(o);

	}

	@Override
	public void delete(Ship o) {
		dao.delete(o);

	}

	@Override
	public Object executeNamedQuery(String nameQuery, Map<String, Object> params) {
//		Query query = dao.executeNamedQuery("findStockByStockCode", new ObjArray()[], new type);
		return dao.executeNamedQuery(nameQuery, params);
	}


}
