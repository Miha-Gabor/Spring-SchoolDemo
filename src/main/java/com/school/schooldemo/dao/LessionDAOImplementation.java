package com.school.schooldemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.schooldemo.classes.Lession;
import com.school.schooldemo.classes.Student;

@Repository
public class LessionDAOImplementation implements LessionDAO {

	private EntityManager entityManager;
	
	@Autowired
	public LessionDAOImplementation(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Lession> getAllLessions() {
		// get current session
		Session currentSession = entityManager.unwrap(Session.class);
		// create Query
		Query<Lession> query = currentSession.createQuery("from Lession", Lession.class);
		
		List<Lession> lessions = query.getResultList();
		return lessions;
	}
	
	@Override
	public List<Student> getLessionStudents(int lessionId){
		// get current session
		Session currentSession = entityManager.unwrap(Session.class);
		// create Query
		Query<Lession> query = currentSession.createQuery("from Lession", Lession.class);
		
		List<Lession> lessions = query.getResultList();
		
		List<Student> studentList = lessions.get(lessionId).students;
		return studentList;
	}

	@Override
	public Lession getLessionById(int lessionId) {
		Session currentSession = entityManager.unwrap(Session.class);
		// create Query
		Query<Lession> query = currentSession.createQuery("from Lession", Lession.class);
		
		List<Lession> lessions = query.getResultList();
		return lessions.get(lessionId);
	}
	
	
	

}
