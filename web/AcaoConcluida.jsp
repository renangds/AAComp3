<%@ page import="dominio.UsuarioMT" %><% UsuarioMT.idenficarUsuario(request, response); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ação Concluída</title>
</head>
<body>
    <h2>Ação concluída com sucesso</h2>
    <br>
    <input type="button" onclick="location.href='/PaginaInicial.jsp';" value="Voltar para página inicial" />
</body>
</html>
