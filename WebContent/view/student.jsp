<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List,com.bnu.we.entity.Student,com.bnu.we.dao.StudentDAO, java.sql.SQLException" %>
<jsp:useBean id="studentObj" class="com.bnu.we.entity.Student" scope="request" />
<jsp:setProperty property="*" name="studentObj" />
<jsp:useBean id="studentDAO" class="com.bnu.we.dao.StudentDAO" scope="request" />
<jsp:setProperty property="*" name="studentDAO" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<BODY>
<H1><center>Student Registration</center></H1>
<FORM ACTION="http://localhost:9090/StudentRegistration_Web_exploded/StudentController" METHOD="POST">
	<Table>
		<TR>
			<TD><LABEL>First NAME:</LABEL></TD>
			<TD><INPUT TYPE="TEXT" NAME="fName" value="${studentObj.firstName}"/></TD>
		</TR>
		<TR>
			<TD><LABEL>Last Name:</LABEL></TD>
			<TD><INPUT TYPE="TEXT" NAME="lName" value="${studentObj.lastName}"/></TD>
		</TR>
		<TR>
			<TD><LABEL>Address:</LABEL></TD>
			<TD><INPUT TYPE="TEXT" NAME="address" value="${studentObj.address}"/></TD>
		</TR>
		<TR>
			<TD><INPUT TYPE="SUBMIT" VALUE="OK" /></TD>
			<TD><INPUT TYPE="RESET" VALUE="Cancel" /></TD>
		</TR>
	</Table>
</FORM>
		
<FORM ACTION="http://localhost:9090/StudentRegistration_Web_exploded/StudentController" METHOD="GET">
	</br></br></br><H1><center>Registered Student Details</center></H1>
	<TABLE border="1">
		<TR>
			<TD><B>First Name</B></TD>
			<TD><B>Last Name</B></TD>
			<TD><B>Address</B></TD>
			<TD><B>Edit</B></TD>
		</TR>
 	<% 	
		int i=0;
		List<Student> listStudent = null;
		try {
			listStudent = studentDAO.getAllStudents();		
			while(i < listStudent.size()){
				Student c = listStudent.get(i);
	%>		
				<TR>
					<TD><%=c.getFirstName()%></TD>
					<TD><%=c.getLastName()%></TD>
					<TD><%=c.getAddress()%></TD>
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
