package com.school.schooldemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.schooldemo.classes.Student;

@Repository
public class StudentDAOImplementation implements StudentDAO {

	private EntityManager entityManager;
	
	@Autowired
	public StudentDAOImplementation(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}


	@Override
	public List<Student> getAllStudents() {
		// get current session
		Session currentSession = entityManager.unwrap(Session.class);
		// create Query
		Query<Student> query = currentSession.createQuery("from Student", Student.class);
		
		List<Student> students = query.getResultList();
		return students;
	}

}
