<%@page import="ppi.locadora.dao.*"%>
<%@page import="ppi.locadora.model.Carro"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Locadora de carros</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">


<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
	integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
	integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
	integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
	crossorigin="anonymous"></script>




</head>
<body>


	<%@include file="cabecalhoFuncionario.jsp"%>



	<br>
	<h3 class="page-header text-center">Carros 
	</h3>
	<br>

	<div class="container">

		<div class="table-responsive">
			<table class="table">
				<thead class="thead-inverse">
					<tr>

						<th>Modelo</th>
						<th>Categoria</th>
						<th>Renavan</th>
						<th>Ano fabricação</th>
						<th>Tarifa dia</th>
						<th>Editar</th>
						<th>Excluir</th>
					</tr>

				</thead>

				<tbody>

            <form action="ControllerServlet" method="post" >
					<c:forEach var="carro" items="${carros}">
						<tr>

							<td>${carro.modelo}</td>
							<td>${carro.categoria}</td>
							<td>${carro.renavan }</td>
							<td>${carro.anoFabricacao }</td>
							<td>${carro.tarifaDia }</td>
							
							<td>
							<input type="hidden" name="modelo" id="modelo" value="${carro.modelo }" />
						    <input type="hidden" name="anofabricacao" value="${carro.anoFabricacao }" />
							<input type="hidden" name="combo"  value="${carro.categoria }" />
							<input type="hidden" name="renavan"  value="${carro.renavan}" />
							<input type="hidden" name="tarifa"  value="${carro.tarifaDia}" />
							<input type="hidden" name="tipo"  value="1" />
						
							<input type="hidden" id="logica" name="logica" value="AlterarCarro" />

							<button type="submit" onclick="formSubmit()" id="button" class="btn btn-outline-warning" >Editar</button>
							</td>
							
							
							<td>
							<input type="hidden" id="logica" name="logica" value="RemoverCarro" />
							
							<input type="hidden" name="renavan"  value="${carro.renavan}" />
							<button type="submit" class="btn btn-outline-danger" id="button">Apagar</button>
							</td>
							
							
						</tr>
					</c:forEach>

					</form>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>