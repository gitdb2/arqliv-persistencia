package uy.edu.ort.arqliv.obligatorio.persistencia.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uy.edu.ort.arqliv.obligatorio.dominio.Ship;
import uy.edu.ort.arqliv.obligatorio.persistencia.bean.Product;
import uy.edu.ort.arqliv.obligatorio.persistencia.service.IEntityService;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// Build application context by reading spring-config.xml
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] { "spring-config.xml" });

		// Get an instance of ProductService class;
//		IEntityService<Product, Integer> prdSvc = (IEntityService<Product, Integer>) ctx.getBean("prodSvc");
//
//		Product p = new Product();
//		p.setName("pizza");
//		Integer persisted = (Integer) prdSvc.save(p);
//		System.out.println("---------------" + persisted);

		// Call getProduct method of ProductService
//		Product fromdb = prdSvc.get(persisted);
//		System.out.println("--->" + fromdb);

		IEntityService<Ship, Long> shipSvc = (IEntityService<Ship, Long>) ctx.getBean("shipSvc");

		Ship ship = new Ship(2.0, 523, 55, "UY", 1978, "deli");

		//save
		long id = shipSvc.save(ship);

		//get
		Ship ship2 = shipSvc.get(id);
		System.out.println("--->" + ship2);
		
		//update
		ship2.setFlag("BR");
		ship2.setName("deli2");
		shipSvc.update(ship2);
		System.out.println("---> Antes de update en la base " + ship2);
		
		Ship ship3 = shipSvc.get(id);
		System.out.println("---> Despues de update en la base " + ship3);
		
		//delete
		
		//getAll
		
	}
}