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
	<div class="mt-5"><jsp:include page="alerta.jsp"></jsp:include></div>
	<div id="layoutSidenav"> <!-- Termina en office-footer.jsp. -->
		<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">
						<a class="nav-link" href="inicio"> <!-- InicioController -->
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Inicio del sitio
						</a>
						<a class="nav-link" href="views/frontoffice/inicio"> <!-- InicioFrontOfficeController -->
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Mi panel
						</a>
						
						<!-- Añadí esta parte a posteriori. Puse otro parámetro para que funcionaran las tres opciones. -->
						<a class="nav-link" href="views/frontoffice/clasicos"> <!-- ClasicosFrontOfficeController -->
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Total de clásicos
						</a>
						
						<!-- Para mostrar los clásicos publicados y los pendientes de aprobación se utiliza el mismo controlador
						(ClasicosFrontOfficeController) al que se le pasa un parámetro que indica si están pendientes de aprobar
						(en este caso es "validados"). Tanto el nombre como el valor de dicho parámetro pueden ser cualesquiera,
						el controlador simplemente recoge el parámetro y lo evalúa para mostrar unos clásicos u otros.-->
						
						<a class="nav-link" href="views/frontoffice/clasicos?total=no"> <!-- ClasicosFrontOfficeController -->
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Clásicos publicados
						</a>
						<a class="nav-link" href="views/frontoffice/clasicos?validados=no&total=no"> <!-- ClasicosFrontOfficeController -->
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Clásicos pendientes
						</a>
						<a class="nav-link" href="views/frontoffice/insertar"> <!--  InsertarFrontOfficeController -->
							<div class="sb-nav-link-icon">
								<i class="fas fa-tachometer-alt"></i>
							</div> Insertar clásico
						</a>
					</div>
				</div>
			</nav>
		</div>