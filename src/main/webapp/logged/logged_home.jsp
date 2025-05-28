<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.edu.ifsp.MicroSaaS.model.Servico" %>
<%@ page import="java.util.HashMap" %>
<%
    HashMap<Servico, String> servicos = (HashMap<Servico, String>) request.getAttribute("servico_list");
	System.out.println(servicos);
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Painel - AgendaFlex</title>
  <link rel="icon" href="assets/MicroSaasLogo.png" type="image/png">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/includes/navbar.jsp"/>

    <div>
        <h2>Serviços Disponíveis</h2>
        <%
            if (servicos != null && !servicos.isEmpty()) {
                for (Servico s : servicos.keySet()) {
        %>
        	<a href="logged.do?action=servicoDetails&servico=<%= s.getId() %>">
	            <div>
	                <img src="<%= request.getContextPath() %>/uploads/<%= servicos.get(s) %>" alt="Imagem do serviço">
	                <div>
	                    <div><%= s.getName() %></div>
	                    <div><%= s.getDescription() %></div>
	                    <div>Local: <%= s.getLocal() %></div>
	                </div>
	            </div>
            </a>
        <%
                }
            } else {
        %>
            <p>Nenhum serviço encontrado.</p>
        <%
            }
        %>
</body>
</html>
