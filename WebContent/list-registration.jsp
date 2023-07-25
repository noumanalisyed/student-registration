<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Course Registration App</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
			<c:set var="context" value="${pageContext.request.contextPath}" />
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add Student -->
			
			<input type="button" value="Add Registration"
				   onclick="window.location.href='add-registration-form.jsp'; return false;"
				   class="add-student-button"/>
			<table>
			
				<tr>
					<th>Registration Date</th>
					<th>Course </th>
					<th>Student</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempRegistration" items="${REGISTRATION_LIST}">
					
					<!-- set up a link for each student -->
					<c:url var="tempLink" value="RegistrationControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="registrationId" value="${tempRegistration.registrationId}" />
					</c:url>

					<!--  set up a link to delete a student -->
					<c:url var="deleteLink" value="RegistrationControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="registrationId" value="${tempRegistration.registrationId}" />
					</c:url>
																		
					<tr>
						<td> ${tempRegistration.regDate} </td>
						<td> ${tempRegistration.course.courseName} </td>
						<td> ${tempRegistration.student.firstName} </td>
						<td> 
							<a href="${tempLink}">Update</a> 
							 | 
							<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this registration?'))) return false">
							Delete</a>	
						</td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>


</html>








