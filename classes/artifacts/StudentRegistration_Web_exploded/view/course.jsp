<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List,com.bnu.we.entity.Course,com.bnu.we.dao.CourseDAO, java.sql.SQLException" %>
<jsp:useBean id="courseObj" class="com.bnu.we.entity.Course" scope="request" />
<jsp:setProperty property="*" name="courseObj" />
<jsp:useBean id="courseDAO" class="com.bnu.we.dao.CourseDAO" scope="request" />
<jsp:setProperty property="*" name="courseDAO" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<BODY>
<H1><center>Course Registration</center></H1>
<FORM ACTION="http://localhost:9090/StudentRegistration_Web_exploded/CourseController" METHOD="POST">
	<Table>
		<TR>
			<TD><LABEL>Course NAME:</LABEL></TD>
			<TD><INPUT TYPE="TEXT" NAME="cName" value="${courseObj.courseName}"/></TD>
		</TR>
		<TR>
			<TD><LABEL>Description:</LABEL></TD>
			<TD><INPUT TYPE="TEXT" NAME="Description" value="${courseObj.description}"/></TD>
		</TR>
		<TR>
			<TD><LABEL>Course Code:</LABEL></TD>
			<TD><INPUT TYPE="TEXT" NAME="cCode" value="${courseObj.courseCode}"/></TD>
		</TR>
		<TR>
			<TD><INPUT TYPE="SUBMIT" VALUE="OK" /></TD>
			<TD><INPUT TYPE="RESET" VALUE="Cancel" /></TD>
		</TR>
	</Table>
</FORM>
		
<FORM ACTION="http://localhost:9090/StudentRegistration_Web_exploded/CourseController" METHOD="GET">
	</br></br></br><H1><center>Registered Courses Details</center></H1>
	<TABLE border="1">
		<TR>
			<TD><B>Course Name</B></TD>
			<TD><B>Description</B></TD>
			<TD><B>Course Code</B></TD>
			<TD><B>Edit</B></TD>
		</TR>
 	<% 	
		int i=0;
		List<Course> listCourse = null;
		try {
			listCourse = courseDAO.getAllCourse();		
			while(i < listCourse.size()){
				Course c = listCourse.get(i);
	%>		
				<TR>
					<TD><%=c.getCourseName()%></TD>
					<TD><%=c.getDescription()%></TD>
					<TD><%=c.getCourseCode()%></TD>
					<TD><input type="Button" value="Edit" /></TD>
				</TR>
	<%i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	%>
</TABLE>
</FORM>
</BODY>			 	
</html>
