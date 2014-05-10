package uy.edu.ort.arqliv.obligatorio.persistencia.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uy.edu.ort.arqliv.obligatorio.dominio.Ship;
import uy.edu.ort.arqliv.obligatorio.persistencia.dao.IShipDAO;

public class Main {

	public static void main(String[] args) {
		// Build application context by reading spring-config.xml
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] { "spring-config.xml" });

		IShipDAO shipDAO = (IShipDAO) ctx.getBean("shipDAO");
		
		Ship ship = new Ship(2.0, 523, 55, "UY", 1978, "deli");

		//save
		System.out.println("-----------------------------");
		shipDAO.store(ship);
		System.out.println("-----------------------------");
	}
}