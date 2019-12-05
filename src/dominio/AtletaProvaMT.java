package dominio;

import dados.AtletaProvaPA;
import exceptions.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "AtletaProvaMT", urlPatterns = {"/dominio/AtletaProvaMT"})
public class AtletaProvaMT extends HttpServlet {

    private static AtletaProvaPA GatewayAtletaProva= new AtletaProvaPA();

    public static ResultSet getAtletasProva(String nome_prova) throws DadoNaoExisteException, SQLException, ClassNotFoundException {
        if(GatewayAtletaProva.findAtletasProva(nome_prova).next() == false){
            throw new DadoNaoExisteException();
        }
        else
            return GatewayAtletaProva.findAtletasProva(nome_prova);
    }

    public static void pontuarAtleta(String nome_prova,String matricula_atleta, String tempo) throws ExceptionDadosIncompletos, DadoNaoExisteException, SQLException, ClassNotFoundException, TempoInvalidoException {
        if(GatewayAtletaProva.findAtletaProva(matricula_atleta,nome_prova).next() == false){
            throw new DadoNaoExisteException();
        }
        else if(matricula_atleta.isEmpty() | tempo.isEmpty()){
            throw new ExceptionDadosIncompletos();
        }
        else if(!tempo.matches("\\d{2}:\\d{2}.\\d{2}")){
            throw new TempoInvalidoException();
        }
        else if(tempo.equals("00:00.00")){
            tempo = "WO";
        }
        GatewayAtletaProva.inserirTempo(nome_prova,matricula_atleta,tempo);
        ResultSet res = GatewayAtletaProva.findPosicoes(nome_prova);
        for(int i = 1; res.next() != false; i++) {
            if(!res.getString("tempo").equals("WO") | !res.getString("tempo").isEmpty()){
                if(i == 1) GatewayAtletaProva.inserirPonto(nome_prova,res.getString("matricula_atleta"), "28");
                if(i == 2) GatewayAtletaProva.inserirPonto(nome_prova,res.getString("matricula_atleta"), "24");
                if(i == 3) GatewayAtletaProva.inserirPonto(nome_prova,res.getString("matricula_atleta"), "20");
                if(i == 4) GatewayAtletaProva.inserirPonto(nome_prova,res.getString("matricula_atleta"), "16");
                if(i == 5) GatewayAtletaProva.inserirPonto(nome_prova,res.getString("matricula_atleta"), "15");
                if(i == 6) GatewayAtletaProva.inserirPonto(nome_prova,res.getString("matricula_atleta"), "14");
                if(i == 7) GatewayAtletaProva.inserirPonto(nome_prova,res.getString("matricula_atleta"), "13");
                if(i == 8) GatewayAtletaProva.inserirPonto(nome_prova,res.getString("matricula_atleta"), "12");
                if(i == 9) GatewayAtletaProva.inserirPonto(nome_prova,res.getString("matricula_atleta"), "09");
                if(i == 10) GatewayAtletaProva.inserirPonto(nome_prova,res.getString("matricula_atleta"), "07");
                if(i == 11) GatewayAtletaProva.inserirPonto(nome_prova,res.getString("matricula_atleta"), "06");
                if(i == 12) GatewayAtletaProva.inserirPonto(nome_prova,res.getString("matricula_atleta"), "05");
                if(i == 13) GatewayAtletaProva.inserirPonto(nome_prova,res.getString("matricula_atleta"), "04");
                if(i == 14) GatewayAtletaProva.inserirPonto(nome_prova,res.getString("matricula_atleta"), "03");
                if(i == 15) GatewayAtletaProva.inserirPonto(nome_prova,res.getString("matricula_atleta"), "02");
                if(i == 16) GatewayAtletaProva.inserirPonto(nome_prova,res.getString("matricula_atleta"), "01");
                if(i > 16) GatewayAtletaProva.inserirPonto(nome_prova,res.getString("matricula_atleta"), "00");
                }
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

        if(acao == 1 | acao == 3 | acao == 4){
            try {
                resultSet = getAtletasProva(request.getParameter("nome"));
            } catch (DadoNaoExisteException e) {
                request.setAttribute("dado", "Prova informada");
                request.getRequestDispatcher("/ExcecaoDadoNaoExiste.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        switch(acao) {
            case 1:
                request.setAttribute("nome_prova", request.getParameter("nome"));
                request.setAttribute("atleta", resultSet);
                request.getRequestDispatcher("/ListaAtletas.jsp?id=1").forward(request, response);
            case 2:
                try {
                    pontuarAtleta(request.getParameter("nome_prova"),
                            request.getParameter("matricula_atleta"),
                            request.getParameter("tempo"));
                }catch (ExceptionDadosIncompletos e){
                    request.getRequestDispatcher("/ExcecaoDadosIncompletos.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (DadoNaoExisteException e) {
                    request.setAttribute("dado", "Matricula de atleta");
                    request.getRequestDispatcher("/ExcecaoDadoNaoExiste.jsp").forward(request, response);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (TempoInvalidoException e) {
                    request.getRequestDispatcher("/ExcecaoTempoInvalido.jsp").forward(request, response);
                }
                request.getRequestDispatcher("/DadosLancadosSucesso.jsp").forward(request, response);
            case 3:
                request.setAttribute("nome_prova", request.getParameter("nome"));
                request.setAttribute("atleta", resultSet);
                request.getRequestDispatcher("/ListaAtletas.jsp?id=3").forward(request, response);
            case 4:
                request.setAttribute("atleta", resultSet);
                request.getRequestDispatcher("/ListaAtletas.jsp?id=4").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
