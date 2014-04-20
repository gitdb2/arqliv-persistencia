package com.techcielo.spring4.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.techcielo.spring4.bean.Product;
import com.techcielo.spring4.service.ProductService;

public class Main {
	
	public static void main(String[] args) {
		// Build application context by reading spring-config.xml
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String[] { "./resources/spring-config.xml" });

		// Get an instance of ProductService class;
		ProductService prdSvc = (ProductService) ctx.getBean("prodSvc");

		 //Call getProduct method of ProductService
        Product prod = prdSvc.getProduct(1);
       
        System.out.println("Got the product:"+prod.getName());
	}
}