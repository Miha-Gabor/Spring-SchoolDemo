package com.school.schooldemo.dao;

import java.util.List;

import com.school.schooldemo.classes.Lession;
import com.school.schooldemo.classes.Student;

public interface LessionDAO {
	public List<Lession> getAllLessions();
	public List<Student> getLessionStudents(int lessionId);
	public Lession getLessionById(int lessionId);
}
