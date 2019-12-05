package dominio;

import dados.UsuarioPA;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "UsuarioMT", urlPatterns = {"/dominio/UsuarioMT"})
public class UsuarioMT extends HttpServlet {

    private static UsuarioPA GatewayUsuario= new UsuarioPA();

    public static int idenficarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Cookie cookies[] = request.getCookies();

        if(cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("nivel_acesso")) {
                    return Integer.parseInt(cookies[i].getValue());
                }
            }
        }

        request.getRequestDispatcher("/Login.jsp").forward(request, response);
        return 0;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricula = request.getParameter("matricula");
        String senha = request.getParameter("senha");
        String nivel_acesso = request.getParameter("nivel_acesso");
        int acao = Integer.parseInt(request.getParameter("acao"));
        try {
            switch (acao) {
                case 1:
                    ResultSet res = GatewayUsuario.getDadosUsuario(matricula, senha);
                    if(res.next()){
                        Cookie cookie_acesso = new Cookie("nivel_acesso", res.getString("nivel_acesso"));
                        cookie_acesso.setMaxAge(900);
                        cookie_acesso.setPath("/");
                        response.addCookie(cookie_acesso);
                        response.sendRedirect("/PaginaInicial.jsp");
                    }else{
                        request.setAttribute("erro", "1");
                        request.getRequestDispatcher("/Login.jsp").forward(request, response);
                    }
                    break;
                case 2:
                    GatewayUsuario.inserir(matricula, senha, nivel_acesso);
                    request.getRequestDispatcher("/Login.jsp").forward(request, response);
                    break;
                default:
                    //OPCAO INVALIDA
            }
        } catch (SQLException e) {
            request.getRequestDispatcher("/ExcecaoBancoDeDados.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            request.getRequestDispatcher("/ExcecaoBancoDeDados.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
