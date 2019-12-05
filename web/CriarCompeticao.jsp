<%@ page import="dominio.UsuarioMT" %><%@ page import="dominio.UsuarioMT" %><% UsuarioMT.idenficarUsuario(request, response); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Criar Nova Competição</title>
</head>
<body>
    <form action="/dominio/CompeticaoMT" method="post">
        Nome: <br><input type="input" name="nome"><br>
        Data: <br><input type="input" name="data"><br>
        <input type="hidden" name="acao" value="1">
        <br><input type="submit" value="Enviar">
    </form>
    <br><br>
    <a href="/PaginaInicial.jsp">Voltar para página inicial</a>
</body>
</html>