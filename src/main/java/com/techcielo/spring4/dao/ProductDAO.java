package com.techcielo.spring4.dao;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;

import com.techcielo.spring4.bean.Product;

@Component("prodDAO")
// This the id by which it will be auto wired
public class ProductDAO extends BaseDAO {

	/*
	public Product getProduct(int id) {
		System.out.println("In DAO class");
		return null;
	}
	*/

	public Product getProduct(int id) {
		System.out.println("In DAO class");
		try {
			return (Product) getSession().get(Product.class, id);
		} catch (HibernateException e) {
			System.out.println("Error occured while getting data");
		}
		return null;
	}
}