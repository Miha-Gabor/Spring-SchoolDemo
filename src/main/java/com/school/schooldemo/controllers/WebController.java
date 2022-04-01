package com.school.schooldemo.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.school.schooldemo.services.SchoolService;

@Controller
public class WebController {
	
	private SchoolService schoolService;
	
	@Autowired
	public WebController(SchoolService theSchoolService) {
		schoolService = theSchoolService;
	}
	
	@GetMapping("/")
	public String index() {
	    // irreveland code here
	    return "redirect:/admin/students";
	}
	
	// ADMIN
	
	// List all students
	@GetMapping("admin/students")
	public String getAllStudents(Model model){
		List<String> studentList = new ArrayList<>();
		for(Student student: schoolService.getAllStudents()) {
			studentList.add(student.toString());
		}
		model.addAttribute("studentList", schoolService.getAllStudents());
		model.addAttribute("list", studentList);
		model.addAttribute("student", true);
		return "admin-panel";
	}
	
	// List all lessions
	@GetMapping("admin/lessions")
	public String getAllLessions(Model model){
		List<String> lessionList = new ArrayList<>();
		for(Lession lession: schoolService.getAllLessions()) {
			lessionList.add(lession.toString());
		}
		model.addAttribute("list", lessionList);
		model.addAttribute("lession", true);
		model.addAttribute("lessions", schoolService.getAllLessions());
		
		return "admin-panel";
	}
	
	@GetMapping("admin/teachers")
	public String getAllTeachers(Model model){
		List<String> lessionList = new ArrayList<>();
		for(Teacher teacher: schoolService.getAllTeachers()){
			lessionList.add(teacher.toString());
		}
		model.addAttribute("list", lessionList);
		model.addAttribute("teacher", true);
		model.addAttribute("teachers", schoolService.getAllTeachers());
		return "admin-panel";
	}
	
	@GetMapping("admin/deleteStudent/{studentId}")
	public String deleteStudent(@PathVariable int studentId, Model model) {
		System.out.println(studentId);
		schoolService.deleteStudentById(studentId);
		return "redirect:/admin/students";
	}
	
	// STUDENT
	
	@GetMapping("student/{studentId}")
	public String getStudentInfo(Model model, @PathVariable int studentId) {
		Student student = schoolService.getStudentById(studentId);
		String userName = student.getName() + " " + student.getSurname();
		
		
		List<Lession> lessions = new ArrayList<>(schoolService.getAllLessions());
		lessions.retainAll(student.lessions);
	
		List<Lession> avalibleLessions = new ArrayList<>(schoolService.getAllLessions());
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
		Lession lession = schoolService.getAllLessions().get(lessionId - 1);
		schoolService.getStudentById(studentId).lessions.add(lession);
		// why does this make things work?
		System.out.println(schoolService.getStudentById(studentId).lessions);
		return "redirect:/student/" + studentId;
	}
	
	@GetMapping("student/{studentId}/remove_{lessionId}")
	public String removeStudentLession(@PathVariable int studentId, @PathVariable int lessionId) {
		Lession lession = schoolService.getAllLessions().get(lessionId - 1);
		schoolService.getStudentById(studentId).lessions.remove(lession);
		// why does this make things work?
		System.out.println(schoolService.getStudentById(studentId).lessions);
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
		schoolService.addStudent(theStudent);
		return "redirect:/admin/students";
	}
	
	// LESSION
	
	@GetMapping("lession/{lessionId}")
	public String getLessions(Model model, @PathVariable int lessionId) {
		
		List<String> students = new ArrayList<>(0);
		for(Student student: schoolService.getLessionStudents(lessionId)) {
			students.add(student.getName() + " " + student.getSurname());
		}
		
		model.addAttribute("list", students);
		model.addAttribute("title", schoolService.getLessionById(lessionId).getName());
		model.addAttribute("buttons", false);
		return "user-info";
	}
	
	
	// TEACHER
	
	@GetMapping("teacher/{teacherId}")
	public String getTeacherInfo(Model model, @PathVariable int teacherId) {
		String userName = schoolService.getAllTeachers().get(teacherId).getName() + " " 
				+ schoolService.getAllTeachers().get(teacherId).getSurname();
		
		List<String> lessions = new ArrayList<>(0);
		for(Lession lession: schoolService.getAllTeachers().get(teacherId).lessions) {
			lessions.add(lession.getName());
		}
		model.addAttribute("title", userName);
		model.addAttribute("buttons", true);
		model.addAttribute("teacher", true);
		model.addAttribute("list", lessions);
		model.addAttribute("lessions", schoolService.getAllTeachers().get(teacherId).lessions);
		return "user-info";
	}
	
	// TIMETABLE
	
	@GetMapping("timetable/lession/{lessionId}")
	public String getLessionTimetable(Model model, @PathVariable int lessionId){
		
		List<Integer> timetableSlots = new ArrayList<>(0);
		for(int i=0; i<=44; i++) {
			timetableSlots.add(99);
		}
		// iterate trough all of the list
		if(schoolService.getLessionById(lessionId).timeslots.size() > 1) {
			for(Timeslot timetable: schoolService.getAllLessions().get(lessionId).timeslots) {
				timetableSlots.set(timetable.getId(), timetable.getTimeslot());
		}
}
		model.addAttribute("singleSlot", true);
		model.addAttribute("view", true);
		model.addAttribute("lessionId", lessionId);
		model.addAttribute("timetableSlots", timetableSlots);
		model.addAttribute("title", schoolService.getAllLessions().get(lessionId).getName());
		System.out.println(timetableSlots);
		return "timetable";
	}
	
	// enables editing of timetables
	@GetMapping("timetable/lession/{lessionId}/edit")
	public String editLessionTimetable(Model model, @PathVariable int lessionId){
		
		List<Integer> timetableSlots = new ArrayList<>(0);
		for(int i=0; i<=44; i++) {
			timetableSlots.add(99);
		}
		// iterate trough all of the list
		if(schoolService.getAllLessions().get(lessionId).timeslots.size() > 1) {
			for(Timeslot timetable: schoolService.getAllLessions().get(lessionId).timeslots) {
				timetableSlots.set(timetable.getId(), timetable.getTimeslot());
				}
		}
		model.addAttribute("singleSlot", true);
		model.addAttribute("edit", true);
		model.addAttribute("timetableSlots", timetableSlots);
		model.addAttribute("title", schoolService.getAllLessions().get(lessionId).getName());
		System.out.println(timetableSlots);
		return "timetable";
	}
	
	// Add a lession to timetable
	@GetMapping("timetable/lession/{lessionId}/add_{slotId}")
	public String addLessionTimetableSlot(Model model, @PathVariable int lessionId, @PathVariable int slotId) {
		
		Lession lession = schoolService.getLessionById(lessionId);
		schoolService.getTimetableById(slotId).lessions.add(lession);
		
		System.out.println(schoolService.getTimetableById(slotId).lessions);

		return "redirect:/timetable/lession/" + lessionId + "/edit";
	}
	
	// delete a slot from timetable
	@GetMapping("timetable/lession/{lessionId}/remove_{slotId}")
	public String removeLessionTimetableSlot(Model model, @PathVariable int lessionId, @PathVariable int slotId) {
		
		Lession lession = schoolService.getLessionById(lessionId);
		schoolService.getTimetableById(slotId).lessions.remove(lession);
		
		System.out.println(schoolService.getTimetableById(slotId).lessions);

		return "redirect:/timetable/lession/" + lessionId + "/edit";
	}
	
	// get all student timetables
	@GetMapping("timetable/student/{studentId}")
	public String getAllStudentTimetable(Model model, @PathVariable int studentId) {
		Student student = schoolService.getStudentById(studentId);
		List<Lession> lessions = student.lessions;

		// get a timetable slot for each lession
		// new hashmap
		Map<Integer, List<Lession>> timetableLessions = new HashMap<Integer, List<Lession>>();
		for(int i=0; i<=44; i++) {
			List<Lession> lessionList = new ArrayList<>();
			
			// best way?
			// search every lession that student signed up for and look trough the timetables and add that lession to timetableLessions
			for(Lession lession: lessions) {
				if(lession.timeslots.size() >= 1) {
					for(Timeslot timeslot: lession.timeslots) {
						if(timeslot.getTimeslot() == i) {
							// matches the slot
							lessionList.add(lession);
						}
					}
					
					
				}
			}
			
			timetableLessions.put(i, lessionList);
		}
		
		String studentName = student.getName() + " " + student.getSurname();
		model.addAttribute("title", studentName);
		model.addAttribute("timetableSlots", timetableLessions);
		model.addAttribute("multipleSlots", true);
		return "timetable";
	}
	
}
