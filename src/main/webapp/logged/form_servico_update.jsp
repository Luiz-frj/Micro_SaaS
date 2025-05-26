<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Criar Serviço</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 40px;
        }

        .container {
            max-width: 600px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #333;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }

        input[type="text"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-top: 6px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
            font-size: 14px;
        }

        textarea {
            resize: vertical;
            min-height: 100px;
        }

        input[type="file"] {
            margin-top: 8px;
        }

        .note {
            font-size: 12px;
            color: #777;
        }

        button {
            margin-top: 25px;
            width: 100%;
            padding: 12px;
            background-color: #4CAF50;
            border: none;
            color: white;
            font-size: 16px;
            border-radius: 6px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

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
