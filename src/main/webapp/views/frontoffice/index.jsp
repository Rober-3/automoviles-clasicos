<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../../includes/office-header.jsp">
	<jsp:param name="pagina" value="Mi panel" />
	<jsp:param name="title" value="Mi panel" />
</jsp:include>
<jsp:include page="../../includes/office-navbar-usuario.jsp" />

<!-- Contenido principal -->
<div id="layoutSidenav_content">
	<main>
		<div class="container-fluid pt-3">

			<h3>Mis clásicos</h3>
			<div class="row">

				<div class="col-xl-3 col-md-6">
					<div class="card bg-info text-white mb-4">
						<div class="card-body">Clásicos totales: 
							<span class="numeroClasicos">${resumenUsuario.clasicosTotales}</span> <!--InicioFrontOfficeController -->
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
							<span class="numeroClasicos">${resumenUsuario.clasicosAprobados}</span> <!--InicioFrontOfficeController -->
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
						<div class="card-body">Clásicos pendientes de aprobar: 
							<span class="numeroClasicos">${resumenUsuario.clasicosPendientes}</span> <!--InicioFrontOfficeController -->
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

			<h3>Mi perfil</h3>
			<div class="row">
				<!-- LoginController -->
				Nombre de usuario: ${usuario.nombre}<br>
				Contraseña: ${usuario.contrasena}<br>
				Foto de perfil: ${usuario.imagen}
			</div>

		</div>
	</main>
 </div>

<jsp:include page="../../includes/office-footer.jsp" />
