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
