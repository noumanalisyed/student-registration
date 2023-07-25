package com.bnu.we.entity;

import java.util.ArrayList;
import java.util.List;

public class Student {
	
	public Student() {
		super();
		registrationList = new ArrayList<Registration>();
	}
	public Student(int studentId, String firstName, String lastName, String address) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		registrationList = new ArrayList<Registration>();
	}

	public Student(String firstName, String lastName, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}

	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void addRegistration(Registration r){
		registrationList.add(r);
	}
	public void removeRegistration(Registration r){
		registrationList.remove(r);
	}

	@Override
	public String toString() {
		return "Student{" +
				"studentId=" + studentId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", address='" + address + '\'' +
				", registrationList=" + registrationList +
				'}';
	}

	private int studentId;
	private String firstName;
	private String lastName;
	private String address;
	private List<Registration> registrationList; 
}
