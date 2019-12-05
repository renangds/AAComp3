package testes.testesFuncionais;

import dominio.LocalMT;
import exceptions.ExceptionDadosIncompletos;
import exceptions.JaExisteException;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalTesteFuncional {
    @Test
    public void testeCadastrarLocal() throws SQLException, ClassNotFoundException {
        boolean sucesso = false;
        try {
            try {
                LocalMT.cadastrarLocal("testeLocal", "testeLocal", "50");
            } catch (JaExisteException e) {
                sucesso = false;
            }
        }catch (ExceptionDadosIncompletos exceptionDadosIncompletos) {
            sucesso = false;
        }
        ResultSet res = LocalMT.listarLocais();
        while(res.next()){
            if(res.getString("nomeLocal").equals("testeLocal")) {
                sucesso = true;
                break;
            }
        }
        assertTrue(sucesso);
    }
    @Test
    public void testeSetNewLocalData() throws SQLException, ClassNotFoundException {
        boolean sucesso = false;
        try {
            LocalMT.setNewLocalData("testeLocal", "SetNewLocalData", "50");
        }catch (ExceptionDadosIncompletos exceptionDadosIncompletos) {
            sucesso = false;
        }
        ResultSet res = LocalMT.listarLocais();
        while(res.next()){
            if(res.getString("logradouro").equals("SetNewLocalData")) {
                sucesso = true;
                break;
            }
        }
        assertTrue(sucesso);
    }
}
