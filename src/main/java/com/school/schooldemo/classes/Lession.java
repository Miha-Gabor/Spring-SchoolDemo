package com.school.schooldemo.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="class")
public class Lession {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="class_id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	
	// Get all students
	@ManyToMany(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="class_has_students",
			   joinColumns=@JoinColumn(name="class_class_id"),
			   inverseJoinColumns=@JoinColumn(name="students_student_id"))
	public List<Student> students;
	
	public void addStudent(Student student) {
		if(students == null) {
			students = new ArrayList<>();
		}
		students.add(student);
	}
	
	// Get all timeslots for when this class is avalible
	@ManyToMany(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="class_has_timeslot",
			   joinColumns=@JoinColumn(name="class_class_id"),
			   inverseJoinColumns=@JoinColumn(name="timeslot_timeslot_id"))
	public List<Timeslot> timeslots;
	public void addTimeslot(Timeslot timeslot) {
		if(timeslots == null) {
			timeslots = new ArrayList<>();
		}
		timeslots.add(timeslot);
	}
	
	// Get all teachers for when this class is avalible
	@ManyToMany(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="teachers_has_class",
			   joinColumns=@JoinColumn(name="class_class_id"),
			   inverseJoinColumns=@JoinColumn(name="teachers_teacher_id"))
	public List<Teacher> teachers;
	public void addTeachers(Teacher teacher) {
		if(teachers == null) {
			teachers = new ArrayList<>();
		}
		teachers.add(teacher);
	}
	
	// Constructors
	public Lession() {
	}
	public Lession(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	// Setters and getters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "[" + id + "] " + name + " - " + description + "";
	}
	
	
	
}
