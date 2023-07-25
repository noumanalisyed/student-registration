package com.bnu.we.entity;

import java.sql.Date;

public class Registration {
	private int registrationId;
	private String regDate;
	private Course course;
	private Student student;

	public Registration() {
	}

	public Registration(String regDate, Course course, Student student) {
		this.regDate = regDate;
		this.course = course;
		this.student = student;
	}

	public Registration(int id, String registrationDate, Course course, Student student) {
		registrationId = id;
		this.course  =course;
		regDate = registrationDate;
		this.student = student;
	}


	public int getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Course getCourse() {
		return course;
	}
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student s){
		if(student != null){
			student.removeRegistration(this);
			student = s;
			student.addRegistration(this);
		}
	}
	public void setCourse(Course c){
		if(course != null){
			course.removeRegistration(this);
			course = c;
			course.addRegistration(this);
		}
	}

	@Override
	public String toString() {
		return "Registration{" +
				"registrationId=" + registrationId +
				", regDate=" + regDate +
				", course=" + course +
				", student=" + student +
				'}';
	}
}
