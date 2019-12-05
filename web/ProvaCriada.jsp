<%@ page import="dominio.UsuarioMT" %><% UsuarioMT.idenficarUsuario(request, response); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Prova criada com sucesso</title>
</head>
<body>
    <h2>Prova criada com sucesso</h2>
    <br>
    <button type="button" name="back" onclick="history.back()">Criar outra prova</button>
</body>
</html>
