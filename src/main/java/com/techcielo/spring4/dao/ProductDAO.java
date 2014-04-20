package com.techcielo.spring4.dao;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import com.techcielo.spring4.bean.Product;

@Service("prodDAO")
// This the id by which it will be auto wired
public class ProductDAO extends BaseDAO {


	public Product getProduct(int id) {
		System.out.println("In DAO class");
		try {
			return (Product) getSession().get(Product.class, id);
		} catch (HibernateException e) {
			System.out.println("Error occured while getting data");
		}
		return null;
	}
	
	  public Serializable save(Product product){
		  System.out.println("quiere salvaer " +product );
          return (Serializable) getSession().save(product);
      }
	
}