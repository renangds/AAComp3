<%@ page import="dominio.UsuarioMT" %><% UsuarioMT.idenficarUsuario(request, response); %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="dominio.CompeticaoMT" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Competições</title>
</head>
<body>
<h2 style="text-align: center">Sistema SISFARJ</h2>
<b>Lista de Competições</b><br><br>
<table border="1px">
    <thead>
    <tr>
        <th>Nome</th>
        <th>Data</th>
    </tr>
    </thead>
    <tbody>
    <%
        ResultSet res = CompeticaoMT.listarCompeticoes();
        if(!res.isClosed()){
            while(res.next()){
    %>
    <tr>
        <td><%=res.getString("nome") %></td>
        <td><%=res.getString("data") %></td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>

<br>
<form action="/dominio/ProvaMT" method="post">
    Escreva o nome da competicao:<br>
    <input type="text" name="nome">
    <input type="submit" value="Enviar">
    <input type="hidden" name="acao" value="1">
</form>
<br><br>

<a href="/PaginaInicial.jsp">Voltar para página inicial</a>
</body>
</html>
