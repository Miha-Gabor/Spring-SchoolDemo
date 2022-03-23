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
@Table(name="teachers")
public class Teacher {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="teacher_id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;
	
	// constructors
	public Teacher() {}
	public Teacher(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}
	
	@ManyToMany(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="teachers_has_class",
			   joinColumns=@JoinColumn(name="teachers_teacher_id"),
			   inverseJoinColumns=@JoinColumn(name="class_class_id"))
	public List<Lession> lessions;
	
	public void addLession(Lession lession) {
		if(lessions == null) {
			lessions = new ArrayList<>();
		}
		lessions.add(lession);
	}

	// setters and getters
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	};
	
	@Override
	public String toString() {
		return "[" + id + "] " + name + " " + surname;
	}
	
	
}
