package com.school.schooldemo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.school.schooldemo.classes.Lession;
import com.school.schooldemo.classes.Student;
import com.school.schooldemo.classes.Teacher;
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
	
	// ADMIN
	
	// List all students
	@GetMapping("admin/students")
	public String getAllStudents(Model model){
		List<String> studentList = new ArrayList<>();
		for(Student student: studentService.getAllStudents()) {
			studentList.add(student.toString());
		}
		model.addAttribute("studentList", studentService.getAllStudents());
		model.addAttribute("list", studentList);
		model.addAttribute("student", true);
		return "admin-panel";
	}
	
	// List all lessions
	@GetMapping("admin/lessions")
	public String getAllLessions(Model model){
		List<String> lessionList = new ArrayList<>();
		for(Lession lession: lessionService.getAllLessions()) {
			lessionList.add(lession.toString());
		}
		model.addAttribute("list", lessionList);
		model.addAttribute("lession", true);
		model.addAttribute("lessions", lessionService.getAllLessions());
		
		return "admin-panel";
	}
	
	@GetMapping("admin/teachers")
	public String getAllTeachers(Model model){
		List<String> lessionList = new ArrayList<>();
		for(Teacher teacher: teacherService.getAllTeachers()){
			lessionList.add(teacher.toString());
		}
		model.addAttribute("list", lessionList);
		model.addAttribute("teacher", true);
		model.addAttribute("teachers", teacherService.getAllTeachers());
		return "admin-panel";
	}
	
	@GetMapping("admin/deleteStudent/{studentId}")
	public String deleteStudent(@PathVariable int studentId, Model model) {
		System.out.println(studentId);
		studentService.deleteStudentById(studentId);
		return "redirect:/admin/students";
	}
	
	// STUDENT
	
	@GetMapping("student/{studentId}")
	public String getStudentInfo(Model model, @PathVariable int studentId) {
		Student student = studentService.getStudentById(studentId);
		String userName = student.getName() + " " + student.getSurname();
		
		
		List<Lession> lessions = new ArrayList<>(lessionService.getAllLessions());
		lessions.retainAll(student.lessions);
	
		List<Lession> avalibleLessions = new ArrayList<>(lessionService.getAllLessions());
		avalibleLessions.removeAll(student.lessions);
		
		model.addAttribute("title", userName);
		model.addAttribute("student", student);
		model.addAttribute("teacher", false);
		model.addAttribute("buttons", true);
	
		model.addAttribute("list", lessions);
		model.addAttribute("avalibleList", avalibleLessions);
		model.addAttribute("lessions", student.lessions);
		return "user-info";
	}
	
	@GetMapping("student/{studentId}/add_{lessionId}")
	public String addStudentLession(@PathVariable int studentId, @PathVariable int lessionId) {
		Lession lession = lessionService.getAllLessions().get(lessionId - 1);
		studentService.getStudentById(studentId).lessions.add(lession);
		// why does this make things work?
		System.out.println(studentService.getStudentById(studentId).lessions);
		return "redirect:/student/" + studentId;
	}
	
	@GetMapping("student/{studentId}/remove_{lessionId}")
	public String removeStudentLession(@PathVariable int studentId, @PathVariable int lessionId) {
		Lession lession = lessionService.getAllLessions().get(lessionId - 1);
		studentService.getStudentById(studentId).lessions.remove(lession);
		// why does this make things work?
		System.out.println(studentService.getStudentById(studentId).lessions);
		return "redirect:/student/" + studentId;
	}
	
	@GetMapping("newStudent")
	public String getForm(Model model) {
		model.addAttribute("title", "New student form");
		model.addAttribute("student", new Student());
		return "user-form";
	}
		
	@PostMapping("addNewStudent")
	public String postForm(@ModelAttribute("student") Student theStudent, Model model) {
		model.addAttribute("student", theStudent);
		model.addAttribute("formCheck", true);
		String formOutput = "Added " + theStudent.toString();
		model.addAttribute("formOutput", formOutput);
		System.out.println(theStudent.toString());
		
		theStudent.setId(0);
		studentService.addStudent(theStudent);
		return "redirect:/admin/students";
	}
	
	// LESSION
	
	@GetMapping("lession/{lessionId}")
	public String getLessions(Model model, @PathVariable int lessionId) {
		
		List<String> students = new ArrayList<>(0);
		for(Student student: lessionService.getLessionStudents(lessionId)) {
			students.add(student.getName() + " " + student.getSurname());
		}
		
		model.addAttribute("list", students);
		model.addAttribute("title", lessionService.getLessionById(lessionId).getName());
		model.addAttribute("buttons", false);
		return "user-info";
	}
	
	
	// TEACHER
	
	@GetMapping("teacher/{teacherId}")
	public String getTeacherInfo(Model model, @PathVariable int teacherId) {
		String userName = teacherService.getAllTeachers().get(teacherId).getName() + " " 
				+ teacherService.getAllTeachers().get(teacherId).getSurname();
		
		List<String> lessions = new ArrayList<>(0);
		for(Lession lession: teacherService.getAllTeachers().get(teacherId).lessions) {
			lessions.add(lession.getName());
		}
		model.addAttribute("title", userName);
		model.addAttribute("buttons", true);
		model.addAttribute("teacher", true);
		model.addAttribute("list", lessions);
		model.addAttribute("lessions", teacherService.getAllTeachers().get(teacherId).lessions);
		return "user-info";
	}
	
	// TIMETABLE
	
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
	
}
