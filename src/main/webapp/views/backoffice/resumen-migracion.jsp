<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Resumen del proceso de migración</h1>

<p>Fichero leído: ${fichero}</p>
<p>Tiempo (ms): ${tiempo}</p>
<p>Número de líneas leídas: ${leidas}</p>
<p>Número de líneas insertadas: ${insertadas}</p>
<p>Número de líneas erróneas: ${erroneas}</p>
<!-- También se puede usar la longitud del array para saber el número de líneas (ver el proyecto de Ander). -->
<br>

<h3>Líneas erróneas</h3>
<c:forEach items="${lineasErroneas}" var="e">
	${e} <br>
</c:forEach>

<p>${mensaje}</p>
