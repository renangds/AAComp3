<%@ page import="dominio.UsuarioMT" %><% UsuarioMT.idenficarUsuario(request, response); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>><%=request.getAttribute("dado")%> já existe</title>
</head>
<body>
    <h2><%=request.getAttribute("dado")%> já existe</h2>
    <br>
    <button type="button" name="back" onclick="history.back()">voltar</button>
</body>
</html>
