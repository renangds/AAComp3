package testes.testesFuncionais;

import dominio.AssociacaoMT;
import exceptions.DadoNaoExisteException;
import exceptions.ExceptionDadosIncompletos;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssociacaoTesteFuncional {
    @Test
    public void testeCadastrarAssociacao() throws SQLException, ClassNotFoundException {
        boolean sucesso = false;
        try {
            AssociacaoMT.cadastrarAssociacao("testeAssociacao", "testeAssociacao", "testeAssociacao", "testeAssociacao", "testeAssociacao", "testeAssociacao", "testeAssociacao");
        }catch (ExceptionDadosIncompletos exceptionDadosIncompletos) {
            sucesso = false;
        }
        ResultSet res = AssociacaoMT.listarAssociacao();
        while(res.next()){
            if(res.getString("nome").equals("testeAssociacao")) {
                sucesso = true;
                break;
            }
        }
        assertTrue(sucesso);
    }
    @Test
    public void testeListarAsssociacaoOrdenado() throws SQLException {
        boolean sucesso = false;
        ResultSet res = AssociacaoMT.listarAssociacao();
        ArrayList<String> nomes = new ArrayList<String>();
        while(res.next()) {
            nomes.add(res.getString("nome"));
        }
        Collections.sort(nomes);
        int i = 0;
        res.beforeFirst();
        while(res.next()){
            if(!res.getString("nome").equals(nomes.get(i))) {
                sucesso = false;
                break;
            }
            i++;
            sucesso = true;
        }
        assertTrue(sucesso);
    }
    @Test
    public void testeAlterarAssociacaoDados() throws ClassNotFoundException, SQLException {
        boolean sucesso = false;
        try {
            AssociacaoMT.alterarAssociacaoDados("testeAssociacaoAlterar","testeAssociacaoAlterar", "testeAssociacaoAlterar", "testeAssociacaoAlterar", "testeAssociacaoAlterar", "teste");
        } catch (ExceptionDadosIncompletos exceptionDadosIncompletos) {
            sucesso = false;
        }catch (DadoNaoExisteException DadoNaoExisteException){
            sucesso = false;
        }
        ResultSet res = AssociacaoMT.listarAssociacao();
        while(res.next()){
            if(res.getString("nome").equals("testeAssociacaoAlterar")) {
                sucesso = true;
                break;
            }
        }
        assertTrue(sucesso);
    }
}
