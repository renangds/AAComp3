<%@ page import="java.sql.ResultSet" %>
<%@ page import="dominio.CompeticaoMT" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Competições</title>
</head>
<body>
<h2 style="text-align: center">Sistema SISFARJ</h2>
<b>Pontuação Final das Associacões</b><br><br>
<table border="1px">
    <thead>
    <tr>
        <th>Nome</th>
        <th>Sigla</th>
        <th>Pontuacao</th>
    </tr>
    </thead>
    <tbody>
    <%
        ResultSet res = (ResultSet)request.getAttribute("pontuacao");
        if(!res.isClosed()){
            while(res.next()){
    %>
    <tr>
        <td><%=res.getString("nome") %></td>
        <td><%=res.getString("sigla") %></td>
        <td><%=res.getString("pontos") %></td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>

<a href="/PaginaInicial.jsp">Voltar para página inicial</a>
</body>
</html>
