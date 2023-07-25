package com.bnu.we.entity;

import java.util.ArrayList;
import java.util.List;

public class Course {
	
	public Course() {
		super();
		registrationList = new ArrayList<Registration>();
	}
	public Course(int courseId, String courseName, String courseCode, String description) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseCode = courseCode;
		this.description = description;
		registrationList = new ArrayList<Registration>();
	}

	public Course(String courseName, String courseCode, String description) {
		this.courseName = courseName;
		this.courseCode = courseCode;
		this.description = description;
	}

	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void addRegistration(Registration r){
		registrationList.add(r);
	}
	public void removeRegistration(Registration r){
		registrationList.remove(r);
	}

	@Override
	public String toString() {
		return "Course{" +
				"courseId=" + courseId +
				", courseName='" + courseName + '\'' +
				", courseCode='" + courseCode + '\'' +
				", description='" + description + '\'' +
				", registrationList=" + registrationList +
				'}';
	}

	private int courseId;
	private String courseName;
	private String courseCode;
	private String description;
	private List<Registration> registrationList; 


}
