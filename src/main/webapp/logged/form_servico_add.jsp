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
            <label for="name">Nome do Serviço:</label>
            <input type="text" id="name" name="name" required>

            <label for="description">Descrição:</label>
            <textarea id="description" name="description" required></textarea>

            <label for="address">Local:</label>
            <input type="text" id="address" name="address" required>
            
            <label for="servico_time">Tempo de Servico:</label>
            <input type="time" id="servico_time" name="servico_time" required>
            
            <label for="especialidade">Especialidade:</label>
            <input type="text" id="especialidade" name="especialidade">

            <label for="images">Imagens do Serviço:</label>
            <input type="file" id="images" name="images" accept="image/*" multiple required>
            <p class="note">Você pode selecionar até 5 imagens.</p>
            
            <h2 class="mb-4">Disponibilidade Semanal</h2>
            <table class="table table-bordered text-center align-middle">
	            <thead class="table-light">
	                <tr>
	                    <th>Dia da Semana</th>
	                    <th>Início</th>
	                    <th>Término</th>
	                    <th>Almoço (Início)</th>
	                    <th>Almoço (Fim)</th>
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
