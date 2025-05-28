<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="br.edu.ifsp.MicroSaaS.model.Servico" %>
<%@ page import="br.edu.ifsp.MicroSaaS.model.Prestador" %>
<%@ page import="java.util.List" %>
<%
    Servico servico = (Servico) request.getAttribute("servico");
	Prestador prestador = (Prestador) request.getAttribute("prestador");
	List<String> images = (List<String>) request.getAttribute("images");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container py-5">
    <div class="card shadow-lg">
        <div class="row g-0">
            <div class="col-md-5">
                <img src="<%= request.getContextPath() %>/uploads/<%= images.get(0) %>" class="img-fluid rounded-start" alt="Imagem do Serviço">
            </div>
            <div class="col-md-7">
                <div class="card-body">
                    <h3 class="card-title mb-3"><%= servico.getName()%></h3>
                    <p class="card-text"><%= servico.getDescription()%></p>
                    <p class="card-text"><strong>Local:</strong> <%= servico.getLocal() %></p>
                    <p class="card-text"><strong>Responsável:</strong> <%= prestador.getName()%></p>
                    <a href="listar_servicos.jsp" class="btn btn-secondary mt-3">Voltar para lista</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>