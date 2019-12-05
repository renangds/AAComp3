<%@ page import="dominio.UsuarioMT" %><% UsuarioMT.idenficarUsuario(request, response); %>
<%@ page import="dominio.AtletaMT" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listar Atletas para Transferência</title>
</head>
<script>

</script>
<body>
<h2 style="text-align: center">Sistema SISFARJ</h2>
<b>Lista de Atletas</b><br><br>
<table border="1px">
    <thead>
    <tr>
        <th>Matrícula</th>
        <th>Nome</th>
        <th>Matrícula da Associação</th>
    </tr>
    </thead>
    <tbody>
    <%
        ResultSet res = AtletaMT.listarAtletas();
        if(!res.isClosed()){
            while(res.next()){
    %>
    <tr>
        <td><%=res.getString("matricula") %></td>
        <td><%=res.getString("nome") %></td>
        <td><%=res.getString("matricula_associacao") %></td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
<br>
<form action="/dominio/AtletaMT" method="post">
    Escreva uma matricula:<br>
    <input type="text" name="matricula">
    <input type="submit" value="Enviar">
    <input type="hidden" name="acao" value="4">
</form>
<br><br>
<a href="/PaginaInicial.jsp">Voltar para página inicial</a>
</body>
</html>
