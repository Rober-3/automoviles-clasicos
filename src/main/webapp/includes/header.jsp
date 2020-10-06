<%@page import="robert.bermudez.rodriguez.modelo.pojo.Clasico"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">

<!-- Si en una de las páginas hay un enlace css de más, incluirlo en la cabecera aunque las demás páginas
	 no lo usen. Es mucho más práctico hacerlo así que hacer una cabecera específica para cada página.-->

<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- Todas las rutas relativas que se carguen en el front comenzarán por el href indicado, es decir, 
    el nombre del proyecto. Haciendo este cambio no será necesario el ../../ para salta carpetas. Todas
    las rutas se compondrán de la raíz del proyecto hacia delante.-->
    <!-- <base href="http://localhost:8080/automoviles-clasicos/" /> -->
    
    <!-- Si se cambia la ruta del proyecto lo anterior no funciona, así que para hacerlo dinámico habrá que
    usar esta línea. Así, todas las URL del proyecto comenzarán con contextPath. El valor de éste será el 
    contexto o nombre de la aplicación, que en este caso será automoviles-clasicos. -->
    <base href="${pageContext.request.contextPath}/" />

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

	<!-- Font Awesome -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">

	<!-- Datatables CSS -->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">

	<!-- CSS personalizado -->
	<!-- <link rel="stylesheet" href="css/styles.css"> -->
	<!-- System.currentTimeMillis()% obliga a cargar siempre el CSS. -->
	<link rel="stylesheet" href="css/styles.css?d=<%=System.currentTimeMillis()%>">

	<!-- <title>title%></title> Para presentar el título de esta manera hay que utilizar el include sin parámetros.  -->
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
							<a class="nav-link text-white" href="doc/index.html">API JavaDoc</a>
						</li>
					
					<!-- Esta parte sólo se muestra si un usuario ha iniciado sesión. -->
					<c:if test="${not empty sessionScope.usuario}">
					
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
					</c:if>
				</ul>

				<!-- Opciones cuando un usuario ha iniciado o cerrado sesión. -->
				<span class="form-inline my-2 my-lg-0 font-weight-bold">
				
					<!-- LoginController -->
					<c:if test="${empty sessionScope.usuario}">
						<a class="nav-link text-white" href="views/login.jsp">Iniciar sesión</a>
					</c:if>
					<c:if test="${not empty sessionScope.usuario}">
						<span>Has iniciado sesión como ${sessionScope.usuario.nombre}</span>
						
						<c:if test="${sessionScope.usuario.rol.rol == 'usuario'}">
							<a class="nav-link text-white" href="views/frontoffice/inicio">Mi panel</a>
						</c:if>
						<c:if test="${sessionScope.usuario.rol.rol == 'administrador'}">
							<a class="nav-link text-white" href="views/backoffice/inicio">Mi panel</a>
						</c:if>
						
						<a class="nav-link text-white" href="logout">Cerrar sesión</a>
					</c:if>
				</span>
			</div>
		</nav>
	</header>

	<jsp:include page="alerta.jsp"></jsp:include>
	