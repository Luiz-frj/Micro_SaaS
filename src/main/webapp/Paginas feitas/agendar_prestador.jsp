<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Agendamentos - AgendaFlex</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
</head>
<body>
  <div class="container py-5">
    <h2 class="mb-4">Seus Agendamentos</h2>
    <table class="table table-bordered">
      <thead class="table-dark">
        <tr>
          <th>Cliente</th>
          <th>Serviço</th>
          <th>Data e Hora</th>
          <th>Status</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <!-- Exemplo de linha -->
        <tr>
          <td>João da Silva</td>
          <td>Corte de Cabelo</td>
          <td>28/05/2025 14:00</td>
          <td>Ativo</td>
          <td>
            <button class="btn btn-success btn-sm">Aceitar</button>
            <button class="btn btn-danger btn-sm">Recusar</button>
          </td>
        </tr>
        <!-- Linhas serão populadas dinamicamente -->
      </tbody>
    </table>
  </div>
</body>
</html>