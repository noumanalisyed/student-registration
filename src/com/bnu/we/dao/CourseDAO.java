package com.bnu.we.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

import com.bnu.we.entity.Course;

public class CourseDAO {
	Connection theCon;
	PreparedStatement preparedStatement;
	public CourseDAO() {
		super();
		theCon = DBConnection.makeConnection();
		// TODO Auto-generated constructor stub
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
	@SuppressWarnings("unchecked")
	public ResultSet getAllCourseFromDB(){
		ResultSet rs = null;
		String strQuery = "Select * from course";
		try {
			preparedStatement = theCon.prepareStatement(strQuery);
			rs = preparedStatement.executeQuery(strQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;		
	}
	
	public List<Course> getAllCourse() throws SQLException{
		ResultSet rs = getAllCourseFromDB();
		List<Course> listCourse = new ArrayList<Course>();
		while(rs.next()){
			int id = rs.getInt("courseId");
			String cname = rs.getString("courseName");
			String desc = rs.getString("description");
			String courseCode = rs.getString("courseCode");
			Course c = new Course(id, cname, desc, courseCode);
			listCourse.add(c);			
		}
		return listCourse;
	}
	
	@SuppressWarnings("unchecked")
	public Course getCourse(String courseId){
			ResultSet rs = null;
			Course course = null;
			String strQuery = "SELECT * FROM course where courseId=?";
			try {
				preparedStatement = theCon.prepareStatement(strQuery);
				int id = Integer.parseInt(courseId);
				preparedStatement.setInt(1,id);
				rs = preparedStatement.executeQuery();


				System.out.println("RS : "+rs);
				if(rs.next()){
					int idNew = rs.getInt("courseId");
					String cname = rs.getString("courseName");
					String desc = rs.getString("description");
					String courseCode = rs.getString("courseCode");
					course = new Course(idNew, cname, desc, courseCode);
					System.out.println("Course : "+course);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Course : "+course);
			return course;		
	 }

	public void updateCourse(Course theCourse) throws Exception {

		//Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection

			// create SQL update statement
			String sql = "update course "
					+ "set courseName=?, courseCode=?, description=? "
					+ "where courseId=?";

			// prepare statement
			myStmt = theCon.prepareStatement(sql);

			// set params
			myStmt.setString(1, theCourse.getCourseName());
			myStmt.setString(2, theCourse.getCourseCode());
			myStmt.setString(3, theCourse.getDescription());
			myStmt.setInt(4, theCourse.getCourseId());

			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			//close(myConn, myStmt, null);
		}
	}

	public void deleteCourse(String theCourseId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// convert student id to int
			int courseId = Integer.parseInt(theCourseId);

			// get connection to database
			//myConn = dataSource.getConnection();

			// create sql to delete student
			String sql = "delete from course where courseId=?";

			// prepare statement
			myStmt = theCon.prepareStatement(sql);

			// set params
			myStmt.setInt(1, courseId);

			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			//close(myConn, myStmt, null);
		}
	}

}
