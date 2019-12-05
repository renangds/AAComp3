<%@ page import="dominio.UsuarioMT" %><% UsuarioMT.idenficarUsuario(request, response); %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Provas</title>
</head>
<body>
<h2 style="text-align: center">Sistema SISFARJ</h2>
<b>Lista de Provas</b><br><br>
<table border="1px">
    <thead>
    <tr>
        <th>Nome</th>
        <th>Categoria</th>
        <th>Classe</th>
    </tr>
    </thead>
    <tbody>
    <%
        ResultSet res = (ResultSet)request.getAttribute("prova");
        if(!res.isClosed()){
            while(res.next()){
    %>
    <tr>
        <td><%=res.getString("nome") %></td>
        <td><%=res.getString("categoria") %></td>
        <td><%=res.getString("classe") %></td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>

<% if(request.getAttribute("id").equals("3")){
%>
<form action="/dominio/AtletaProvaMT" method="post">
    <br>
    Escreva o nome da prova que deseja ver os resultados:<br>
    <input type="text" name="nome">
    <input type="submit" value="Enviar">
    <input type="hidden" name="acao" value="3">
</form>
<% } else if(request.getAttribute("id").equals("4")){%>
<form action="/dominio/AtletaProvaMT" method="post">
    <br>
    Escreva o nome da prova para ver a pontuação:<br>
    <input type="text" name="nome">
    <input type="submit" value="Enviar">
    <input type="hidden" name="acao" value="4">
</form>
<% } else{%>
<form action="/dominio/AtletaProvaMT" method="post">
    <br>
    Escreva o nome da prova para inserir os resultados:<br>
    <input type="text" name="nome">
    <input type="submit" value="Enviar">
    <input type="hidden" name="acao" value="1">
</form>
<% } %>
<a href="/PaginaInicial.jsp">Voltar para página inicial</a>
</body>
</html>
