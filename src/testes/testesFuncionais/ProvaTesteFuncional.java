package testes.testesFuncionais;

import dados.AtletaProvaPA;
import dominio.ProvaMT;
import exceptions.*;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProvaTesteFuncional {
    AtletaProvaPA GatewayAtletaProva = new AtletaProvaPA();
    @Test
    public void testeCadastrarProva() throws SQLException, ClassNotFoundException {
        boolean sucesso = false;
        try {
            try {
                ProvaMT.cadastrarProva("testeProva", "testeProva", "testeProva", "testeCompeticaoAlterar");
            } catch (JaExisteException e) {
                sucesso = false;
            } catch (ClassNotFoundException e) {
                sucesso = false;
            }
            if(GatewayAtletaProva.findAtletaProva("testeProva", "testeProva").next() == true){
                sucesso = false;
            }
        } catch (ExceptionDadosIncompletos exceptionDadosIncompletos) {
            sucesso = false;
        }
        ResultSet res = null;
        try {
            res = ProvaMT.getProvasCompeticao("testeCompeticaoAlterar");
        } catch (DadoNaoExisteException e) {
            sucesso = false;
        } catch (ClassNotFoundException e) {
            sucesso = false;
        }
        while (res.next()) {
            if (res.getString("nome").equals("testeProva")) {
                sucesso = true;
                break;
            }
        }
        assertTrue(sucesso);
    }
    @Test
    public void testeInscreverAtletaProva() throws ClassNotFoundException, SQLException, MatriculaInvalidaException {
        boolean sucesso = false;
        try {
            ProvaMT.inscreverAtletaProva("teste", "testeProva");
        } catch (ExceptionDadosIncompletos exceptionDadosIncompletos) {
            sucesso = false;
        }catch(AtletaJaInscritoEmProvaException AtletaJaInscritoEmProvaException){
            sucesso = false;
        }
        ResultSet res = null;
        try {
            res = ProvaMT.getProvasCompeticao("testeCompeticaoAlterar");
        } catch (DadoNaoExisteException e) {
            e.printStackTrace();
        }
        while (res.next()) {
            if (res.getString("nome").equals("testeProva")) {
                sucesso = true;
                break;
            }
        }
        assertTrue(sucesso);
    }
}