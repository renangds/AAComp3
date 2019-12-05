package dominio;

import java.sql.ResultSet;
import dados.AtletaPA;
import dados.AtletaProvaPA;
import dados.CompeticaoPA;
import dados.ProvaPA;
import java.sql.SQLException;
import exceptions.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProvaMT", urlPatterns = {"/dominio/ProvaMT"})
public class ProvaMT extends HttpServlet {

    private static CompeticaoPA GatewayCompeticao= new CompeticaoPA();
    private static ProvaPA GatewayProva= new ProvaPA();
    private static AtletaProvaPA GatewayAtletaProva= new AtletaProvaPA();
    private static AtletaPA GatewayAtleta= new AtletaPA();

    public static ResultSet getProvasCompeticao(String nome) throws DadoNaoExisteException, SQLException, ClassNotFoundException {
        if(GatewayCompeticao.buscarCompeticaoDados(nome).next() == false){
            throw new DadoNaoExisteException();
        }
        return GatewayProva.buscarProvasporCompeticao(nome);
    }

    public static void cadastrarProva(String nome, String classe, String categoria, String nome_competicao) throws ExceptionDadosIncompletos, JaExisteException, SQLException, ClassNotFoundException {
        if(GatewayProva.buscarProva(nome).next() == true){
            throw new JaExisteException();
        } else if(nome.isEmpty() | classe.isEmpty() | categoria.isEmpty()){
            throw new ExceptionDadosIncompletos();
        } else{
            GatewayProva.inserir(nome, classe, categoria, nome_competicao);
        }
    }

    public static void inscreverAtletaProva(String matricula_atleta, String nome_prova) throws SQLException, ClassNotFoundException, ExceptionDadosIncompletos, MatriculaInvalidaException, AtletaJaInscritoEmProvaException{
        if(GatewayAtleta.buscarAtleta(matricula_atleta).next() == false){
            throw new MatriculaInvalidaException();
        }
        else if(matricula_atleta.isEmpty() | nome_prova.isEmpty()){
            throw new ExceptionDadosIncompletos();
        }
        else if(GatewayAtletaProva.findAtletaProva(matricula_atleta,nome_prova).next() == true){
            throw new AtletaJaInscritoEmProvaException();
        }
        else{
            GatewayAtletaProva.cadastrarAtletaProva(matricula_atleta,nome_prova);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int acao;
        ResultSet resultSet = null;
        try{
            acao = Integer.parseInt(request.getParameter("acao"));
        } catch (NumberFormatException e){
            acao = 0;
        }

        if(acao == 1| acao == 4 | acao == 5 | acao == 6){
            try {
                resultSet = getProvasCompeticao(request.getParameter("nome"));
            } catch (DadoNaoExisteException e) {
                request.setAttribute("dado", "Competição informada");
                request.getRequestDispatcher("/ExcecaoDadoNaoExiste.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        switch(acao) {
            case 1:
                request.setAttribute("prova", resultSet);
                request.getRequestDispatcher("/InscreverAtletaListaProvas.jsp").forward(request, response);
            case 2:
                try {
                    inscreverAtletaProva(request.getParameter("matricula_atleta"),
                            request.getParameter("nome_prova"));
                }catch (ExceptionDadosIncompletos e){
                    request.getRequestDispatcher("/ExcecaoDadosIncompletos.jsp").forward(request, response);
                }catch (SQLException e){
                    e.printStackTrace();
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                }catch (MatriculaInvalidaException e){
                    request.getRequestDispatcher("/ExcecaoMatriculaInvalida.jsp").forward(request, response);
                }catch (AtletaJaInscritoEmProvaException e){
                    request.getRequestDispatcher("/AtletaJaInscritoEmProva.jsp").forward(request, response);
                }
                request.getRequestDispatcher("/DadosLancadosSucesso.jsp").forward(request, response);
            case 3:
                try {
                    cadastrarProva(request.getParameter("nome_prova"),
                            request.getParameter("classe"),
                            request.getParameter("categoria"),
                            request.getParameter("nome_competicao"));
                    CompeticaoMT.updateLocal(request.getParameter("nome_competicao"),
                            request.getParameter("nome_local"));
                }catch (ExceptionDadosIncompletos e){
                    request.getRequestDispatcher("/ExcecaoDadosIncompletos.jsp").forward(request, response);
                }catch (SQLException e){
                    e.printStackTrace();
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                } catch (JaExisteException e) {
                    request.setAttribute("dado", "Nome de prova");
                    request.getRequestDispatcher("/ExcecaoJaExiste.jsp").forward(request, response);
                } catch (DadoNaoExisteException e) {
                    request.setAttribute("dado", "Local informado");
                    request.getRequestDispatcher("/ExcecaoDadoNaoExiste.jsp").forward(request, response);
                }
                request.getRequestDispatcher("/ProvaCriada.jsp").forward(request, response);
            case 4:
                request.setAttribute("id", "1");
                request.setAttribute("prova", resultSet);
                request.getRequestDispatcher("/ListaProva.jsp").forward(request, response);
            case 5:
                request.setAttribute("id", "3");
                request.setAttribute("prova", resultSet);
                request.getRequestDispatcher("/ListaProva.jsp?").forward(request, response);
            case 6:
                request.setAttribute("id", "4");
                request.setAttribute("prova", resultSet);
                request.getRequestDispatcher("/ListaProva.jsp?").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
