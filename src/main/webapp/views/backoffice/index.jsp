<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../../includes/office-header.jsp">
	<jsp:param name="pagina" value="Indice backoffice" />
	<jsp:param name="title" value="Indice backoffice" />
</jsp:include>
<jsp:include page="../../includes/office-navbar-admin.jsp" />

<!-- Contenido principal -->
<div id="layoutSidenav_content">
	<main>
		<h3 class="text-center">Estadísticas</h3>
		<div class="container-fluid">
			<a href="views/backoffice/index2.jsp">index2</a>
			<ol class="breadcrumb mb-3">
				<li class="breadcrumb-item active">Clásicos</li>
			</ol>
			<div class="row">
				<div class="col-xl-3 col-md-6">
					<div class="card bg-info text-white mb-5">
						<div class="card-body">Total de clásicos:

							<!--InicioBackOfficeController -->
							<span class="numero">${estadisticasClasicos.total}</span>
						</div>
						<div class="card-footer d-flex align-items-center justify-content-between">

							<!-- ClasicosBackOfficeController -->
							<a class="small text-white stretched-link"
							   href="views/backoffice/clasicos">Ver detalles</a>

							<div class="small text-white">
								<i class="fas fa-angle-right"></i>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-md-6">
					<div class="card bg-success text-white mb-5">
						<div class="card-body">Clásicos publicados:

							<!--InicioBackOfficeController -->
							<span class="numero">${estadisticasClasicos.aprobados}</span>
						</div>
						<div class="card-footer d-flex align-items-center justify-content-between">

							<!-- ClasicosBackOfficeController -->
							<a class="small text-white stretched-link"
							   href="views/backoffice/clasicos?total=no">Ver detalles</a>

							<div class="small text-white">
								<i class="fas fa-angle-right"></i>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-md-6">
					<div class="card bg-warning text-white mb-5">
						<div class="card-body">Clásicos pendientes:

							<!--InicioBackOfficeController -->
							<span class="numero">${estadisticasClasicos.pendientes}</span>
						</div>
						<div
							class="card-footer d-flex align-items-center justify-content-between">

							<!-- ClasicosBackOfficeController -->
							<a class="small text-white stretched-link"
							   href="views/backoffice/clasicos?validados=no&total=no">Ver detalles</a>

							<div class="small text-white">
								<i class="fas fa-angle-right"></i>
							</div>
						</div>
					</div>
				</div>
			</div>
			<ol class="breadcrumb mb-3">
				<li class="breadcrumb-item active">Marcas</li>
			</ol>
			<div class="row">
				<div class="col-xl-3 col-md-6">
					<div class="card bg-info text-white mb-5">
						<div class="card-body">Total de marcas:

							<!--InicioBackOfficeController -->
							<span class="numero">${estadisticasMarcas.total}</span>
						</div>
						<div class="card-footer d-flex align-items-center justify-content-between">

							<!-- MarcasBackOfficeController -->
							<a class="small text-white stretched-link"
							   href="views/backoffice/marcas">Ver detalles</a>

							<div class="small text-white">
								<i class="fas fa-angle-right"></i>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-md-6">
					<div class="card bg-success text-white mb-5">
						<div class="card-body">Marcas publicadas:

							<!--InicioBackOfficeController -->
							<span class="numero">${estadisticasMarcas.aprobadas}</span>
						</div>
						<div class="card-footer d-flex align-items-center justify-content-between">

							<!-- MarcasBackOfficeController -->
							<a class="small text-white stretched-link"
							   href="views/backoffice/marcas?total=no">Ver detalles</a>

							<div class="small text-white">
								<i class="fas fa-angle-right"></i>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-md-6">
					<div class="card bg-warning text-white mb-5">
						<div class="card-body">Marcas pendientes:

							<!--InicioBackOfficeController -->
							<span class="numero">${estadisticasMarcas.pendientes}</span>
						</div>
						<div class="card-footer d-flex align-items-center justify-content-between">

							<!-- MarcasBackOfficeController -->
							<a class="small text-white stretched-link"
							   href="views/backoffice/marcas?validadas=no&total=no">Ver detalles</a>

							<div class="small text-white">
								<i class="fas fa-angle-right"></i>
							</div>
						</div>
					</div>
				</div>
			</div>
			<ol class="breadcrumb mb-3">
				<li class="breadcrumb-item active">Usuarios</li>
			</ol>
			<div class="row">
				<div class="col-xl-3 col-md-6">
					<div class="card bg-info text-white mb-5">
						<div class="card-body">Usuarios:

							<!--InicioBackOfficeController -->
							<span class="numero">{}</span>
						</div>
						<div class="card-footer d-flex align-items-center justify-content-between">
							<a class="small text-white stretched-link" href="#">Ver detalles</a>

							<div class="small text-white">
								<i class="fas fa-angle-right"></i>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<h5 class="col">Usuarios que han iniciado sesión</h5>
			</div>
			<div class="row">
				<div class="col">
					<div class="table-responsive">
						<table class="table" id="dataTable" width="100%" cellspacing="0">
							<thead>
								<tr>
									<th>Id</th>
									<th>Nombre</th>
									<th>Key</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<!-- UsuariosLogueadosListener -->
									<c:forEach items="${usuariosLogueados}" var="ul">
										<td>${ul.value.id}</td>
										<td>${ul.value.nombre}</td>
										<td>${ul.key}</td>
									</c:forEach>	
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</main>
</div>
<jsp:include page="../../includes/office-footer.jsp" />
