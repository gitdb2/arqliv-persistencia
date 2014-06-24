package uy.edu.ort.arqliv.obligatorio.persistencia.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import uy.edu.ort.arqliv.obligatorio.dominio.Container;

@Repository("containerDAO")
public class ContainerDAO implements IContainerDAO {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Long store(Container obj) {
		Container stored = entityManager.merge(obj);
		return stored.getId();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Long update(Container obj) {
		Container stored = entityManager.find(Container.class, obj.getId());
		if (stored.getCapacity() != obj.getCapacity()) {// si cambia la
														// capacidad chequeo que
														// no este en uso
			if (countContainerUsage(obj.getId()) > 0) {
				return null;
			}
		}

		stored = entityManager.merge(obj);
		return stored.getId();
	}

	/**
	 * cuenta la cantidad de usos para un contenedor ya sea arrival como departure
	 * @param obj
	 * @return
	 */
	private long countContainerUsage(Long containerId) {
		long countInUse;
		Query query = entityManager.createNamedQuery(
				"Container.countUsageForArrival", Long.class);
		query.setParameter("id", containerId);

		countInUse = (Long) query.getSingleResult();
		
		
		//esto puede evitarse si se logra controlar que todo container departed esta si o si como arrived
		query = entityManager.createNamedQuery(//tampoco puede estar usado en un departure
				"Container.countUsageForDepartures", Long.class);
		query.setParameter("id", containerId);

		countInUse += (Long) query.getSingleResult();
		return countInUse;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean delete(Long id) {

		Long countInUse = countContainerUsage(id);
		if (countInUse != null && countInUse == 0) {
			Container obj = entityManager.find(Container.class, id);
			entityManager.remove(obj);
			return true;
		}
		return false;
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public Container findById(Long id) {
		return entityManager.find(Container.class, id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public List<Container> findAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Container> cq = cb.createQuery(Container.class);
		Root<Container> rootEntry = cq.from(Container.class);
		CriteriaQuery<Container> all = cq.select(rootEntry);
		TypedQuery<Container> allQuery = entityManager.createQuery(all);
		return allQuery.getResultList();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public List<Container> executeNamedQuery(String namedQuery,
			Map<String, Object> parameters) {
		TypedQuery<Container> query = entityManager.createNamedQuery(
				namedQuery, Container.class);
		UtilsDAO.setParameters(query, parameters);
		return query.getResultList();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean isContainerInUseForArrival(Long id, Date arrivalDate) {

		Query query = entityManager.createNamedQuery(
				"Container.countUsageOnDateForArrival", Long.class);
		query.setParameter("id", id);
		query.setParameter("arrivalDate", arrivalDate);

		Long countInUse = (Long) query.getSingleResult();
		return countInUse != 0;

	}

	@Override
	public boolean isContainerInUseForDeparture(Long id, Date departureDate) {
		Query query = entityManager.createNamedQuery(
				"Container.countUsageOnDateForDepartures", Long.class);
		query.setParameter("id", id);
		query.setParameter("departureDate", departureDate);

		Long countInUse = (Long) query.getSingleResult();
		return countInUse != 0;
	}

	@Override
	public boolean isContaineriUseForShip(Long id, Long shipId, Date date, EnumDepartureOrArrival departureOrArrival) {
		return false;
	}

}