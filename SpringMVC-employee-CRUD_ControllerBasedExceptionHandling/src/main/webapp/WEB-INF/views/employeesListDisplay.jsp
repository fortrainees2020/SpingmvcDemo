<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
	<title>Spring MVC</title>
</head>

<body>
	<h2>All Employees in System</h2>

	<table border="1">
		<tr>
			<th>Employee Id</th>
			<th>FirstName</th>
			<th>Salary</th>
			<th>Designation</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${employees}" var="employee">
			<tr>
				<td>${employee.id}</td>
				<td>${employee.name}</td> <!-- Case Sensitive -->
				<td>${employee.salary}</td>
				<td>${employee.designation}</td>
				<td>
					  <a href="editEmployee?id=${employee.id}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                      <a href="deleteEmployee?id=${employee.id}">Delete</a>                       
                </td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>

