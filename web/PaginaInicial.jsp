<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sistema SISFARJ</title>
</head>
<body>
<script>
    function deleteAllCookies() {
        var cookies = top.document.cookie.split(";");

        for (var i = 0; i < cookies.length; i++) {
            var cookie = cookies[i];
            var eqPos = cookie.indexOf("=");
            var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
            top.document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
        }
    }
</script>
    <h2 style="text-align: center">Sistema SISFARJ</h2>
    <table>
        <thead>
            <tr>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
        <%
            int nivel_acesso = 0;
            Cookie cookies[] = request.getCookies();
            for(int i=0;i<cookies.length;i++){
                if(cookies[i].getName().equals("nivel_acesso")){
                    nivel_acesso = Integer.parseInt(cookies[i].getValue());
                }
            }
        %>
            <tr><td> Listar balizamento competição </td></tr>
            <tr><td><a class="feito" href="/ListaCompeticao.jsp?id=3">Listar pontuação competição</a></td></tr>
            <tr><td><a class="feito" href="/ListaCompeticao.jsp?id=4">Listar pontuação final</a></td></tr>
        <% if (nivel_acesso >= 2){ %>
            <tr><td><a class="feito" href="/FiliarAssociacao.jsp">Filiar Associação</a></td></tr>
            <tr><td><a class="feito" href="/AlterarAssociacaoLista.jsp">Alterar filiação de associação</a></td></tr>
            <tr><td><a class="feito" href="/ListarAssociacao.jsp">Listar associação</a></td></tr>
            <tr><td><a class="feito" href="/CadastrarAtleta.jsp">Cadastrar atleta</a></td></tr>
            <tr><td><a class="feito" href="/ListaAtletas.jsp?id=5">Listar atletas</a></td></tr>
            <tr><td><a class="feito" href="/ListaAtletas.jsp?id=6">Alterar cadastro de atleta</a></td></tr>
            <tr><td><a class="feito" href="/TransferirAtletaLista.jsp">Transferir atleta</a></td></tr>
        <% } %>
        <% if (nivel_acesso >= 3) {%>
            <tr><td><a class="feito" href="/CriarLocal.jsp">Incluir locais de competição</a></td></tr>
            <tr><td><a class="feito" href="/AlterarLocal.jsp">Alterar locais de competição</a></td></tr>
            <tr><td><a class="feito" href="/ListarLocais.jsp">Listar locais de competição</a></td></tr>
            <tr><td><a class="feito" href="/CriarCompeticao.jsp">Criar competição</a></td></tr>
            <tr><td><a class="feito" href="/ListaCompeticao.jsp?id=5">Alterar competição</a></td></tr>
            <tr><td><a class="feito" href="/ListaCompeticao.jsp?id=1">Inserir resultado do atleta</a></td></tr>
            <tr><td><a class="feito" href="/ListaCompeticao.jsp?id=2">Listar competição</a></td></tr>
        <% } %>
        <% if (nivel_acesso == 1) { %>
            <tr><td><a class="feito" href="/InscreverAtletaListaCompeticoes.jsp">Increver atleta em competição</a></td></tr>
        <% } %>
        </tbody>
    </table>
    <br><br>
    <a href="/" onclick="deleteAllCookies();">Deslogar</a>
</body>
</html>

<style>

.feito{
    font-weight: bold;
    text-decoration: inherit;
}

</style>