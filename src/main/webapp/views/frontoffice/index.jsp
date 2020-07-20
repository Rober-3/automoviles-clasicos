<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../../includes/office-header.jsp" />
<jsp:include page="../../includes/office-navbar.jsp" />

<div id="layoutSidenav_content"> <!-- Termina en office-footer.jsp. -->
	<main>
		<div class="container-fluid">
		
			<h3>Mis clásicos</h3>
			<div class="row">

				<div class="col-xl-3 col-md-6">
					<div class="card bg-success text-white mb-4">
					
						<div class="card-body">Clásicos publicados
							${clasicos_publicados}</div> <!--InicioFrontOfficeController -->
						<div
						
							class="card-footer d-flex align-items-center justify-content-between">
							<a class="small text-white stretched-link" href="#">View
								Details</a>
							<div class="small text-white">
								<i class="fas fa-angle-right"></i>
							</div>
						</div>
					</div>
				</div>

				<div class="col-xl-3 col-md-6">
					<div class="card bg-warning text-white mb-4">
					
						<div class="card-body">Clásicos pendientes de aprobación
							${clasicos_pendientes}</div> <!-- InicioFrontOfficeController -->
						<div
						
							class="card-footer d-flex align-items-center justify-content-between">
							<a class="small text-white stretched-link" href="#">View
								Details</a>
							<div class="small text-white">
								<i class="fas fa-angle-right"></i>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<h3>Mi perfil</h3>
			<div class="row">
				${usuario} <!-- LoginController -->
			</div>

		</div>
	</main>

	<jsp:include page="../../includes/office-footer.jsp" />