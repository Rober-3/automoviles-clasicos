<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<base href="${pageContext.request.contextPath}/" />
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
		<!-- Font Awesome -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
		<!-- CSS personalizado -->
		<link rel="stylesheet" href="css/styles.css?d=<%=System.currentTimeMillis()%>">
		<title>Inicio de sesión</title>
	</head>
    <body>
    	<jsp:include page="/includes/alerta.jsp"></jsp:include>
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Inicio de sesión</h3></div>
                                    <div class="card-body">
                                        <form action="login" method="post" class="mt-3">
                                        	<!-- value="${sessionScope.usuario.nombre}" -->
                                            <div class="form-group">
                                                <label class="mb-1" for="nombre">Nombre de usuario</label>
                                                <input class="form-control py-4" 
                                               		   id="nombre"
                                               		   name="nombre"
                                               		   type="text"
                                               		   value="admin"
                                               		   placeholder="Introduce tu nombre de usuario" />
                                            </div>
                                            <!-- value="${sessionScope.usuario.contrasena}" -->
                                            <div class="form-group">
                                            	<div>
                                                	<label class="mb-1" for="contrasena">Contraseña</label>
                                                	<i class="far fa-eye" onclick="showHidePass('contrasena')"></i>
												</div>
                                                <input class="form-control py-4"
                                                	   id="contrasena"
                                                	   name="contrasena"
                                                	   type="password"
                                                	   value="admin"
                                                	   placeholder="Introduce tu contraseña" />
                                            </div>
                                            <div class="form-group d-flex align-items-center justify-content-end my-3 mb-0">
                                                <button type="submit" class="btn btn-primary">Iniciar sesión</button>
                                                <button type="button" class="btn btn-danger ml-3" onClick="window.parent.location='inicio'">Cancelar</button>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="card-footer text-right">
                                        <a href="views/public/registro.jsp">Registro de usuario</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
        <%@include file="../../includes/footer.jsp"%>
        