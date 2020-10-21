<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<body class="sb-nav-fixed">
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<a class="navbar-brand" href="index.html">Mi panel</a>
		<!-- Navbar-->
		<ul class="navbar-nav ml-auto ml-md-0">
			<li class="nav-item">
				<a class="dropdown-item text-white" href="logout">Cerrar sesión</a> <!-- LogoutController -->
			</li>
		</ul>
	</nav>
	<div class="mt-5">
		<jsp:include page="alerta.jsp"></jsp:include>
	</div>
	<div id="layoutSidenav"> <!-- Termina en office-footer.jsp. -->
		<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-dark"
				id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">
						<a class="nav-link" href="inicio"> <!-- InicioController -->
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Inicio del sitio
						</a>
						<a class="nav-link" href="views/backoffice/inicio"> <!-- InicioBackOfficeController -->
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Mi panel
						</a>
						<a class="nav-link" href="views/backoffice/usuarios"> <!-- UsuariosBackOfficeController -->
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Usuarios
						</a>
						<a class="nav-link" href="views/backoffice/clasicos"> <!-- ClasicosBackOfficeController -->
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Total de clásicos
						</a>
						<a class="nav-link" href="views/backoffice/clasicos?total=no"> <!-- ClasicosBackOfficeController -->
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Clásicos publicados
						</a>
						<a class="nav-link" href="views/backoffice/clasicos?validados=no&total=no"> <!-- ClasicosBackOfficeController -->
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Clásicos pendientes
						</a>
						<a class="nav-link" href="views/backoffice/marcas"> <!-- MarcasBackOfficeController -->
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Total de marcas
						</a>
						<a class="nav-link" href="views/backoffice/marcas?total=no"> <!-- MarcasBackOfficeController -->
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Marcas publicadas
						</a>
						<a class="nav-link" href="views/backoffice/marcas?validadas=no&total=no"> <!-- MarcasBackOfficeController -->
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Marcas pendientes
						</a>
						<!-- Eliminado por carecer de utilidad.
						<a class="nav-link" href="views/backoffice/migracion">  MigracionBackOfficeController 
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Lanzar proceso de migración
						</a>
						<a class="nav-link" href="#">
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Parsear web
						</a>  -->
					</div>
				</div>
			</nav>
		</div>