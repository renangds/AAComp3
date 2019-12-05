<%@ page import="dominio.UsuarioMT" %><% UsuarioMT.idenficarUsuario(request, response); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dados Lançados</title>
</head>
<body>
    <h2>Dados lançados com sucesso</h2>
    <br>
    <button type="button" name="back" onclick="history.back()">Lançar novos dados</button>
</body>
</html>
