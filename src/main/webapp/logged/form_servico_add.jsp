<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Criar Serviço</title>
</head>
<body>

    <div class="container">
        <h2>Criar Novo Serviço</h2>
        <form action="logged.do?action=newServico" method="post" enctype="multipart/form-data">
            <label for="name">Nome do Serviço:</label>
            <input type="text" id="name" name="name" required>

            <label for="description">Descrição:</label>
            <textarea id="description" name="description" required></textarea>

            <label for="address">Local:</label>
            <input type="text" id="address" name="address" required>

            <label for="images">Imagens do Serviço:</label>
            <input type="file" id="images" name="images" accept="image/*" multiple required>
            <p class="note">Você pode selecionar até 5 imagens.</p>

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
