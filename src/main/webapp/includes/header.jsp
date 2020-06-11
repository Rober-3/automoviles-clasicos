<%@page import="robert.bermudez.rodriguez.modelo.Clasico"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">

<head>
<meta charset="UTF-8">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
<!-- Datatables CSS -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
<!-- CSS personalizado -->
<link rel="stylesheet" href="css/styles.css">

<title>${param.title}</title>

</head>

<body>

	<header>

		<!-- Barra de navegación -->

		<nav class="barra navbar navbar-expand-lg navbar-light bg-light py-5">
		
			<a class="navbar-brand" href="#">Navbar</a>
			<button class="navbar-toggler" 
					type="button" 
					data-toggle="collapse"
					data-target="#navbarTogglerDemo02"
					aria-controls="navbarTogglerDemo02" aria-expanded="false"
					aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			

			<div class="collapse navbar-collapse" id="navbarTogglerDemo02">
			
				<!-- Enlaces -->
				
				<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
					<li class="nav-item active font-weight-bold">
						<a class="nav-link" href="index.jsp">Inicio <span class="sr-only">(current)</span> </a>
					</li>
					<li class="nav-item font-weight-bold">
						<a class="nav-link ${ ('Clasicos americanos' eq param.pagina ?'disabled' :'') }" href="clasicos" tabindex="-1" aria-disabled="true">Clásicos americanos</a> 
					</li>
					<li class="nav-item font-weight-bold">
						<a class="nav-link ${ ('Formulario' eq param.pagina ?'disabled' :'') }" href="insertar-editar">Nuevo registro</a>
					</li>
				</ul>
				
				<form class="form-inline my-2 my-lg-0">
					<input class="form-control mr-sm-2" type="search" placeholder="Search">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
				</form>
				
			</div>
			
		</nav>
		
	</header>
	
	<jsp:include page="alerta.jsp"></jsp:include>