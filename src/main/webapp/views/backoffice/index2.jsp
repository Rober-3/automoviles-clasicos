<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../../includes/office-head.jsp">
	<jsp:param name="pagina" value="Indice backoffice" />
	<jsp:param name="title" value="Indice backoffice" />
</jsp:include>


<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
 	<a class="navbar-brand" href="index.html">Insertar logo</a>
	<button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#"><i class="fas fa-bars"></i></button>
	
	<!-- Opciones cuando un usuario ha iniciado o cerrado sesión. -->
	<span class="form-inline my-2 my-lg-0 font-weight-bold"> <!-- LoginController -->
		<c:if test="${empty sessionScope.usuario}">
			<a class="nav-link text-white" href="views/public/login.jsp">Iniciar sesión</a>
		</c:if>
		<c:if test="${not empty sessionScope.usuario}">
			<span class="text-white">Has iniciado sesión como ${sessionScope.usuario.nombre}</span>
			<c:if test="${sessionScope.usuario.rol.rol == 'usuario'}">
				<a class="nav-link text-white" href="views/frontoffice/inicio">Mi panel</a>
			</c:if>
			<c:if test="${sessionScope.usuario.rol.rol == 'administrador'}">
				<a class="nav-link text-white" href="views/backoffice/inicio">Mi panel</a>
			</c:if>
			<a class="nav-link text-white" href="logout">Cerrar sesión</a>
		</c:if>
	</span>
	
		<!-- Navbar Search-->
		<form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
			<div class="input-group">
				<input class="form-control" type="text" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2" />
				<div class="input-group-append">
					<button class="btn btn-primary" type="button"><i class="fas fa-search"></i></button>
				</div>
			</div>
		</form>
			
	<!-- Navbar-->
	<ul class="navbar-nav ml-auto ml-md-0">
		<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" id="userDropdown" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				<i class="fas fa-user fa-fw"></i>
			</a>
			<div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
				<a class="dropdown-item" href="#">Settings</a>
				<a class="dropdown-item" href="#">Activity Log</a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" href="logout">Cerrar sesión</a> <!-- LogoutController -->
			</div>
		</li>
	</ul>
</nav>
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
					</a> <a class="nav-link" href="views/backoffice/inicio"> <!-- InicioBackOfficeController -->
						<div class="sb-nav-link-icon">
							<i class="fas fa-tachometer-alt"></i>
						</div> Mi panel
					</a>
					<div class="sb-sidenav-menu-heading">Secciones</div>
					<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseClasicos" aria-expanded="false" aria-controls="collapseClasicos">
						<div class="sb-nav-link-icon">
							<i class="fas fa-car-side"></i>
						</div> Clásicos
						<div class="sb-sidenav-collapse-arrow">
							<i class="fas fa-angle-down"></i>
						</div>
					</a>
					
					<div class="collapse" id="collapseClasicos" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
						<nav class="sb-sidenav-menu-nested nav">
							<!-- ClasicosBackOfficeController -->
							<a class="nav-link" href="views/backoffice/clasicos">Total</a>
							<a class="nav-link" href="views/backoffice/clasicos?validados=no&total=no">Pendientes</a>
							<a class="nav-link" href="views/backoffice/clasicos?total=no">Publicados</a>
						</nav>
					</div>
					
					<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseMarcas" aria-expanded="false" aria-controls="collapseMarcas">
						<div class="sb-nav-link-icon">
							<i class="fas fa-book-open"></i>
						</div>Marcas
						<div class="sb-sidenav-collapse-arrow">
							<i class="fas fa-angle-down"></i>
						</div>
					</a>
					
					<div class="collapse" id="collapseMarcas" aria-labelledby="headingTwo" data-parent="#sidenavAccordion">
						<nav class="sb-sidenav-menu-nested nav">
							<!-- MarcasBackOfficeController -->
							<a class="nav-link" href="views/backoffice/marcas">Total</a>
							<a class="nav-link" href="views/backoffice/marcas?validadas=no&total=no">Pendientes</a>
							<a class="nav-link" href="views/backoffice/marcas?total=no">Publicadas</a>
						</nav>
					</div>
					
					<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUsuarios" aria-expanded="false" aria-controls="collapseUsuarios">
						<div class="sb-nav-link-icon">
							<i class="fas fa-users"></i>
						</div>Usuarios
						<div class="sb-sidenav-collapse-arrow">
							<i class="fas fa-angle-down"></i>
						</div>
					</a>
					
					<div class="collapse" id="collapseUsuarios" aria-labelledby="headingTwo" data-parent="#sidenavAccordion">
						<nav class="sb-sidenav-menu-nested nav">
							<!-- UsuariosBackOfficeController -->
							<a class="nav-link" href="views/backoffice/usuarios">Lista</a>
						</nav>
					</div>
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
					
					<div class="sb-sidenav-menu-heading">Addons</div>
					<a class="nav-link" href="charts.html">
						<div class="sb-nav-link-icon">
							<i class="fas fa-chart-area"></i>
						</div> Charts
					</a> <a class="nav-link" href="tables.html">
						<div class="sb-nav-link-icon">
							<i class="fas fa-table"></i>
						</div> Tables
					</a>
				</div>
				<div class="sb-sidenav-footer">
					<div class="small">Logged in as:</div>
					Start Bootstrap
				</div>
			</div>
		</nav>
	</div>
	<div id="layoutSidenav_content">
		<main>
			<div class="container-fluid">
				<h1 class="mt-4">Mi panel</h1>
				<ol class="breadcrumb mb-4">
					<li class="breadcrumb-item active">Estadísticas</li>
				</ol>
				<div class="row">
					<div class="col-xl-3 col-md-6">
						<div class="card bg-info text-white mb-4">
							<div class="card-body">Total</div>
							<div
								class="card-footer d-flex align-items-center justify-content-between">
								<a class="small text-white stretched-link" href="#">Ver
									detalles</a>
								<div class="small text-white">
									<i class="fas fa-angle-right"></i>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-md-6">
						<div class="card bg-warning text-white mb-4">
							<div class="card-body">Pendientes</div>
							<div
								class="card-footer d-flex align-items-center justify-content-between">
								<a class="small text-white stretched-link" href="#">Ver
									detalles</a>
								<div class="small text-white">
									<i class="fas fa-angle-right"></i>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-md-6">
						<div class="card bg-success text-white mb-4">
							<div class="card-body">Publicados</div>
							<div
								class="card-footer d-flex align-items-center justify-content-between">
								<a class="small text-white stretched-link" href="#">Ver
									detalles</a>
								<div class="small text-white">
									<i class="fas fa-angle-right"></i>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-table mr-1"></i> DataTable Usuarios que han iniciado sesión
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered" id="dataTable" width="100%"
								cellspacing="0">
								<thead>
									<tr>
										<th>Id</th>
										<th>Nombre</th>
										<th>Key</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th>Id</th>
										<th>Nombre</th>
										<th>Key</th>
									</tr>
								</tfoot>
								<tbody>
									<tr>
										<td>4</td>
										<td>admin</td>
										<td>F9DE93F281B7706247ED1715C4CB51AA</td>
									</tr>
									<tr>
										<td>4</td>
										<td>admin</td>
										<td>F9DE93F281B7706247ED1715C4CB51AA</td>
									</tr>
									<tr>
										<td>4</td>
										<td>admin</td>
										<td>F9DE93F281B7706247ED1715C4CB51AA</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</main>
		<footer class="py-4 bg-light mt-auto">
			<div class="container-fluid">
				<div class="d-flex align-items-center justify-content-between small">
					<div class="text-muted">Copyright &copy; Your Website 2020</div>
					<div>
						<a href="#">Privacy Policy</a> &middot; <a href="#">Terms
							&amp; Conditions</a>
					</div>
				</div>
			</div>
		</footer>
	</div>
</div>
<jsp:include page="../../includes/office-footer.jsp" />
