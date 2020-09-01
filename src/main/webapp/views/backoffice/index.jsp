<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../../includes/office-header.jsp">
	<jsp:param name="pagina" value="Indice backoffice" />
	<jsp:param name="title" value="Indice backoffice" />
</jsp:include>
<jsp:include page="../../includes/office-navbar-admin.jsp" />

<!-- Contenido principal -->
<div id="layoutSidenav_content">
	<main>
		<div class="container-fluid pt-3">

			<div class="row">

				<div class="col-xl-3 col-md-6">
					<div class="card bg-info text-white mb-4">
						<div class="card-body">Usuarios: 
							<span class="numeroClasicos">{}</span> <!--InicioFrontOfficeController -->
						</div>
						<div class="card-footer d-flex align-items-center justify-content-between">
							<a class="small text-white stretched-link" href="#">Ver detalles</a>
							<div class="small text-white">
								<i class="fas fa-angle-right"></i>
							</div>
						</div>
					</div>
				</div>

				<div class="col-xl-3 col-md-6">
					<div class="card bg-success text-white mb-4">
						<div class="card-body">Clásicos publicados: 
							<span class="numeroClasicos">{}</span> <!--InicioFrontOfficeController -->
						</div>
						<div class="card-footer d-flex align-items-center justify-content-between">
							<a class="small text-white stretched-link" href="#">Ver detalles</a>
							<div class="small text-white">
								<i class="fas fa-angle-right"></i>
							</div>
						</div>
					</div>
				</div>

				<div class="col-xl-3 col-md-6">
					<div class="card bg-warning text-white mb-4">
						<div class="card-body">Clásicos pendientes de aprobación: 
							<span class="numeroClasicos">{}</span> <!--InicioFrontOfficeController -->
						</div>
						<div class="card-footer d-flex align-items-center justify-content-between">
							<a class="small text-white stretched-link" href="#">Ver detalles</a>
							<div class="small text-white">
								<i class="fas fa-angle-right"></i>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-xl-3 col-md-6">
					<div class="card bg-success text-white mb-4">
						<div class="card-body">Marcas: 
							<span class="numeroClasicos">{}</span> <!--InicioFrontOfficeController -->
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

			<h3>Usuarios que han iniciado sesión</h3>
			<div class="row">
				<ul class="list-group">
					<!-- UsuariosLogueadosListener -->
					<c:forEach items="${usuariosLogueados}" var="ul">
						<li class="list-group-item"><b>KEY:</b> ${ul.key} &nbsp&nbsp <b>ID:</b> ${ul.value.id} &nbsp&nbsp <b>NOMBRE:</b> ${ul.value.nombre}</li>
					</c:forEach>
				</ul>
			</div>

		</div>
	</main>
 </div>

<jsp:include page="../../includes/office-footer.jsp" />
