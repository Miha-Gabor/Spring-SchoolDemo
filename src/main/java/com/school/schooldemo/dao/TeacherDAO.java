package com.school.schooldemo.dao;

import java.util.List;

import com.school.schooldemo.classes.Teacher;

public interface TeacherDAO {
	public List<Teacher> getAllTeachers();
	public Teacher getTeacherById(int teacherId);

}
