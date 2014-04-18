package uy.edu.ort.arqliv.obligatorio.persistencia.util;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uy.edu.ort.arqliv.obligatorio.dominio.Container;
import uy.edu.ort.arqliv.obligatorio.persistencia.bo.ContainerBo;

public class EntityCreationTest {

	@Test
	public void CreateShip() {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("resources/spring/config/beanlocations.xml");
    	ContainerBo cBo = (ContainerBo) appContext.getBean("containerBo");
    	Container c = new Container();
    	c.setBrand("AAA");
    	c.setCapacity(1000);
    	c.setCode(111);
    	c.setModel("Modeloooo");
    	cBo.save(c);
	}
	
	/*
	@Test
	public void CreateContainer() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Container c1 = new Container();
		c1.setCode(1);
		c1.setBrand("KKLU");
		c1.setCapacity(5000);
		c1.setModel("Refrigerated");
		
		session.save(c1);

		transaction.commit();
		session.close();
	}
	*/

}
