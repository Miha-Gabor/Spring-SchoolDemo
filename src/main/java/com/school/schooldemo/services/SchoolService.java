package com.school.schooldemo.services;

import java.util.List;

import com.school.schooldemo.classes.Lession;
import com.school.schooldemo.classes.Student;
import com.school.schooldemo.classes.Teacher;
import com.school.schooldemo.classes.Timeslot;

public interface SchoolService {
	
	// Timetable
	public Timeslot getTimetableById(int id);
	
	// Lession (class in database -- will change later)
	public List<Lession> getAllLessions();
	public List<Student> getLessionStudents(int lessionId);
	public Lession getLessionById(int lessionId);
	
	// Student
	public List<Student> getAllStudents();
	public Student getStudentById(int id);
	public void addStudent(Student student);
	public void deleteStudentById(int id);
	
	// Teacher
	public List<Teacher> getAllTeachers();
	public Teacher getTeacherById(int teacherId);
}
