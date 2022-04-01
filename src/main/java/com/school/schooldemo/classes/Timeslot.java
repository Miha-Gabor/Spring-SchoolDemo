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
@Table(name="timeslot")
public class Timeslot {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="timeslot_id")
	private int id;
	
	@Column(name="time")
	private int timeslot;
	
	// Get all lessions for when this timeslot is avalible
	@ManyToMany(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="class_has_timeslot",
			   joinColumns=@JoinColumn(name="timeslot_timeslot_id"),
			   inverseJoinColumns=@JoinColumn(name="class_class_id"))
	public List<Lession> lessions;
	public void addLession(Lession lession) {
		if(lessions == null) {
			lessions = new ArrayList<>();
		}
		lessions.add(lession);
	}
	
	// const
	public Timeslot() {}

	public Timeslot(int timeslot) {
		this.timeslot = timeslot;
	}
	
	public Timeslot(int id, int timeslot) {
		this.id= id;
		this.timeslot = timeslot;
	}

	// GET / SET
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(int timeslot) {
		this.timeslot = timeslot;
	};
	
	
}
