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

		<nav class="barra text-light navbar navbar-expand-lg navbar-light bg-light py-4">

			<a class="navbar-brand" href="#">Navbar</a>
			<button class="navbar-toggler" 
					type="button"
					data-toggle="collapse"
					data-target="#navbarTogglerDemo02"
					aria-controls="navbarTogglerDemo02" 
					aria-expanded="false"
					aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>


			<div class="collapse navbar-collapse text-white" id="navbarTogglerDemo02">

				<!-- Enlaces -->

				<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				
					<li class="nav-item active font-weight-bold">
						<a class="nav-link text-white" href="inicio">Inicio <span class="sr-only">(current)</span> </a>
					</li>
					
					<li class="nav-item font-weight-bold">
						<a class="nav-link text-white ${ ('Clasicos americanos' eq param.pagina ?'disabled' :'') }" href="clasicos" tabindex="-1" aria-disabled="true">Clásicos americanos</a>
					</li>
					
					<li class="nav-item font-weight-bold">
						<a class="nav-link text-white ${ ('Formulario clasicos' eq param.pagina ?'disabled' :'') }" href="insertar-editar">Nuevo clásico</a>
					</li>
					
					<li class="nav-item font-weight-bold">
						<a class="nav-link text-white ${ ('Formulario marcas' eq param.pagina ?'disabled' :'') }" href="marcas">Nueva marca</a>
					</li>
					
					<li class="nav-item">
						<div class="dropdown">
							<button class="btn dropdown-toggle bg-transparent font-weight-bold text-white" type="button" id="dropdownMenuButton" 
									data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Marcas</button>
							<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							
								<a class="dropdown-item font-weight-bold" href="marcas">Todas</a>
								
								<a class="dropdown-item font-weight-bold" href="inicio">Todas A-Z</a>
							
								<c:forEach items="${marcas}" var="m">
									<!-- idMarca se envía a InicioController para seleccionar una marca. -->
									<!-- marca se envía como atributo para mostrar en pantalla la marca de un grupo de clásicos. -->
									<a class="dropdown-item" href="inicio?idMarca=${m.id}&marca=${m.marca}">${m.marca}</a>
								</c:forEach>
								
							</div>
						</div>
					</li>
				</ul>

				<!-- Opciones en el inicio de sesión -->

				<span class="form-inline my-2 my-lg-0 font-weight-bold">
				
					<c:if test="${empty usuario}">
						<a class="nav-link text-white" href="login">Iniciar sesión</a>
					</c:if> 
					
					<c:if test="${not empty usuario}">
						<span>${usuario.nombre}</span>
						<a class="nav-link text-white" href="#">Cerrar sesión</a>
					</c:if>

				</span>
			</div>
		</nav>
	</header>

	<jsp:include page="alerta.jsp"></jsp:include>