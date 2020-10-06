<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<body class="sb-nav-fixed">
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">

		<a class="navbar-brand" href="index.html">Mi panel</a>

		<!-- Navbar-->
		<ul class="navbar-nav ml-auto ml-md-0">
			<li class="nav-item">
			
				<!-- LogoutController -->
				<a class="dropdown-item text-white" href="logout">Cerrar sesión</a>
			</li>
		</ul>
	</nav>
	
	<div class="mt-5"><jsp:include page="alerta.jsp"></jsp:include></div>
	
	<div id="layoutSidenav"> <!-- Termina en office-footer.jsp. -->
		<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-dark"
				id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">
						<div>
							<!-- InicioController -->
							<a class="nav-link" href="inicio">
								<div class="sb-nav-link-icon">
									<i class="fas fa-tachometer-alt"></i>
								</div> Inicio del sitio
							</a>
							<!-- InicioBackOfficeController -->
							<a class="nav-link" href="views/backoffice/inicio">
								<div class="sb-nav-link-icon">
									<i class="fas fa-tachometer-alt"></i>
								</div> Mi panel
							</a>
							<!-- UsuariosBackOfficeController -->
							<a class="nav-link" href="views/backoffice/usuarios">
								<div class="sb-nav-link-icon">
									<i class="fas fa-tachometer-alt"></i>
								</div> Usuarios
							</a>
							<a class="nav-link" href="#">
								<div class="sb-nav-link-icon">
									<i class="fas fa-tachometer-alt"></i>
								</div> Clásicos
							</a>
							<a class="nav-link" href="#">
								<div class="sb-nav-link-icon">
									<i class="fas fa-tachometer-alt"></i>
								</div> Marcas
							</a>
							<!-- MigracionBackOfficeController -->
							<a class="nav-link" href="views/backoffice/migracion">
								<div class="sb-nav-link-icon">
									<i class="fas fa-tachometer-alt"></i>
								</div> Lanzar proceso de migración
							</a>
							<a class="nav-link" href="#">
								<div class="sb-nav-link-icon">
									<i class="fas fa-tachometer-alt"></i>
								</div> Parsear web
							</a>
						</div>
					</div>
				</div>
			</nav>
		</div>