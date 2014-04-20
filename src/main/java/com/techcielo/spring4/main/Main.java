package com.techcielo.spring4.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uy.edu.ort.arqliv.obligatorio.dominio.Ship;

import com.techcielo.spring4.bean.Product;
import com.techcielo.spring4.service.ProductService;
import com.techcielo.spring4.service.ShipService;

public class Main {

	public static void main(String[] args) {
		// Build application context by reading spring-config.xml
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String[] { "spring-config.xml" });

		// Get an instance of ProductService class;
		ProductService prdSvc = (ProductService) ctx.getBean("prodSvc");

		Product p = new Product();
		p.setName("pizza");
		Integer persisted = (Integer) prdSvc.save(p);
		System.out.println("---------------" + persisted);

		// Call getProduct method of ProductService
		Product fromdb = prdSvc.getProduct(persisted);
		System.out.println("--->" + fromdb);

		ShipService shipSvc = (ShipService) ctx.getBean("shipSvc");

		Ship ship = new Ship(2.0, 523, 55, "UY", 1978, "deli");

		long id = (Long) shipSvc.save(ship);

		Ship ship2 = shipSvc.getShip(id);
		System.out.println("--->" + ship2);

	}
}