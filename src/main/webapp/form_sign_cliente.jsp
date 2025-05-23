<%-- cadastro.jsp --%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Cadastro Cliente - AgendaFlex</title>
  <link rel="icon" href="<%= request.getContextPath() %>/img/favicon.png" type="image/png">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
</head>
<body>
  <div class="container form-container">
    <h2 class="text-center mb-4">Criar Conta</h2>
    <form action="front.do?action=signCliente" method="post" enctype="multipart/form-data">
      <div class="mb-3">
        <label for="nome" class="form-label">Nome</label>
        <input type="text" class="form-control" name="name" id="name" required>
      </div>
      <div class="mb-3">
        <label for="username" class="form-label">Endereco</label>
        <input type="text" class="form-control" name="address" id="address" required>
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">E-mail</label>
        <input type="email" class="form-control" name="email" id="email" required>
      </div>
      <div class="mb-3">
        <label for="senha" class="form-label">Senha</label>
        <input type="password" class="form-control" name="password" id="password" required>
      </div>
      <div class="mb-3">
        <label for="phone" class="form-label">Telefone</label>
        <input type="text" class="form-control" name="phone" id="phone" required>
      </div>
      <button type="submit" class="btn btn-primary w-100">Cadastrar</button>
    </form>
  </div>
</body>
</html>
