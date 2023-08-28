<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formateo fechas (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- Formulario form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- para errores de renderizado en rutas PUT -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>BOOKS</title>
<!-- BOOTSTRAP  -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- cambiar para que coincida con Tu archivo/estructura de nombres -->
<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
<h1>Editar Libro</h1>
	<form:form action="/books/${libro.id}" method="post" modelAttribute="libro">
		<input type="hidden" name="_method" value="put" >
		<div>
			<form:label path="title">Title</form:label>
			<form:errors class="text-danger" path="title" />
			<form:input path="title" />
		</div>
		<div>
			<form:label path="description">Description</form:label>
			<form:errors class="text-danger" path="description" />
			<form:textarea path="description" />
		</div>
		<div>
			<form:label path="language">Language</form:label>
			<form:errors class="text-danger" path="language" />
			<form:input path="language" />
		</div>
		<div>
			<form:label path="numberOfPages">Pages</form:label>
			<form:errors class="text-danger" path="numberOfPages" />
			<form:input type="number" path="numberOfPages" />
		</div>
		<input type="submit" value="Submit" class="btn btn-primary m-3" />
	</form:form>

</body>
</html>