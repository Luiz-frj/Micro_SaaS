<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Criar Serviço</title>
</head>
<body>
<jsp:include page="/includes/navbar.jsp"/>
    <div class="container">
        <h2>Criar Novo Serviço</h2>
        <form action="SalvarServicoServlet" method="post" enctype="multipart/form-data">
            <label for="nome">Nome do Serviço:</label>
            <input type="text" id="nome" name="nome" required>

            <label for="descricao">Descrição:</label>
            <textarea id="descricao" name="descricao" required></textarea>

            <label for="local">Local:</label>
            <input type="text" id="local" name="local" required>

            <label for="imagens">Imagens do Serviço:</label>
            <input type="file" id="imagens" name="imagens" accept="image/*" multiple required>
            <p class="note">Você pode selecionar até 5 imagens.</p>

            <button type="submit">Atualizar Serviço</button>
        </form>
    </div>

    <script>
        // Limitar seleção a 5 arquivos
        document.getElementById("imagens").addEventListener("change", function () {
            if (this.files.length > 5) {
                alert("Você pode enviar no máximo 5 imagens.");
                this.value = ""; // limpa os arquivos selecionados
            }
        });
    </script>

</body>
</html>
