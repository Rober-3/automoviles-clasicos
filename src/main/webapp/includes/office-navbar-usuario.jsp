<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body class="sb-nav-fixed">
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-secondary">
		<a class="navbar-brand text-dark" href="index.html">Insertar logo</a>
		<button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#">
			<i class="fas fa-bars"></i>
		</button>
		<!-- InicioFrontOfficeController / ClasicosFrontOfficeController / MarcasFrontOfficeController -->
		<h3 class="navbar-brand text-white">${encabezado}</h3>
		<!-- Navbar-->
		<ul class="navbar-nav d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
			<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" id="userDropdown" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				<i class="fas fa-user fa-fw"></i>
			</a>
				<div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
					<span class="dropdown-item">Has iniciado sesión como ${sessionScope.usuario.nombre}</span>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="logout">Cerrar sesión</a> <!-- LogoutController -->
				</div>
			</li>
		</ul>
	</nav>
	<div class="mt-5">
		<jsp:include page="alerta.jsp"></jsp:include>
	</div>
	<div id="layoutSidenav"> <!-- Termina en office-footer.jsp. -->
		<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-light" id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">
						<div class="sb-sidenav-menu-heading">Navegación</div>
						<a class="nav-link" href="inicio"> <!-- InicioController -->
							<div class="sb-nav-link-icon">
								<i class="fas fa-power-off"></i>
							</div> Inicio del sitio
						</a>
						<a class="nav-link" href="views/frontoffice/inicio"> <!-- InicioFrontOfficeController -->
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Mi panel
						</a>
						<div class="sb-sidenav-menu-heading">Secciones</div>
						<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseClasicos" aria-expanded="false" aria-controls="collapseClasicos">
							<div class="sb-nav-link-icon">
								<i class="fas fa-car-side"></i>
							</div> Mis clásicos
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapseClasicos" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<!-- Para mostrar los clásicos publicados y los pendientes de validación se utiliza el mismo controlador
								(ClasicosFrontOfficeController) al que se le pasan dos parámetros: uno que indica si están pendientes de
								validar (validados) y otro que indica si se mostrarán todos o sólo los publicados (total). Los nombres y
								los valores de dichos parámetros pueden ser cualesquiera, el controlador simplemente los recoge y los 
								evalúa para mostrar unos clásicos u otros.-->
								<a class="nav-link" href="views/frontoffice/clasicos">Lista</a>
								<a class="nav-link" href="views/frontoffice/clasicos?validados=no&total=no">Pendientes</a>
								<a class="nav-link" href="views/frontoffice/clasicos?total=no">Publicados</a>
							</nav>
						</div>
						<a class="nav-link" href="views/frontoffice/insertar-editar-clasico"> <!-- InsEditClasFrontOfficeController -->
							<div class="sb-nav-link-icon">
								<i class="fas fa-plus"></i>
							</div> Insertar clásico
						</a>
						<a class="nav-link" href="views/frontoffice/insertar-editar-marca"> <!-- InsEditMarcaFrontOfficeController -->
							<div class="sb-nav-link-icon">
								<i class="fas fa-plus"></i>
							</div> Insertar marca
						</a>
					</div>
				</div>
			</nav>
		</div>