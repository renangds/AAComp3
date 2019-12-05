<%@ page import="dominio.UsuarioMT" %><% UsuarioMT.idenficarUsuario(request, response); %>
<%@ page import="dominio.AssociacaoMT" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="dominio.UsuarioMT" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listar Associacao</title>
</head>
<script>

</script>
<body>
<h2 style="text-align: center">Sistema SISFARJ</h2>
<b>Lista de associações</b><br><br>
<table border="1px">
    <thead>
    <tr>
        <th>Matrícula</th>
        <th>Nome da associação</th>
    </tr>
    </thead>
    <tbody>
    <%
        ResultSet res = AssociacaoMT.listarAssociacao();
        if(!res.isClosed()){
            while(res.next()){
    %>
    <tr>
        <td><%=res.getString("matricula") %></td>
        <td><%=res.getString("nome") %></td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
<br>
<form action="/dominio/AssociacaoMT" method="post">
    Escreva uma matricula:<br>
    <input type="text" name="matricula">
    <input type="submit" value="Enviar">
    <input type="hidden" name="acao" value="2">
</form>
<br><br>
<a href="/PaginaInicial.jsp">Voltar para página inicial</a>
</body>
</html>
