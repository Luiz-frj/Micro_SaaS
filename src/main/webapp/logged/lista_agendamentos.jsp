<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="br.edu.ifsp.MicroSaaS.model.Agendamento" %>
<%@ page import="br.edu.ifsp.MicroSaaS.model.Servico" %>
<%
	HashMap<Agendamento, Servico> agendamentos = (HashMap<Agendamento, Servico>) request.getAttribute("agendamentos");
	boolean is_prestador = (boolean) request.getAttribute("is_prestador");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Meus Agendamentos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<jsp:include page="/includes/navbar.jsp"/>

<div class="container py-5">
    <h2 class="mb-4">Meus Agendamentos</h2>

    <%
        if (agendamentos != null && !agendamentos.isEmpty()) {
    %>
        <table class="table table-bordered table-hover align-middle text-center">
            <thead class="table-light">
                <tr>
                    <th>Serviço</th>
                    <th>Data</th>
                    <th>Horário</th>
                    <th>Status</th>
                    <%if (is_prestador) { %>
                    	<th>Ações Prestador</th>
                    <%} %>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
            <% for (Agendamento ag : agendamentos.keySet()) { %>
                <tr>
                    <td><%= agendamentos.get(ag).getName() %></td>
                    <td><%= ag.getData().toString() %></td>
                    <td><%= ag.getHorario() %></td>
                    <td>
                        <span class="badge 
                            <%= ag.getStatus_agendamento() == 1 ? "bg-success" : 
                            	ag.getStatus_agendamento() > 1 ? "bg-danger" : "bg-warning text-dark" %>">
                            <%= ag.getTrueStatusAgendamento() %>
                        </span>
                    </td>
                    <% if (is_prestador) {%>
	                    <td>
	                        <% if (ag.getStatus_agendamento() == 0) { %>
	                        <form action="logged.do?action=updateStatusAgendamento&agendamento=<%=ag.getId() %>&to=1" method="post" style="margin: 0;">
	                            <button type="submit" class="btn btn-sm btn-danger">Aceitar</button>
	                        </form>
	                        <% }%>
	                        <% if (ag.getStatus_agendamento() == 1) { %>
	                        <form action="logged.do?action=updateStatusAgendamento&agendamento=<%=ag.getId() %>&to=2" method="post" style="margin: 0;">
	                            <button type="submit" class="btn btn-sm btn-danger">Finalizar</button>
	                        </form>
	                        <% }%>
	                    </td>
                    <%} else {%>
                     	<% if (ag.getStatus_agendamento() <= 1) { %>
		                    <td>
		                        <form action="logged.do?action=updateStatusAgendamento&agendamento=<%=ag.getId() %>&to=3" method="post" style="margin: 0;">
		                            <button type="submit" class="btn btn-sm btn-danger">Cancelar</button>
		                        </form>
		                    </td>
                   		<%} %>
                   <%} %>
                </tr>
            <% } %>
            </tbody>
        </table>
    <%
        } else {
    %>
        <div class="alert alert-info">Você ainda não possui nenhum agendamento.</div>
    <%
        }
    %>
</div>

</body>
</html>
