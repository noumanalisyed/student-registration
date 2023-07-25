package com.bnu.we.test;

import com.bnu.we.dao.StudentDAO;
import com.bnu.we.entity.Student;

public class TestClass {
	StudentDAO studentDAO;
	Student s;
	

	public TestClass() {
		super();
		studentDAO = new StudentDAO();
		s = new Student();
		studentDAO.insert(s);
	}



	public static void main(String[] args) {
		TestClass t = new TestClass();

	}

}
