<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<br>
<form action="/dominio/UsuarioMT" method="post">
    Matricula:<br>
    <input type="text" name="matricula" required><br>
    Senha:<br>
    <input type="password" name="senha" required>
    <br>
    <% String erro = (String) request.getAttribute("erro");
    if(erro != null && erro.equals("1")){ %>
        <div style="color:red">Login ou senha incorreta</div>
    <% }%>
    <br>
    <input type="submit" value="Logar">    <input type="button" onclick="location.href='/PaginaInicial.jsp';" value="Sou visitante">
    <input type="hidden" name="acao" value="1">
</form>
<input type="button" onclick="location.href='/CadastrarUsuario.jsp';" value="Criar Usuário">

</body>
</html>
