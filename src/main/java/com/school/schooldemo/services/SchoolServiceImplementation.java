package com.school.schooldemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.schooldemo.classes.Lession;
import com.school.schooldemo.classes.Student;
import com.school.schooldemo.classes.Teacher;
import com.school.schooldemo.classes.Timeslot;
import com.school.schooldemo.dao.LessionDAO;
import com.school.schooldemo.dao.StudentDAO;
import com.school.schooldemo.dao.TeacherDAO;
import com.school.schooldemo.dao.TimeslotDAO;

@Service
public class SchoolServiceImplementation implements SchoolService {

	private TimeslotDAO timeslotDAO;
	private LessionDAO lessionDAO;
	private StudentDAO studentDAO;
	private TeacherDAO teacherDAO;
	
	@Autowired
	public SchoolServiceImplementation(TimeslotDAO timeslotDAO, LessionDAO lessionDAO, StudentDAO studentDAO, TeacherDAO teacherDAO) {
		this.timeslotDAO = timeslotDAO;
		this.lessionDAO = lessionDAO;
		this.studentDAO = studentDAO;
		this.teacherDAO = teacherDAO;
	}

	// Timetable
	@Override
	@Transactional
	public Timeslot getTimetableById(int id) {
		return timeslotDAO.getTimeslotById(id);
	}
	
	// Lession
	@Override
	@Transactional
	public List<Lession> getAllLessions() {
		return lessionDAO.getAllLessions();
	}

	@Override
	@Transactional
	public List<Student> getLessionStudents(int lessionId) {
		return lessionDAO.getLessionStudents(lessionId);
	}

	@Override
	@Transactional
	public Lession getLessionById(int lessionId) {
		return lessionDAO.getLessionById(lessionId);
	}

	// Student
	@Override
	@Transactional
	public List<Student> getAllStudents() {
		return studentDAO.getAllStudents();
	}

	@Override
	@Transactional
	public void addStudent(Student student) {
		studentDAO.addStudent(student);
	}

	@Override
	@Transactional
	public void deleteStudentById(int id) {
		studentDAO.deleteStudentById(id);
		
	}

	@Override
	@Transactional
	public Student getStudentById(int id) {
		return studentDAO.getStudentById(id);
	}
	
	// Teacher
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
