<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Ajouter un individu</title>
	<style>
		.container
		{
			width: 1000px;
			margin: 0 auto;
			text-align: center;
		}
		form
		{
			width: 75%;
			margin: 0 auto;
		}
	</style>
</head>
<body>
	<div class="container">
		<header>
			<h3>Ajouter un individu</h3>
		</header>
		<form action='<c:url value="/add-user"></c:url>' method="POST" accept-charset="UTF-8">
			<div>
				<label for="firstname">Votre prénom : </label>
				<input type="text" name="firstname" required/>
			</div>
			<div>
				<label for="lastname">Votre nom : </label>
				<input type="text" name="lastname" required/>
			</div>
			<div>
				<label for="age">Votre âge : </label>
				<input type="number" name="age" required/>
			</div>
			<div>
				<input type="submit" value="Envoyer"/>
				<input type="reset" value="Réinitialiser"/>
				<a href='<c:url value="/"></c:url>'>Retour liste</a>
			</div>
		</form>
	</div>
</body>
</html>