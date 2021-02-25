<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>

<head>

	<title>Customers</title>
	
	<!-- reference our style sheet -->
	
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

</head>




<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id="continer">
		
		<div id="content">
		
			<!-- put new button: Add Customer -->
			<br>
			<input 
				type="button" 
				value="Add Customer" 
				onClick="window.location.href='showFormForAdd'; return false;"
				class="add-button"
			/>
			
			   <!--  add a search box -->
            <form:form action="search" method="GET">
                Search customer: <input type="text" name="theSearchName" />
                
                <input type="submit" value="Search" class="add-button" />
            </form:form>

		
			<!-- add out html table here -->
			
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action/Delete</th>
				</tr>
				
				<!-- loop over and print our customers -->
				<c:forEach var="tempCustomer" items="${customers}">
				
					<!-- construct an "update" link with customer id -->
					<c:url var="updateLink" value="/customer/showFormUpdate">
						<c:param name="customerId" value="${tempCustomer.id}"></c:param>
					</c:url>
					
					<!-- delete a customer -->
					<c:url var="deleteLink" value="/customer/deleteCustomer">
						<c:param name="customerId" value="${tempCustomer.id}"></c:param>
					</c:url>
				
					<tr>
						<td>${tempCustomer.firstName}</td> <!-- call tempCustomer.getFirstName()  -->
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<td>
							<!-- display the update link -->
							<a href="${updateLink}">Update </a>
							|
							<a href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to delete this slave?'))) return false"> Delete</a>
						</td>
					</tr>
				
				</c:forEach>

			</table>
		
		</div>
	
	</div>


</body>



</html>


