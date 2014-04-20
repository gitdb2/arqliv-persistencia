package com.techcielo.spring4.dao;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;

import com.techcielo.spring4.bean.Product;

@Component("prodDAO")
// This the id by which it will be auto wired
public class ProductDAO extends BaseDAO<Product, Integer> {


	public Product get(int id) {
		System.out.println("In DAO class");
		try {
			return  super.get(id);
		} catch (HibernateException e) {
			System.out.println("Error occured while getting data");
		}
		return null;
	}
//	
//	  public Integer save(Product product){
//		  System.out.println("quiere salvaer " +product );
//          return super.save(product);
//      }
	
}