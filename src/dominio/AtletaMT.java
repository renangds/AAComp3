package dominio;

import java.sql.ResultSet;
import java.util.Random;
import dados.AssociacaoPA;
import dados.AtletaPA;
import java.sql.SQLException;
import exceptions.DadoNaoExisteException;
import exceptions.ExceptionDadosIncompletos;
import exceptions.MatriculaInvalidaException;
import exceptions.MesmaMatriculaException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AtletaMT", urlPatterns = {"/dominio/AtletaMT"})
public class AtletaMT extends HttpServlet {

    private static AtletaPA GatewayAtleta= new AtletaPA();
    private static AssociacaoPA GatewayAssociacao= new AssociacaoPA();

    public static ResultSet listarAtletas() {
        try {
            return GatewayAtleta.buscarTodosAtletas();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void alterarAtletaDados(String nome, String numero, String data_entrada, String data_oficio, String matricula) throws ExceptionDadosIncompletos, SQLException, ClassNotFoundException {
        if(nome.isEmpty() | numero.isEmpty() | data_entrada.isEmpty() | data_oficio.isEmpty() | matricula.isEmpty()){
            throw new ExceptionDadosIncompletos();
        }else {
            GatewayAtleta.update(nome, numero, data_entrada, data_oficio, matricula);
        }
    }

    public static void transferirAtleta(String numero, String data_oficio, String comprovante, String data_entrada, String matricula, String matricula_associacao) throws ExceptionDadosIncompletos, SQLException, ClassNotFoundException, MatriculaInvalidaException, MesmaMatriculaException {
        ResultSet result = GatewayAtleta.buscarAtleta(matricula);
        result.next();
        if(GatewayAssociacao.buscarAssociacao(matricula_associacao).next() == false){
            throw new MatriculaInvalidaException();
        }
        else if(result.getString("matricula_associacao").equals(matricula_associacao)){
            throw new MesmaMatriculaException();
        }
        else if(comprovante.isEmpty() | numero.isEmpty() | data_entrada.isEmpty() | data_oficio.isEmpty() | matricula.isEmpty() | matricula_associacao.isEmpty() ){
            throw new ExceptionDadosIncompletos();
        }else {
            GatewayAtleta.transferir(numero, data_oficio, comprovante, data_entrada, matricula_associacao, matricula);
        }
    }

    public static ResultSet getDadosAtleta(String matricula) throws SQLException, ClassNotFoundException, DadoNaoExisteException {
        if(GatewayAtleta.buscarAtleta(matricula).next() == false){
            throw new DadoNaoExisteException();
        }
        else {
            return GatewayAtleta.buscarAtletaDados(matricula);
        }
    }

    public static void cadastrarAtleta(String nome, String numero, String data_entrada, String data_oficio, String data_nascimento, String comprovante, String matricula_associacao) throws SQLException, ClassNotFoundException, ExceptionDadosIncompletos, MatriculaInvalidaException {
        if(nome.isEmpty() | numero.isEmpty() | data_entrada.isEmpty() | data_oficio.isEmpty() | data_nascimento.isEmpty() | comprovante.isEmpty() | matricula_associacao.isEmpty()){
            throw new ExceptionDadosIncompletos();
        }else if(GatewayAssociacao.buscarAssociacao(matricula_associacao).next() == false){
            throw new MatriculaInvalidaException();
        }else {
            String matricula;
            while(GatewayAtleta.buscarAtleta(matricula = Integer.toString(new Random().nextInt(999999999) + 1)).next()){}
            System.out.println(matricula);
            GatewayAtleta.inserir(nome, numero, data_entrada, data_oficio, data_nascimento, comprovante, matricula_associacao, matricula);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int acao;
        ResultSet resultSet = null;
        try{
            acao = Integer.parseInt(request.getParameter("acao"));
        } catch (NumberFormatException e){
            acao = 0;
        }

        switch(acao){
            case 1:
                try {
                    cadastrarAtleta(request.getParameter("nome"),
                            request.getParameter("numero"),
                            request.getParameter("data_entrada"),
                            request.getParameter("data_oficio"),
                            request.getParameter("data_nascimento"),
                            request.getParameter("comprovante"),
                            request.getParameter("matricula_associacao"));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (ExceptionDadosIncompletos exceptionDadosIncompletos) {
                    request.getRequestDispatcher("/ExcecaoDadosIncompletos.jsp").forward(request, response);
                } catch (MatriculaInvalidaException e) {
                    request.getRequestDispatcher("/ExcecaoMatriculaInvalida.jsp").forward(request, response);
                }
                request.getRequestDispatcher("/AcaoConcluida.jsp").forward(request, response);
            case 2:
                try {
                    resultSet = getDadosAtleta(request.getParameter("matricula"));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (DadoNaoExisteException e) {
                    request.setAttribute("dado", "Matrícula de Atleta");
                    request.getRequestDispatcher("/ExcecaoDadoNaoExiste.jsp").forward(request, response);
                }
                request.setAttribute("atleta", resultSet);
                request.getRequestDispatcher("/AlterarAtletaDados.jsp").forward(request, response);
            case 3:
                try {
                    alterarAtletaDados(request.getParameter("nome"),
                            request.getParameter("numero"),
                            request.getParameter("data_entrada"),
                            request.getParameter("data_oficio"),
                            request.getParameter("matricula"));
                } catch (ExceptionDadosIncompletos exceptionDadosIncompletos) {
                    request.getRequestDispatcher("/ExcecaoDadosIncompletos.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                request.getRequestDispatcher("/AcaoConcluida.jsp").forward(request, response);
            case 4:
                try {
                    resultSet = getDadosAtleta(request.getParameter("matricula"));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (DadoNaoExisteException e) {
                    request.setAttribute("dado", "Matrícula de Atleta");
                    request.getRequestDispatcher("/ExcecaoDadoNaoExiste.jsp").forward(request, response);
                }
                request.setAttribute("atleta", resultSet);
                request.getRequestDispatcher("/TransferirAtletaDados.jsp").forward(request, response);
            case 5:
                try {
                    transferirAtleta(request.getParameter("numero"),
                            request.getParameter("data_oficio"),
                            request.getParameter("comprovante_pagamento"),
                            request.getParameter("data_entrada"),
                            request.getParameter("matricula"),
                            request.getParameter("matricula_associacao"));
                } catch (ExceptionDadosIncompletos exceptionDadosIncompletos) {
                    request.getRequestDispatcher("/ExcecaoDadosIncompletos.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (MatriculaInvalidaException e){
                    request.getRequestDispatcher("/ExcecaoMatriculaInvalida.jsp").forward(request, response);
                } catch (MesmaMatriculaException e) {
                    request.getRequestDispatcher("/ExcecaoMesmaMatricula.jsp").forward(request, response);
                }
                request.getRequestDispatcher("/AcaoConcluida.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

