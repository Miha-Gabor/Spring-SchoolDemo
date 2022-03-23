package com.school.schooldemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.schooldemo.classes.Student;
import com.school.schooldemo.dao.StudentDAO;

@Service
public class StudentServiceImplementation implements StudentService {

	private StudentDAO studentDao;
	@Autowired
	public StudentServiceImplementation(StudentDAO studentDao) {
		super();
		this.studentDao = studentDao;
	}

	@Override
	@Transactional
	public List<Student> getAllStudents() {
		return studentDao.getAllStudents();
	}

	@Override
	@Transactional
	public void addStudent(Student student) {
		studentDao.addStudent(student);
	}

	@Override
	@Transactional
	public void deleteStudentById(int id) {
		studentDao.deleteStudentById(id);
		
	}

	@Override
	@Transactional
	public Student getStudentById(int id) {
		return studentDao.getStudentById(id);
	}
	
	

}
