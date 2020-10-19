<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="../../includes/header.jsp">
	<jsp:param name="pagina" value="login" />
	<jsp:param name="title" value="login" />
</jsp:include>

<main>
	<div class="container my-5 formulario">
		<h3 class="text-center">Registro de usuario</h3>
		<form action="registro" method="post" class="mt-3"> <!-- RegistroUsuarioController -->
			<div class="form-group">
				<label for="nombre">Nombre de usuario</label>
				<!-- Etiqueta que emplea Bootstrap para mostrar mensajes en pequeño. Se
						 usará para indicar si el nombre de usuario está o no disponible. -->
				<small id="nombreHelp" class="form-text"></small>
				<input type="text"
					   id="nombre"
					   name="nombre"
					   onkeyup="buscarUsuario(event)"
					   value="${nombre}"
					   class="form-control"
					   placeholder="Introduce tu nombre de usuario">
			</div>
			<div class="form-group">
				<label for="fecha">Fecha de nacimiento</label>
				<input type="date"
					   id="fecha"
					   name="fecha"
					   value="${fecha}"
					   class="form-control"
					   placeholder="Introduce tu fecha de nacimiento">
			</div>
			<div class="form-group">
				<label for="contrasena">Contraseña</label>
				<input type="password"
					   id="contrasena"
					   name="contrasena"
					   class="form-control"
					   placeholder="Introduce la contraseña">
			</div>
			<div class="form-group">
				<label for="confirmar">Confirmar contraseña</label>
				<input type="password"
					   id="confirmar"
					   name="confirmar"
					   class="form-control"
					   placeholder="Confirma la contraseña">
			</div>
			<button type="submit" class="btn btn-primary btn-sm">Registrarse</button>
		</form>
	</div>
</main>
<%@include file="../../includes/footer.jsp"%>
