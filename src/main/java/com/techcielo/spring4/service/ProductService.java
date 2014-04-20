package com.techcielo.spring4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techcielo.spring4.bean.Product;
import com.techcielo.spring4.dao.ProductDAO;

@Service("prodSvc")
public class ProductService {
      
       @Autowired
       private ProductDAO prodDAO;
       
       public void setProdDAO(ProductDAO prodDAO) {
              this.prodDAO = prodDAO;
       }
      
       public Product getProduct(int id){
              System.out.println("In Service class...will call DAO");
              return prodDAO.getProduct(id);
       }
}