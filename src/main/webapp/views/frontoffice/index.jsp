<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../includes/office-head.jsp">
	<jsp:param name="pagina" value="Indice frontoffice" />
	<jsp:param name="title" value="Indice frontoffice" />
</jsp:include>
<jsp:include page="../../includes/office-navbar-usuario.jsp" />
<div id="layoutSidenav_content">
	<main>
		<h3 class="text-center">Estadísticas</h3>
		<div class="container-fluid pt-3">
			<ol class="breadcrumb mb-3">
				<li class="breadcrumb-item active">Mis clásicos</li>
			</ol>
			<div class="row">
				<div class="col-xl-3 col-md-6">
					<div class="card bg-info text-white mb-4">
						<div class="card-body">Total de clásicos: 
							<span class="numero">${resumenUsuario.clasicosTotal}</span>
						</div>
						<div class="card-footer d-flex align-items-center justify-content-between">
							<a class="small text-white stretched-link" href="views/frontoffice/clasicos">Ver detalles</a>
							<div class="small text-white">
								<i class="fas fa-angle-right"></i>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-md-6">
					<div class="card bg-success text-white mb-4">
						<div class="card-body">Clásicos publicados:
							<span class="numero">${resumenUsuario.clasicosAprobados}</span>
						</div>
						<div class="card-footer d-flex align-items-center justify-content-between">	
							<a class="small text-white stretched-link" href="views/frontoffice/clasicos?total=no">Ver detalles</a>
							<div class="small text-white">
								<i class="fas fa-angle-right"></i>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-md-6">
					<div class="card bg-warning text-white mb-4">
						<div class="card-body">Clásicos pendientes de aprobar:	
							<span class="numero">${resumenUsuario.clasicosPendientes}</span>
						</div>
						<div class="card-footer d-flex align-items-center justify-content-between">			
							<a class="small text-white stretched-link" href="views/frontoffice/clasicos?validados=no&total=no">Ver detalles</a>
							<div class="small text-white">
								<i class="fas fa-angle-right"></i>
							</div>
						</div>
					</div>
				</div>
			</div>
			<ol class="breadcrumb mb-3">
				<li class="breadcrumb-item active">Mis marcas</li>
			</ol>
			<div class="row mt-5">
				<h3 class="col text-center">Mi perfil</h3>
			</div>
			<div class="row">
				<div class="col">
					<div class="table-responsive">
						<table class="table" id="dataTable" width="100%" cellspacing="0">
							<thead>
								<tr>
									<th>Nombre</th>
									<th>Contraseña</th>
									<th>Foto</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>${usuario.nombre}</td>
									<td>${usuario.contrasena}</td>
									<td>${usuario.imagen}</td>
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
