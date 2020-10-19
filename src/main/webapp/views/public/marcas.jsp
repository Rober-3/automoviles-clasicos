<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="../includes/header.jsp">
	<jsp:param name="pagina" value="Marcas" />
	<jsp:param name="title" value="Marcas" />
</jsp:include>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%
	// TODO Notas: mostrar en una aplicación web todos los registros de una tabla.
	// TODO Notas: Puede usarse ${}, llamado EL (Expression Language en los JSPs). Suele ser más comodo que usar < % % >,
	//			   (SCRIPTLETS). Además, pueden combinarse con JSTL o Java Servlet Tag Libraries,(librerías de etiquetas).
%>
	

<main>
	
		<div class="tablamarcas container my-5">
			
			<h2 class="text-center my-5">Marcas</h2>

			<!-- Tabla -->

			<table class="table table-striped mt-3 tabla">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Id</th>
						<th scope="col">Marca</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<!-- Recoger la información o atributo enviado desde el controlador. -->
					<c:forEach items="${marcas}" var="m">
						<tr>

							<td class="align-middle">${m.id}</td>
							<td class="align-middle">${m.marca}</td>
							
							<td class="align-middle">
								<a href="marcas?id=${m.id}"> <i class="fas fa-edit fa-1x mx-2 align-middle" title="Editar"> </i> </a>
								<a href="marcas?id=${m.id}" onclick="confirmar('${m.marca}')"> <i class="fas fa-trash fa-1x mx-2" title="Eliminar"> </i> </a>
								<!-- El evento confirmar ejecuta un script de JavaScript para confirmar la eliminación de un modelo. -->
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>

	<%@include file="../includes/footer.jsp"%>