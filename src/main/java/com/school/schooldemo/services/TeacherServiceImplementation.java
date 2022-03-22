package com.school.schooldemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.schooldemo.classes.Teacher;
import com.school.schooldemo.dao.TeacherDAO;

@Service
public class TeacherServiceImplementation implements TeacherService {

	private TeacherDAO teacherDAO;
	
	@Autowired
	public TeacherServiceImplementation(TeacherDAO teacherDAO) {
		super();
		this.teacherDAO = teacherDAO;
	}

	@Override
	@Transactional
	public List<Teacher> getAllTeachers() {
		return teacherDAO.getAllTeachers();
	}

	@Override
	@Transactional
	public Teacher getTeacherById(int teacherId) {
		return teacherDAO.getTeacherById(teacherId);
	}

}
