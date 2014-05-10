package uy.edu.ort.arqliv.obligatorio.persistencia.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Long id = shipDAO.store(ship);
		
		Ship ship2 = shipDAO.findById(id);
		ship2.setFlag("BR");
		ship2.setName("nuevoNombre");
		shipDAO.store(ship2);
		
		//shipDAO.delete(ship2.getId());
		
		List<Ship> todos = shipDAO.findAll();
		
		for (Ship s : todos) {
			System.out.println(s.toString());
		}
		
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("flag", "BR");
		List<Ship> byNamedQuery = shipDAO.executeNamedQuery("Ship.findByFlag", parameters);
		
		for (Ship s : byNamedQuery) {
			System.out.println(s.toString());
		}

	}
}