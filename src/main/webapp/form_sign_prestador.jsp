<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="cadastro" method="post" enctype="multipart/form-data">
		Nome: <input type="text" name="name"><br> <br>
		Usuário: <input type="text" name="user"><br> <br>
		Email: <input type="email" name="email"><br> <br>
		CPF: <input type="text" name="cpf"><br> <br>
		Foto: <input type="file" name="foto" accept="image/png, image/jpeg"><br> <br>
		Senha: <input type="password" name="password"><br> <br>
		Telefone: <input type="text" name="phone"><br> <br>
		<input type="submit" value="Cadastrar">
	</form>
</body>
</html>