<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String servico = (String) request.getAttribute("servico");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Agendar Serviço - AgendaFlex</title>
  <link rel="icon" href="assets/MicroSaasLogo.png" type="image/png">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/includes/navbar.jsp"/>
  <div class="container py-5">
    <h2 class="mb-4 text-center">Agendar Serviço</h2>
    <div class="form-container">
      <form action="logged.do?action=newAgendamento&servico=<%=servico %>" method="post">
        <input type="hidden" name="id_servico" value="<%= request.getParameter("id_servico") %>">
        
        <div class="mb-3">
          <label for="date" class="form-label">Data</label>
          <input type="date" class="form-control" id="date" name="date" required>
        </div>

        <div class="mb-3">
          <label for="time" class="form-label">Hora</label>
          <input type="time" class="form-control" id="time" name="time" required>
        </div>

        <button type="submit" class="btn btn-primary w-100">Confirmar Agendamento</button>
      </form>
    </div>
  </div>
</body>
</html>
