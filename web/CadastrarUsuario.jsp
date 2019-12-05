<%@ page import="dominio.UsuarioMT" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<br>
<form action="/dominio/UsuarioMT" method="post">
    Matricula:<br>
    <input type="text" name="matricula" required><br>
    Senha:<br>
    <input type="password" name="senha" required><br><br>
    Selecione o nível do usuário:<br>
    <select name="nivel_acesso">
        <option value="2">Secretário</option>
        <option value="3">Diretor técnico</option>
    </select>
    <br><br>
    <input type="submit" value="Cadastrar">
    <input type="hidden" name="acao" value="2">
</form>
<br><br>
<a href="/">Voltar para página de login</a>

</body>
</html>
