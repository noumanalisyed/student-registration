package com.bnu.we.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bnu.we.entity.Course;
import com.bnu.we.entity.Registration;
import com.bnu.we.entity.Student;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDAO {
	Connection theCon;
	PreparedStatement preparedStatement;
	RegistrationDAO registrationDAO;
	public StudentDAO() {
		super();
		theCon = DBConnection.makeConnection();

		// TODO Auto-generated constructor stub
	}
	
	public void insert(Student s){
		String strInsert = "insert into student (firstName,lastName,address) values (?,?,?)";
		try {
			preparedStatement =  
					theCon.prepareStatement(strInsert,
							PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1,s.getFirstName());
			preparedStatement.setString(2,s.getLastName());
			preparedStatement.setString(3,s.getAddress());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	@SuppressWarnings("unchecked")
	private ResultSet getAllStudentsFromDB(){
		ResultSet rs = null;
		String strQuery = "Select * from student";
		List<Student> listStudent = null;
		try {
			preparedStatement = theCon.prepareStatement(strQuery);
			rs = preparedStatement.executeQuery(strQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;		
	}
	
	public List<Student> getAllStudents() throws SQLException{
		registrationDAO = new RegistrationDAO();
		ResultSet rs = getAllStudentsFromDB();
		List<Student> listStudent = new ArrayList<Student>();
		while(rs.next()){
			int id = rs.getInt("studentId");
			String fname = rs.getString("firstName");
			String lname = rs.getString("lastName");
			String address = rs.getString("address");
			Student s = new Student(id, fname, lname, address);
			List<Registration> registrations = registrationDAO.getRegistration(id);
			int i = 0;
			while (i < registrations.size()){
				s.addRegistration(registrations.get(i));
				i++;
			}
			listStudent.add(s);			
		}
		return listStudent;
	}


	public void insert(Course s){
		String strInsert = "insert into course (courseName,description,courseCode) values (?,?,?)";
		try {
			preparedStatement =
					theCon.prepareStatement(strInsert,
							PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1,s.getCourseName());
			preparedStatement.setString(2,s.getDescription());
			preparedStatement.setString(3,s.getCourseCode());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Student getStudent(String studentId){
		ResultSet rs = null;
		Student student = null;
		String strQuery = "SELECT * FROM student where studentId=?";
		try {
			preparedStatement = theCon.prepareStatement(strQuery);
			int id = Integer.parseInt(studentId);
			preparedStatement.setInt(1,id);
			rs = preparedStatement.executeQuery();


			System.out.println("RS : "+rs);
			if(rs.next()){
				int idNew = rs.getInt("studentId");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String address = rs.getString("address");
				student = new Student(idNew, firstName, lastName, address);
				System.out.println("Student : "+student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Student : "+student);
		return student;
	}

	public void updateStudent(Student theStudent) throws Exception {

		//Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection

			// create SQL update statement
			String sql = "update student "
					+ "set firstName=?, lastName=?, address=? "
					+ "where studentId=?";

			// prepare statement
			myStmt = theCon.prepareStatement(sql);

			// set params
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getAddress());
			myStmt.setInt(4, theStudent.getStudentId());

			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			//close(myConn, myStmt, null);
		}
	}

	public void deleteStudent(String theStudentId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// convert student id to int
			int studentId = Integer.parseInt(theStudentId);

			// get connection to database
			//myConn = dataSource.getConnection();

			// create sql to delete student
			String sql = "delete from student where studentId=?";

			// prepare statement
			myStmt = theCon.prepareStatement(sql);

			// set params
			myStmt.setInt(1, studentId);

			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			//close(myConn, myStmt, null);
		}
	}

}
