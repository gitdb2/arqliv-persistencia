package com.techcielo.spring4.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	
    @Id
    @Column(name="ProductID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prodID;
   
    @Column(name="ProductName")
    private String name;
               
    public int getProdID() {
        return prodID;
    }

    public void setProdID(int prodID) {
        this.prodID = prodID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    @Override
    public String toString() {
        return "Product{" + "prodID=" + prodID + ", name=" + name + '}';
    }
}
