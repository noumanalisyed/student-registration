package com.bnu.we.controller;

import com.bnu.we.dao.CourseDAO;
import com.bnu.we.dao.DBConnection;
import com.bnu.we.entity.Course;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;


@WebServlet("/CourseControllerServlet")
public class CourseControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CourseDAO courseDAO;
	Course courseObj;

	public CourseControllerServlet(){
	    try {
            init();
        }catch (ServletException r){
	        r.getMessage();
        }
    }
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			courseDAO = new CourseDAO();
			courseObj  = new Course();

		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listCourses(request, response);
				break;
				
			case "ADD":
				addCourse(request, response);
				break;
				
			case "LOAD":
				loadCourse(request, response);
				break;
				
			case "UPDATE":
				updateCourse(request, response);
				break;
			
			case "DELETE":
				deleteCourse(request, response);
				break;
				
			default:
				listCourses(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	private void deleteCourse(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read student id from form data
		String theCourseId = request.getParameter("courseId");
		
		// delete student from database
		courseDAO.deleteCourse(theCourseId);
		
		// send them back to "list students" page
		listCourses(request, response);
	}

	private void updateCourse(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read student info from form data
		int id = Integer.parseInt(request.getParameter("courseId"));
		String courseName = request.getParameter("courseName");
		String courseCode = request.getParameter("courseCode");
		String description = request.getParameter("description");
		
		// create a new student object
		Course theCourse = new Course(id, courseName, courseCode, description);
		
		// perform update on database
		courseDAO.updateCourse(theCourse);
		
		// send them back to the "list students" page
		listCourses(request, response);
		
	}

	private void loadCourse(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read student id from form data
		String theCourseId = request.getParameter("courseId");
		
		// get student from database (db util)
		System.out.println("Course Id  : "+theCourseId);
		Course theCourse = courseDAO.getCourse(theCourseId);
		
		// place student in the request attribute
		request.setAttribute("THE_COURSE", theCourse);
		
		// send to jsp page: update-course-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/update-course-form.jsp");
		dispatcher.forward(request, response);		
	}

	private void addCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student info from form data
		String courseName = request.getParameter("courseName");
		String courseCode = request.getParameter("courseCode");
		String description = request.getParameter("description");
		
		// create a new student object
		Course theCourse = new Course(courseName,courseCode, description);
		
		// add the student to the database
		courseDAO.insert(theCourse);
				
		// send back to main page (the student list)
		listCourses(request, response);
	}

	private void listCourses(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// get students from db util
		List<Course> courses = courseDAO.getAllCourse();
		
		// add students to the request
		request.setAttribute("COURSE_LIST", courses);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-course.jsp");
		dispatcher.forward(request, response);
	}

}













