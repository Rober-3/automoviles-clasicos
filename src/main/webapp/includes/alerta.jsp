<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Para mostrar diferentes mensajes de alerta hay que cambiar dos valores: el tipo de alerta (alert, success,... 
y el mensaje en si. Se podría hacer pasando dos atributos del controlador a la jsp y hacer el cambio de manera
dinámica, pero lo mejor será encapsularlos en un objeto a través de una clase. Para ello se creará un pojo que
no será un pojo del modelo, ya que no se usará en la BBDD, si no que será un pojo de los controladores. -->

<!-- c:if evita que se muestren el área y el botón de la alerta estando vacía-->
<c:if test="${not empty alerta}">
<div class>
	<div class="alert alert-${alerta.tipo} alert-dismissible fade show text-center" role="alert">
		${alerta.texto}
		
		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		
	</div>
</div>
</c:if>
