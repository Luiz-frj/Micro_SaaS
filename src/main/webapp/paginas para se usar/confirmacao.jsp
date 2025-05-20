<%-- confirmacao.jsp --%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Confirmado - AgendaFlex</title>
  <link rel="icon" href="<%= request.getContextPath() %>/img/favicon.png" type="image/png">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
</head>
<body>
  <div class="container text-center py-5">
    <h2 class="text-success">Agendamento Confirmado!</h2>
    <p class="lead">Você receberá um e-mail com os detalhes.</p>
    <a href="index.jsp" class="btn btn-primary mt-3">Voltar para Início</a>
  </div>
</body>
</html>