<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Agendar Serviço - AgendaFlex</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
</head>
<body>
  <div class="container py-5">
    <h2 class="mb-4 text-center">Agendar Serviço</h2>
    <div class="form-container">
      <form action="<%= request.getContextPath() %>/AgendamentoServlet" method="post">
        <input type="hidden" name="id_servico" value="<%= request.getParameter("id_servico") %>">

        <div class="mb-3">
          <label for="nome" class="form-label">Seu Nome</label>
          <input type="text" class="form-control" id="nome" name="nome" required>
        </div>

        <div class="mb-3">
          <label for="data" class="form-label">Data</label>
          <input type="date" class="form-control" id="data" name="data" required>
        </div>

        <div class="mb-3">
          <label for="hora" class="form-label">Hora</label>
          <input type="time" class="form-control" id="hora" name="hora" required>
        </div>

        <button type="submit" class="btn btn-primary w-100">Confirmar Agendamento</button>
      </form>
    </div>
  </div>
</body>
</html>