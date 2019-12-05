<%@ page import="dominio.UsuarioMT" %><%@ page import="dominio.UsuarioMT" %><% UsuarioMT.idenficarUsuario(request, response); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Atleta Já Inscrito Em Prova</title>
</head>
<body>
    <h2>O Atleta já se encontra inscrito na prova</h2>
    <br>
    <button type="button" name="back" onclick="history.back()">back</button>
</body>
</html>
