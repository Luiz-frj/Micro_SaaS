<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Criar Serviço</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/includes/navbar.jsp"/>
    <div>
        <h2>Criar Novo Serviço</h2>
        <form action="logged.do?action=newServico" method="post" enctype="multipart/form-data">
            <div class="mb-3">
	            <label for="name"class="form-label">Nome do Serviço:</label>
	            <input type="text" class="form-control"id="name" name="name" required>
			</div>
			<div class="mb-3">
	            <label for="description"class="form-label">Descrição:</label>
	            <textarea class="form-control"id="description" name="description" required></textarea>
			</div>
			<div class="mb-3">
	            <label for="address"class="form-label">Local:</label>
	            <input type="text" class="form-control"id="address" name="address" required>
            </div>
            <div class="mb-3">
	            <label for="servico_time"class="form-label">Tempo de Servico:</label>
	            <input type="time" class="form-control"id="servico_time" name="servico_time" required>
            </div>
            <div class="mb-3">
	            <label for="especialidade"class="form-label">Especialidade:</label>
	            <input type="text" class="form-control"id="especialidade" name="especialidade">
			</div>
			<div class="mb-3">
            	<label for="images"class="form-label">Imagens do Serviço:</label>
            	<input type="file" class="form-control"id="images" name="images" accept="image/*" multiple required>
            	<p class="note">Você pode selecionar até 5 imagens.</p>
            </div>
            <h2 class="mb-4">Disponibilidade Semanal</h2>
            <table class="table table-bordered text-center align-middle">
	            <thead class="table-light">
	                <tr>
	                    <th>Dia da Semana</th>
	                    <th>Início</th>
	                    <th>Término</th>
	                    <th>Descanso (Início)</th>
	                    <th>Descanso (Fim)</th>
	                </tr>
	            </thead>
	            <tbody>
	                <% String[] dias = { "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo" }; 
	                   for (int i = 0; i < dias.length; i++) { 
	                %>
	                    <tr>
	                        <td><%= dias[i] %></td>
	                        <td><input type="time" class="form-control" name="inicio_<%= i %>"></td>
	                        <td><input type="time" class="form-control" name="fim_<%= i %>"></td>
	                        <td><input type="time" class="form-control" name="almoco_inicio_<%= i %>"></td>
	                        <td><input type="time" class="form-control" name="almoco_fim_<%= i %>"></td>
	                    </tr>
	                <% } %>
	            </tbody>
	        </table>

            <button type="submit">Cadastrar Serviço</button>
        </form>
    </div>

    <script>
        // Limitar seleção a 5 arquivos
        document.getElementById("imagens").addEventListener("change", function () {
            if (this.files.length > 5) {
                alert("Você pode enviar no máximo 5 imagens.");
                this.value = "";
            }
        });
    </script>

</body>
</html>
