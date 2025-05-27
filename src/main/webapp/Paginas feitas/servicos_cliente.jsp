<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Serviços Disponíveis - AgendaFlex</title>
  <link rel="icon" href="assets/MicroSaasLogo.png" type="image/png">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet">
</head>
<body>
  <div class="container py-5">
    <h2 class="mb-4 text-center">Serviços Disponíveis</h2>
    <div class="row">
      <!-- Card de serviço -->
      <div class="col-md-4 mb-4">
        <div class="card h-100">
          <img src="<%= request.getContextPath() %>/img/servico_exemplo.jpg" class="card-img-top" alt="Imagem Serviço">
          <div class="card-body">
            <h5 class="card-title">Barbearia do João</h5>
            <p class="card-text">Corte de cabelo, barba e sobrancelha.</p>
            <a href="agendar.jsp?id_servico=1" class="btn btn-primary w-100">Agendar Horário</a>
          </div>
        </div>
      </div>
      <!-- Repetir para mais serviços -->
    </div>
  </div>
</body>
</html>
