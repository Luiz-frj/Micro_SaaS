<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<title>Lista de Usuários Cadastrados</title>
<link rel="stylesheet" href="css/estilo.css">
</head>
<body>
	<form action="cadastro" method="post" enctype="multipart/form-data">
		Nome: <input type="text" name="name"><br> <br>
		Usuário: <input type="text" name="user"><br> <br>
		Email: <input type="email" name="email"><br> <br>
		Senha: <input type="password" name="password"><br> <br>
		Telefone: <input type="text" name="phone"><br> <br>
		<input type="submit" value="Cadastrar">
	</form>
</body>
</html>