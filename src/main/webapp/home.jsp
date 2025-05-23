<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>AgendaFlex</title>
  <link rel="icon" href="<%= request.getContextPath() %>/img/favicon.png" type="image/png">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1>Bem-vindo</h1>
        <a href="front.do?action=formLoginCliente" class="btn">Painel do Cliente</a>
        <a href="front.do?action=formLoginPrestador" class="btn">Painel do Prestador</a>
    </div>
</body>
</html>