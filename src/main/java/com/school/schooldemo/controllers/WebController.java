package com.school.schooldemo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.school.schooldemo.classes.Timeslot;
import com.school.schooldemo.services.LessionService;
import com.school.schooldemo.services.StudentService;
import com.school.schooldemo.services.TeacherService;

@Controller
public class WebController {
	
private LessionService lessionService;
private TeacherService teacherService;
private StudentService studentService;
	
	@Autowired
	public WebController(LessionService theLessionService, TeacherService theTeacherService, StudentService theStudentService) {
		lessionService = theLessionService;
		teacherService = theTeacherService;
		studentService = theStudentService;
	}
	
	
	@GetMapping("lessions")
	public String getLessions(Model model) {
		model.addAttribute("lessions", lessionService.getAllLessions());
		return "lessions";
	}
	
	@GetMapping("lessions/{lessionId}")
	public String getLessions(Model model, @PathVariable int lessionId) {
		model.addAttribute("students", lessionService.getLessionStudents(lessionId));
		model.addAttribute("lession", lessionService.getLessionById(lessionId));
		return "lession";
	}
	
	@GetMapping("timetable")
	public String getTimetable(){
		return "timetable";
	}
	
	@GetMapping("timetable/{lessionId}")
	public String getLessionTimetable(Model model, @PathVariable int lessionId){
		List<Integer> timetableSlots = new ArrayList<>(0);
		for(int i=0; i<=44; i++) {
			timetableSlots.add(0);
		}
		// iterate trough all of the list
		if(lessionService.getAllLessions().get(lessionId).timeslots.size() > 1) {
			for(Timeslot timetable: lessionService.getAllLessions().get(lessionId).timeslots) {
				timetableSlots.set(timetable.getId(), timetable.getTimeslot());
		}
}
		
		
		model.addAttribute("timetableSlots", timetableSlots);
		model.addAttribute("title", lessionService.getAllLessions().get(lessionId).getName());
		System.out.println(timetableSlots);
		return "timetable";
	}
	
	@GetMapping("teachers")
	public String getAllTeachers(Model model) {
		model.addAttribute("teachers", teacherService.getAllTeachers());
		return "teachers";
	}
	
	@GetMapping("teacher/{teacherId}")
	public String getTeacherInfo(Model model, @PathVariable int teacherId) {
		String userName = teacherService.getAllTeachers().get(teacherId).getName() + " " 
				+ teacherService.getAllTeachers().get(teacherId).getSurname();
		model.addAttribute("user", userName);
		model.addAttribute("teacher", true);
		model.addAttribute("lessions", teacherService.getAllTeachers().get(teacherId).lessions);
		return "user-info";
	}
	
	@GetMapping("student/{studentId}")
	public String getStudentInfo(Model model, @PathVariable int studentId) {
		String userName = studentService.getAllStudents().get(studentId).getName() + " " 
				+ studentService.getAllStudents().get(studentId).getSurname();
		model.addAttribute("user", userName);
		model.addAttribute("teacher", false);
		model.addAttribute("lessions", studentService.getAllStudents().get(studentId).lessions);
		return "user-info";
	}
	
}
