<%-- agendar.jsp --%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Agendar - AgendaFlex</title>
  <link rel="icon" href="<%= request.getContextPath() %>/img/favicon.png" type="image/png">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
</head>
<body>
  <div class="container py-5">
    <h2 class="text-center">Agende com João Silva</h2>
    <form class="mt-4" style="max-width: 500px; margin: 0 auto;">
      <div class="mb-3">
        <label for="nome" class="form-label">Seu Nome</label>
        <input type="text" class="form-control" id="nome" required>
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">Seu E-mail</label>
        <input type="email" class="form-control" id="email" required>
      </div>
      <div class="mb-3">
        <label for="data" class="form-label">Data</label>
        <input type="date" class="form-control" id="data" required>
      </div>
      <div class="mb-3">
        <label for="hora" class="form-label">Hora</label>
        <input type="time" class="form-control" id="hora" required>
      </div>
      <button type="submit" class="btn btn-primary w-100">Agendar</button>
    </form>
  </div>
</body>
</html>