<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Liste des individus</title>
	<style>
		.container
		{
			width: 1000px;
			margin: 0 auto;
			text-align: center;
		}
		
		table
		{
			border-collapse: collapse;
			width: 75%;
			margin: 0 auto;
		}
		th, td
		{
			border : 1px solid black;
			padding : 5px;
		}
	</style>
</head>
<body>
	<div class="container">
		<table>
			<tr>			
				<th>Prénom</th>
				<th>Nom</th>
				<th>Age</th>
				<th>Date de création</th>
				<th>Actions</th>
			</tr>
			<c:if test="${empty users}">
				<tr>
					<td colspan="5">Aucun utilisateur trouvé....</td>
				</tr>
			</c:if>
			<c:forEach var="u" items="${users}">
				<tr>						
					<td><c:out value="${u.firstname}"></c:out></td>
					<td><c:out value="${u.lastname}"></c:out></td>
					<td><c:out value="${u.age}"></c:out></td>
					<td><fmt:formatDate value="${u.dateCreation}" pattern="dd / MMM / yyyy"/></td>
					<td>
						<form action="<c:url value="/delete-user"></c:url>" method="POST">
							<input type="hidden" value="${u.id}" name="userid"/>
							<input type="submit" value="Supprimer"/>
						</form>
						</td>
				</tr>
			</c:forEach>			
			<tr>
				<td colspan="5"><a href='<c:url value="/add-user"></c:url>'>Ajouter un individu</a></td>
			</tr>
			<tr>
				<td colspan="5">
						<form action="<c:url value="/delete-user"></c:url>" method="POST">
							<input type="hidden" value="-1" name="userid"/>
							<input type="submit" value="Tout supprimer"/>
						</form>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>