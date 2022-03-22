package com.school.schooldemo.services;

import java.util.List;

import com.school.schooldemo.classes.Teacher;

public interface TeacherService {
	public List<Teacher> getAllTeachers();
	public Teacher getTeacherById(int teacherId);
}
