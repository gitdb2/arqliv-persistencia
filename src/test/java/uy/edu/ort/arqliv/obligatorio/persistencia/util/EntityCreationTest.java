package uy.edu.ort.arqliv.obligatorio.persistencia.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import uy.edu.ort.arqliv.obligatorio.dominio.Container;

public class EntityCreationTest {

	@Test
	public void CreateArrival() {
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

}
