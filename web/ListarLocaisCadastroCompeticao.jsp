<%@ page import="dominio.UsuarioMT" %><% UsuarioMT.idenficarUsuario(request, response); %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="dominio.LocalMT"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Criar Provas</title>
</head>
<body>
<h2 style="text-align: center">Sistema SISFARJ</h2>
<b>Criar Prova para a competição</b><br><br>
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

<form action="/dominio/ProvaMT" method="post">
    Nome do local: <br><input type="input" name="nome_local"><br>
    Nome da prova: <br><input type="input" name="nome_prova"><br>
    Classe: <br><input type="input" name="classe"><br>
    Categoria: <br><input type="input" name="categoria"><br>
    <input type="hidden" name="nome_competicao" value="<%=request.getParameter("nome")%>">
    <input type="hidden" name="acao" value="3">
    <br><input type="submit" value="Enviar">
</form>

<a href="/PaginaInicial.jsp">Voltar para página inicial</a>
</body>
</html>
