package testes.testesFuncionais;

import dados.CompeticaoPA;
import dominio.CompeticaoMT;
import exceptions.DadoNaoExisteException;
import exceptions.ExceptionDadosIncompletos;
import exceptions.JaExisteException;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompeticaoTesteFuncional {
    CompeticaoPA GatewayCompeticao = new CompeticaoPA();
    @Test
    public void testeCadastrarCompeticao() throws SQLException, ClassNotFoundException {
        boolean sucesso = false;
        try {
            CompeticaoMT.cadastrarCompeticao("testeCompeticao", "testeCompeticao");
        } catch (ExceptionDadosIncompletos exceptionDadosIncompletos) {
            sucesso = false;
        }catch (JaExisteException e){
            sucesso = false;
        }
        ResultSet res = CompeticaoMT.listarCompeticoes();
        while(res.next()){
            if(res.getString("nome").equals("testeCompeticao")) {
                sucesso = true;
                break;
            }
        }
        assertTrue(sucesso);
    }
    @Test
    public void testeUpdateLocal() throws SQLException, ClassNotFoundException {
        boolean sucesso = false;
        try {
            CompeticaoMT.updateLocal("testeCompeticao", "teste");
        } catch (ExceptionDadosIncompletos exceptionDadosIncompletos) {
            sucesso = false;
        }catch (DadoNaoExisteException e){
            sucesso = false;
        }
        ResultSet res = GatewayCompeticao.buscarCompeticaoLocal("testeCompeticao");
        while(res.next()){
            if(res.getString("nomelocal").equals("teste")) {
                sucesso = true;
                break;
            }
        }
        assertTrue(sucesso);
    }
    @Test
    public void testeAlterarCompeticao() throws SQLException, ClassNotFoundException {
        boolean sucesso = false;
        try {
            CompeticaoMT.alterarCompeticaoDados("testeCompeticao","testeCompeticaoAlterar", "testeCompeticao");
        } catch (ExceptionDadosIncompletos exceptionDadosIncompletos) {
            sucesso = false;
        }
        ResultSet res = CompeticaoMT.listarCompeticoes();
        while(res.next()){
            if(res.getString("nome").equals("testeCompeticao")) {
                sucesso = true;
                break;
            }
        }
        assertTrue(sucesso);
    }
}
