<%@ page import="dominio.UsuarioMT" %><% UsuarioMT.idenficarUsuario(request, response); %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="dominio.LocalMT"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Locais de Competição</title>
</head>
<body>
<h2 style="text-align: center">Sistema SISFARJ</h2>
<b>Lista de Locais</b><br><br>
<table border="1px">
    <thead>
    <tr>
        <th>Nome Local</th>
        <th>Endereço</th>
        <th>Tamanho Piscina</th>
    </tr>
    </thead>
    <tbody>
    <%
        ResultSet res = LocalMT.listarLocais();
        if(!res.isClosed()){
            while(res.next()){
    %>
    <tr>
        <td><%=res.getString("nomelocal") %></td>
        <td><%=res.getString("logradouro") %></td>
        <td><%=res.getString("piscina") %></td>
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
