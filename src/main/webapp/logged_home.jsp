<%-- painel.jsp --%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Painel - AgendaFlex</title>
  <link rel="icon" href="<%= request.getContextPath() %>/img/favicon.png" type="image/png">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
</head>
<body>
  <div class="container py-5">
    <h2 class="mb-4">Painel do Profissional</h2>
    <div class="mb-4">
      <h5>Próximos Agendamentos</h5>
      <ul class="list-group">
        <li class="list-group-item">João - 21/05/2025 às 10:00</li>
        <li class="list-group-item">Maria - 22/05/2025 às 14:00</li>
      </ul>
    </div>
    <div>
    <a href="logged.do?action=logoff">Sair da conta</a>
      <h5>Cadastrar Novo Horário</h5>
      <form class="row g-3">
        <div class="col-md-6">
          <label for="data" class="form-label">Data</label>
          <input type="date" class="form-control" id="data">
        </div>
        <div class="col-md-6">
          <label for="hora" class="form-label">Hora</label>
          <input type="time" class="form-control" id="hora">
        </div>
        <div class="col-12">
          <button class="btn btn-primary" type="submit">Salvar Horário</button>
        </div>
      </form>
    </div>
  </div>
</body>
</html>