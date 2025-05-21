<%-- cadastro.jsp --%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Cadastro Prestador - AgendaFlex</title>
  <link rel="icon" href="<%= request.getContextPath() %>/img/favicon.png" type="image/png">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
</head>
<body>
  <div class="container form-container">
    <h2 class="text-center mb-4">Criar Conta</h2>
    <form action="front.do?action=signPrestador" method="post" enctype="multipart/form-data">
      <div class="mb-3">
        <label for="nome" class="form-label">Nome</label>
        <input type="text" class="form-control" name="name" id="name" required>
      </div>
      <div class="mb-3">
        <label for="username" class="form-label">Usu�rio</label>
        <input type="text" class="form-control" name="username" id="username" required>
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">E-mail</label>
        <input type="email" class="form-control" name="email" id="email" required>
      </div>
      <div class="mb-3">
        <label for="cpf" class="form-label">CPF</label>
        <input type="text" class="form-control" name="cpf" id="cpf" required>
      </div>
      <div class="mb-3">
        <label for="foto" class="form-label">Foto</label>
        <input type="file" class="form-control" name="foto" id="foto" accept="image/png, image/jpeg" required>
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
