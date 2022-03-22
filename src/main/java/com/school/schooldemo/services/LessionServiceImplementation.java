package com.school.schooldemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.schooldemo.classes.Lession;
import com.school.schooldemo.classes.Student;
import com.school.schooldemo.dao.LessionDAO;

@Service
public class LessionServiceImplementation implements LessionService {

	private LessionDAO lessionDAO;
	
	@Autowired
	public LessionServiceImplementation(LessionDAO theLessionDAO) {
		lessionDAO = theLessionDAO;
	}
	
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
	public Lession getLessionById(int lessionId) {
		return lessionDAO.getLessionById(lessionId);
	}
	
	

}
