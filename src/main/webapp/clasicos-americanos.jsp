<%@page import="robert.bermudez.rodriguez.modelo.Clasico"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<!-- Datatables CSS -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
<!-- CSS personalizado -->
<link rel="stylesheet" href="css/styles.css">

<title>Clásicos americanos</title>

</head>
<body>
	<div class="container">

		<h3 class="text-center">Clásicos americanos</h3>

		<a href="index.jsp">Página principal</a>

		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Modelo</th>
					<th scope="col">Marca</th>
					<th scope="col">Año</th>
					<th scope="col">Foto</th>
				</tr>
			</thead>
			<tbody>

				<!-- Recoger la información o atributo enviado desde el controlador. -->
				<c:forEach items="${clasicosAmericanos}" var="a">

					<tr>

						<td>${a.id}</td>
						<td>${a.modelo}</td>
						<td>${a.marca}</td>
						<td>${a.anio}</td>
						<td><img src="${a.foto}" class="img-thumbnail" alt="foto"></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<!-- Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    
    <!-- DataTables JS -->
    <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
	
</body>
</html>