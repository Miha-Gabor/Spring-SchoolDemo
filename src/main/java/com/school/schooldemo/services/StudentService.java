package com.school.schooldemo.services;

import java.util.List;

import com.school.schooldemo.classes.Student;

public interface StudentService {
	public List<Student> getAllStudents();
	public Student getStudentById(int id);
	public void addStudent(Student student);
	public void deleteStudentById(int id);
}	
