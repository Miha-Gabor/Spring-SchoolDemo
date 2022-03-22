package com.school.schooldemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.school.schooldemo.classes.Teacher;

@Repository
public class TeacherDAOImplementation implements TeacherDAO {

	private EntityManager entityManager;
	
	@Autowired
	public TeacherDAOImplementation(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Teacher> getAllTeachers() {
		// get current session
		Session currentSession = entityManager.unwrap(Session.class);
		// create Query
		Query<Teacher> query = currentSession.createQuery("from Teacher", Teacher.class);
		
		List<Teacher> teachers = query.getResultList();
		return teachers;
	}

	@Override
	public Teacher getTeacherById(int teacherId) {
		// get current session
		Session currentSession = entityManager.unwrap(Session.class);
		// create Query
		Query<Teacher> query = currentSession.createQuery("from Teacher", Teacher.class);
		
		Teacher teacherResult = null;
		for (Teacher teacher : query.getResultList()) {
			if(teacher.getId() == teacherId) {
				teacherResult = teacher;
				break;
			}
		};
		return teacherResult;
	}
}
