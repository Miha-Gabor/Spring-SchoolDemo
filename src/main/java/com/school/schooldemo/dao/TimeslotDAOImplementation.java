package com.school.schooldemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.schooldemo.classes.Timeslot;

@Repository
public class TimeslotDAOImplementation implements TimeslotDAO {

	private EntityManager entityManager;
	
	@Autowired
	public TimeslotDAOImplementation(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}


	@Override
	public Timeslot getTimeslotById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		// create Query
		Query<Timeslot> query = currentSession.createQuery("from Timeslot", Timeslot.class);
		
		List<Timeslot> timeslots = query.getResultList();
		Timeslot timeslot = timeslots.get(id);
		return timeslot;
	}

}
