package dominio;

import dados.AssociacaoPA;
import dados.UsuarioPA;
import exceptions.DadoNaoExisteException;
import exceptions.ExceptionDadosIncompletos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

@WebServlet(name = "AssociacaoMT", urlPatterns = {"/dominio/AssociacaoMT"})
public class AssociacaoMT extends HttpServlet {

    private static AssociacaoPA GatewayAssociacao = new AssociacaoPA();
    private static UsuarioPA GatewayUsuario= new UsuarioPA();


    public static ResultSet listarAssociacao() {
        try {
            return GatewayAssociacao.buscarTodasAssociacoes();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static void cadastrarAssociacao(String numero_oficio, String data, String nome, String sigla, String endereco, String telefone, String comprovante_pagamento) throws SQLException, ClassNotFoundException, ExceptionDadosIncompletos {
        if(numero_oficio.isEmpty() | data.isEmpty() | nome.isEmpty() | sigla.isEmpty() | endereco.isEmpty() | telefone.isEmpty() | comprovante_pagamento.isEmpty()){
            throw new ExceptionDadosIncompletos();
        }else {
            String matricula, senha;
            senha = Integer.toString(new Random().nextInt(999999999) + 10000000);
            while(GatewayAssociacao.buscarAssociacao(matricula = Integer.toString(new Random().nextInt(999999999) + 1)).next()){}
            GatewayAssociacao.inserir(numero_oficio, data, nome, sigla, endereco, telefone, comprovante_pagamento, matricula, senha);
            GatewayUsuario.inserir(matricula, senha, "1");
        }
    }

    public static ResultSet getDadosAssociacao(String matricula) throws SQLException, ClassNotFoundException, DadoNaoExisteException {
        if(GatewayAssociacao.buscarAssociacao(matricula).next() == false)
            throw new DadoNaoExisteException();
        else {
            return GatewayAssociacao.buscarAssociacao(matricula);
        }
    }

    public static void alterarAssociacaoDados(String numero_oficio, String data, String nome, String sigla, String senha, String matricula) throws ExceptionDadosIncompletos, DadoNaoExisteException,SQLException, ClassNotFoundException {
        if(GatewayAssociacao.buscarAssociacao(matricula).next() == false){
            throw new DadoNaoExisteException();
        }
        else if(numero_oficio.isEmpty() | data.isEmpty() | nome.isEmpty() | sigla.isEmpty() | senha.isEmpty() | matricula.isEmpty()){
            throw new ExceptionDadosIncompletos();
        }else {
            GatewayAssociacao.update(numero_oficio, data, nome, sigla, senha, matricula);
            GatewayUsuario.updateSenha(matricula,senha);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int acao;
        try{
            acao = Integer.parseInt(request.getParameter("acao"));
        } catch (NumberFormatException e){
            acao = 0;
        }

        switch(acao){
            case 1:
                try {
                    cadastrarAssociacao(request.getParameter("numero_oficio"),
                            request.getParameter("data_oficio"),
                            request.getParameter("nome"),
                            request.getParameter("sigla"),
                            request.getParameter("endereco"),
                            request.getParameter("telefone"),
                            request.getParameter("comprovante_pagamento"));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (ExceptionDadosIncompletos exceptionDadosIncompletos) {
                    request.getRequestDispatcher("/ExcecaoDadosIncompletos.jsp").forward(request, response);
                }
                request.getRequestDispatcher("/AcaoConcluida.jsp").forward(request, response);
            case 2:
                ResultSet resultSet = null;
                try {
                    resultSet = getDadosAssociacao(request.getParameter("matricula"));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (DadoNaoExisteException e) {
                    request.setAttribute("dado", "Matrícula da Associação");
                    request.getRequestDispatcher("/ExcecaoDadoNaoExiste.jsp").forward(request, response);
                }
                request.setAttribute("associacao", resultSet);
                request.getRequestDispatcher("/AlterarAssociacaoDados.jsp").forward(request, response);
            case 3:
                try {
                    alterarAssociacaoDados(request.getParameter("numero_oficio"),
                            request.getParameter("data_oficio"),
                            request.getParameter("nome"),
                            request.getParameter("sigla"),
                            request.getParameter("senha"),
                            request.getParameter("matricula"));
                } catch (ExceptionDadosIncompletos exceptionDadosIncompletos) {
                    request.getRequestDispatcher("/ExcecaoDadosIncompletos.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (DadoNaoExisteException e) {
                    request.getRequestDispatcher("/ExcecaoDadoNaoExiste.jsp").forward(request, response);
                }
                request.getRequestDispatcher("/AcaoConcluida.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}