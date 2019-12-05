<%@ page import="dominio.UsuarioMT" %><% UsuarioMT.idenficarUsuario(request, response); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dados incompletos</title>
</head>
<body>
    <h2>Dados incompletos</h2>
    <br>
    <button type="button" name="back" onclick="history.back()">voltar</button>
</body>
</html>
