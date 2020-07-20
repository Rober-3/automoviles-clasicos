<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<body class="sb-nav-fixed">
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">

		<a class="navbar-brand" href="index.html">Mi panel</a>

		<!-- Navbar-->
		<ul class="navbar-nav ml-auto ml-md-0">
			<li class="nav-item"><a class="dropdown-item text-white" href="logout">Cerrar sesión</a></li>
		</ul>

	</nav>
	<div id="layoutSidenav"> <!-- Termina en office-footer.jsp. -->
		<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">
					
						<a class="nav-link" href="inicio">
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Inicio del sitio
						</a>
						
						<a class="nav-link" href="views/frontoffice/inicio">
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Mi panel
						</a>
						
						<!-- Para mostrar los clásicos publicados y los pendientes de aprobación se usa el mismo
						controlador (ClasicosFrontOfficeController) al que se le pasa un parámetro que indica si 
						están pendientes de aprobar (en este caso es "validados"). Tanto el nombre como el valor
						de dicho parámetro pueden ser cualesquiera, el controlador simplemente lo recoge y evalúa
						para mostrar unos clásicos u otros.-->
						
						<a class="nav-link" href="views/frontoffice/clasicos">
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Clásicos publicados
						</a>
						
						<a class="nav-link" href="views/frontoffice/clasicos?validados=0">
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Clásicos pendientes de aprobación
						</a>
						
						<a class="nav-link" href="views/frontoffice/insertar">
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Registrar un nuevo clásico
						</a>
						
					</div>
				</div>
			</nav>
		</div>