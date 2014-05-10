package uy.edu.ort.arqliv.obligatorio.persistencia.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uy.edu.ort.arqliv.obligatorio.dominio.Arrival;
import uy.edu.ort.arqliv.obligatorio.dominio.Container;
import uy.edu.ort.arqliv.obligatorio.dominio.Ship;
import uy.edu.ort.arqliv.obligatorio.persistencia.dao.IArrivalDAO;
import uy.edu.ort.arqliv.obligatorio.persistencia.dao.IContainerDAO;
import uy.edu.ort.arqliv.obligatorio.persistencia.dao.IShipDAO;

public class Main {

	public static void main(String[] args) {
		testShip();
		testContainer();
		testArrival();
		//testUser();
	}

	private static void testShip() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] { "spring-config.xml" });
		
		System.out.println("------------------------------");

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
		
		System.out.println("------------------------------");
	}
	
	private static void testContainer() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] { "spring-config.xml" });
		
		System.out.println("------------------------------");

		IContainerDAO containerDAO = (IContainerDAO) ctx.getBean("containerDAO");
		
		Container cont = new Container();
		cont.setBrand("KKLU");
		cont.setCapacity(100);
		cont.setCode(1111);
		cont.setModel("REF");
		
		System.out.println(cont.toString());
		
		Long id = containerDAO.store(cont);
		
		Container cont2 = containerDAO.findById(id);
		cont2.setBrand("MAEU");
		cont2.setModel("DRY");
		containerDAO.store(cont2);
		
		System.out.println(cont2.toString());
		
		containerDAO.delete(cont2.getId());
		
		List<Container> todos = containerDAO.findAll();
		
		for (Container c : todos) {
			System.out.println(c.toString());
		}
		
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("model", "DRY");
		List<Container> namedQuery = containerDAO.executeNamedQuery("Container.findByModel", parameters);
		
		for (Container c : namedQuery) {
			System.out.println(c.toString());
		}
		
		System.out.println("------------------------------");
	}
	
	private static void testArrival() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] { "spring-config.xml" });
		
		System.out.println("------------------------------");

		IArrivalDAO arrivalDAO = (IArrivalDAO) ctx.getBean("arrivalDAO");
		
		Arrival arrival = new Arrival();
		arrival.setArrivalDate(new Date());
		arrival.setContainersDescriptions("bananas manzanas peras");
		arrival.setShipOrigin("India");

		IShipDAO shipDAO = (IShipDAO) ctx.getBean("shipDAO");
		Ship ship = new Ship(2.0, 523, 55, "IN", 1978, "Barco de india");
		shipDAO.store(ship);
		
		IContainerDAO containerDAO = (IContainerDAO) ctx.getBean("containerDAO");
		Container cont = new Container();
		cont.setBrand("MOLU");
		cont.setCapacity(1000);
		cont.setCode(2222);
		cont.setModel("CHORI");
		containerDAO.store(cont);
		
		Container cont2 = new Container();
		cont2.setBrand("MAEU");
		cont2.setCapacity(5000);
		cont2.setCode(3333);
		cont2.setModel("T1000");
		containerDAO.store(cont2);
		
		List<Container> containers = new ArrayList<Container>(2);
		containers.add(cont);
		containers.add(cont2);
		
		arrival.setShip(ship);
		arrival.setContainers(containers);
		
		Long id = arrivalDAO.store(arrival);
		
		System.out.println(arrival.toString());
		
		Arrival arrival2 = arrivalDAO.findById(id);
		arrival2 = arrivalDAO.initializeAndUnproxy(arrival2);
		
		arrival2.setArrivalDate(new Date());
		arrival2.setContainersDescriptions("Nueva descripcion de contenedores");
		arrival2.getContainers().remove(0);
		arrivalDAO.store(arrival2);
		
		List<Arrival> todos = arrivalDAO.findAll();
		
		for (Arrival a : todos) {
			System.out.println(a.toString());
		}
		
		System.out.println("------------------------------");
	}
	
	private static void testUser() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] { "spring-config.xml" });
		
		System.out.println("------------------------------");

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
		
		System.out.println("------------------------------");
	}
	
}