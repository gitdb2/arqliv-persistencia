package uy.edu.ort.arqliv.obligatorio.persistencia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.edu.ort.arqliv.obligatorio.persistencia.bean.Product;
import uy.edu.ort.arqliv.obligatorio.persistencia.dao.ProductDAO;

@Service("prodSvc")
public class ProductService implements IEntityService<Product, Integer> {

	@Autowired
	private ProductDAO dao;

	public void setProdDAO(ProductDAO prodDAO) {
		this.dao = prodDAO;
	}

	@Override
	public Product get(Integer id) {
		return dao.get(id);
	}

	@Override
	public List<Product> getAll() {
		return dao.getAll();
	}

	@Override
	public Integer save(Product o) {
		return dao.save(o);
	}

	@Override
	public void update(Product o) {
		dao.update(o);
	}

	@Override
	public void delete(Product o) {
		dao.delete(o);
	}

}