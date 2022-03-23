package com.school.schooldemo.dao;

import java.util.List;

import com.school.schooldemo.classes.Student;

public interface StudentDAO {
	public List<Student> getAllStudents();
	public Student getStudentById(int id);
	public void addStudent(Student student);
	public void deleteStudentById(int id);
}
