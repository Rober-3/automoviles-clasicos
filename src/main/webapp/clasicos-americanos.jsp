<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="includes/header.jsp">
	<jsp:param name="pagina" value="Clasicos americanos" />
	<jsp:param name="title" value="Clasicos americanos" />
</jsp:include>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%
	// TODO Notas: mostrar en una aplicación web todos los registros de una tabla.
	// TODO Notas: Puede usarse ${}, llamado EL (Expression Language en los JSPs). Suele ser más comodo que usar < % % >,
	//			   (SCRIPTLETS). Además, pueden combinarse con JSTL o Java Servlet Tag Libraries,(librerías de etiquetas).
%>
	

<main>
	
		<div class="container my-5">

			<h3 class="text-center">Clásicos americanos</h3>

			<div class="my-5"> <a href="index.jsp">Página principal</a> </div>

			<!-- Tabla -->

			<table class="table table-striped mt-3 tabla">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Id</th>
						<th scope="col">Modelo</th>
						<th scope="col">Marca</th>
						<th scope="col">Año</th>
						<th scope="col">Foto</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>

					<!-- Recoger la información o atributo enviado desde el controlador. -->
					<c:forEach items="${clasicosAmericanos}" var="a">

						<tr>

							<td class="align-middle">${a.id}</td>
							<td class="align-middle">${a.modelo}</td>
							<td class="align-middle">${a.marca}</td>
							<td class="align-middle">${a.anio}</td>
							<td class="align-middle"><img src="${a.foto}" class="img-thumbnail" alt="foto"></td>
							
							<td class="align-middle">
								<% // Se pasa el id del clásico a editar o eliminar como parámetro en la URL. %>
								<a href="insertar-editar?id=${a.id}"> <i class="fas fa-edit fa-1x mx-2 align-middle" title="Editar"> </i> </a>
								<a href="eliminar?id=${a.id}" onclick="confirmar('${a.modelo}')"> <i class="fas fa-trash fa-1x mx-2" title="Eliminar"> </i> </a>
								<!-- El evento confirmar ejecuta un script de JavaScript para confirmar la eliminación de un modelo. -->
							</td>

						</tr>
						
					</c:forEach>
				</tbody>
				
			</table>
			
		</div>
		
	</main>

	<%@include file="includes/footer.jsp"%>