<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../../includes/header.jsp">
	<jsp:param name="pagina" value="Clasicos" />
	<jsp:param name="title" value="Clasicos" />
</jsp:include>

<%
	// TODO Notas: mostrar en una aplicación web todos los registros de una tabla.
	// TODO Notas: Puede usarse ${}, llamado EL (Expression Language en los JSPs). Suele ser más comodo que usar < % % >,
	//			   (SCRIPTLETS). Además, pueden combinarse con JSTL o Java Servlet Tag Libraries (librerías de etiquetas).
%>

<main>
	<div class="tablaclasicos container my-5">
		<img src="img/encabezado-americanos.png" class="mx-auto my-5 d-block">

		<table class="tabla table table-striped mt-3">
			<thead class="thead-light">
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Modelo</th>
					<th scope="col">Marca</th>
					<th scope="col">Año</th>
					<th scope="col">Foto</th>
					<!-- <th scope="col"></th> -->
				</tr>
			</thead>
			<tbody>
				<!-- Recoger la información o atributo enviado desde el controlador. -->
				<c:forEach items="${clasicos}" var="c"> <!-- ClasicosController -->
					<tr>

						<td class="align-middle">${c.id}</td>
						<td class="align-middle">${c.modelo}</td>
						<td class="align-middle">${c.marca.marca}</td> <!-- Accede al atributo marca del objeto Marca. -->
						<td class="align-middle">${c.anio}</td>
						<td>
							<img src="${c.foto}" class="img-thumbnail" alt="foto">
							<a href="${c.foto}" target="_blank">Pulsa para ver</a>
						</td>
						<!-- <td class="align-middle">
						
							< // Se pasa el id del clásico a editar o eliminar como parámetro en la URL. >
							<a href="insertar-editar?id=${c.id}"><i class="fas fa-edit fa-1x mx-2 align-middle" title="Editar"></i></a>
							<-- El evento confirmar ejecuta un script de JavaScript para confirmar la eliminación de un modelo. ->
							<a href="eliminar?id=${c.id}" onclick="confirmar('${c.modelo}')"><i class="fas fa-trash fa-1x mx-2" title="Eliminar"></i></a>
						</td> -->
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</main>

<%@include file="../../includes/footer.jsp"%>
