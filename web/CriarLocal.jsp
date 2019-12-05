<%@ page import="dominio.UsuarioMT" %><% UsuarioMT.idenficarUsuario(request, response); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Novo Local de Competição</title>
</head>
<body>
    <form action="/dominio/LocalMT" method="post">
        Nome Local: <br><input type="input" name="nome_local"><br>
        Endereço: <br><input type="input" name="endereco"><br>
        <br>Tamanho da Piscina:<br>
        <input type="radio" name="piscina" value="50" checked>50m<br>
        <input type="radio" name="piscina" value="25" checked>25m<br>
        <input type="hidden" name="acao" value="1">
        <br><input type="submit" value="Enviar">
    </form>
    <br><br>
    <input type="hidden" name="acao" value="1">
    <a href="/PaginaInicial.jsp">Voltar para página inicial</a>
</body>
</html>