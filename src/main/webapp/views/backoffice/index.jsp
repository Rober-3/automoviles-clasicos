<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../includes/office-head.jsp">
	<jsp:param name="pagina" value="Indice backoffice" />
	<jsp:param name="title" value="Indice backoffice" />
</jsp:include>
<jsp:include page="../../includes/office-navbar-admin.jsp" />
<!-- Contenido principal -->
<div id="layoutSidenav_content">
	<main>
		<h3 class="text-center">Estadísticas</h3>
		<div class="container-fluid">
			<ol class="breadcrumb mb-3">
				<li class="breadcrumb-item active">Clásicos</li>
			</ol>
			<div class="row">
				<div class="col-xl-3 col-md-6">
					<div class="card bg-info text-white mb-5">
						<div class="card-body">Total de clásicos:
							<span class="numero">${estadisticasClasicos.total}</span> <!--InicioBackOfficeController -->
						</div>
						<div class="card-footer d-flex align-items-center justify-content-between">
							<a class="small text-white stretched-link" href="views/backoffice/clasicos">Ver detalles</a> <!-- ClasicosBackOfficeController -->
							<div class="small text-white">
								<i class="fas fa-angle-right"></i>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-md-6">
					<div class="card bg-success text-white mb-5">
						<div class="card-body">Clásicos publicados:
							<span class="numero">${estadisticasClasicos.aprobados}</span> <!--InicioBackOfficeController -->
						</div>
						<div class="card-footer d-flex align-items-center justify-content-between">
							<a class="small text-white stretched-link" href="views/backoffice/clasicos?total=no">Ver detalles</a> <!-- ClasicosBackOfficeController -->
							<div class="small text-white">
								<i class="fas fa-angle-right"></i>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-md-6">
					<div class="card bg-warning text-white mb-5">
						<div class="card-body">Clásicos pendientes:
							<span class="numero">${estadisticasClasicos.pendientes}</span> <!--InicioBackOfficeController -->
						</div>
						<div class="card-footer d-flex align-items-center justify-content-between">			
							<a class="small text-white stretched-link" href="views/backoffice/clasicos?validados=no&total=no">Ver detalles</a> <!-- ClasicosBackOfficeController -->
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
							<span class="numero">${estadisticasMarcas.total}</span> <!--InicioBackOfficeController -->
						</div>
						<div class="card-footer d-flex align-items-center justify-content-between">
							<a class="small text-white stretched-link" href="views/backoffice/marcas">Ver detalles</a> <!-- MarcasBackOfficeController -->
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
						<div class="card-body">Total de usuarios:
							<span class="numero">${estadisticasUsuarios.total}</span> <!--InicioBackOfficeController -->
						</div>
						<div class="card-footer d-flex align-items-center justify-content-between">
							<a class="small text-white stretched-link" href="views/backoffice/usuarios">Ver detalles</a>
							<div class="small text-white">
								<i class="fas fa-angle-right"></i>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<h3 class="col text-center">Usuarios que han iniciado sesión</h3>
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
									<c:forEach items="${usuariosLogueados}" var="ul"> <!-- UsuariosLogueadosListener -->
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
