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

<%if(request.getParameter("id").equals("4")){%>
<form action="/dominio/CompeticaoMT" method="post">
    <br>
    Escreva o nome da competição:<br>
    <input type="text" name="nome">
    <input type="submit" value="Enviar">
    <input type="hidden" name="acao" value="4">
    <br>
        <%}else if(request.getParameter("id").equals("5")){%>
    <form action="/dominio/CompeticaoMT" method="post">
        Escreva o nome da competição que deseja alterar:<br>
        <input type="text" name="nome">
        <input type="submit" value="Enviar">
        <input type="hidden" name="acao" value="2">
    </form>
<%}else{%>
<form action="/dominio/ProvaMT" method="post">
    <br>
    Escreva o nome da competição que deseja ver as provas:<br>
    <input type="text" name="nome">
    <input type="submit" value="Enviar">
    <% if(request.getParameter("id").equals("2")){
    %>
        <input type="hidden" name="acao" value="5">
    <% }else if(request.getParameter("id").equals("3")){%>
        <input type="hidden" name="acao" value="6">
    <% }else{ %>
        <input type="hidden" name="acao" value="4">
    <% } %>
</form>
<%}%>

<a href="/PaginaInicial.jsp">Voltar para página inicial</a>
</body>
</html>
