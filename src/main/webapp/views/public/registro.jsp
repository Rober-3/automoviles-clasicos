<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../includes/head.jsp">
	<jsp:param name="pagina" value="Registro de usuario" />
	<jsp:param name="title" value="Registro de usuario" />
</jsp:include>
<main class="container mt-2">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-5">
				<div class="card shadow-lg border-0 rounded-lg mt-5">
					<div class="card-header">
						<h3 class="text-center font-weight-light my-4">Registro de usuario</h3>
					</div>
					<div class="card-body">
						<form action="registro" method="post" class="mt-3">
							<div class="form-group">
								<label class="mb-1" for="nombre">Nombre de usuario</label>
								<small id="nombreHelp" class="form-text"></small>
								<input class="form-control py-4"
									   type="text"
									   id="nombre"
									   name="nombre"
									   onkeyup="buscarUsuario(event)"
									   value="${nombre}"
									   placeholder="Introduce tu nombre de usuario" />
							</div>
							<div class="form-group">
								<label class="mb-1" for="contrasena">Contraseña</label>
								<input class="form-control py-4"
									   type="password"
									   id="contrasena"
									   name="contrasena"
									   placeholder="Introduce la contraseña" />
							</div>
							<div class="form-group">
								<label class="mb-1" for="confirmar">Confirmar contraseña</label>
								<input class="form-control py-4"
									   type="password"
						   			   id="confirmar"
									   name="confirmar"
									   placeholder="Confirma la contraseña">
							</div>
							<div class="form-group d-flex align-items-center justify-content-end my-3 mb-0">
								<button type="submit" class="btn btn-primary">Registrarse</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</main>
<%@include file="../../includes/footer.jsp"%>
