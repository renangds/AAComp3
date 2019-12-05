package testes.testesFuncionais;

import dominio.AtletaMT;
import exceptions.DadoNaoExisteException;
import exceptions.ExceptionDadosIncompletos;
import exceptions.MatriculaInvalidaException;
import exceptions.MesmaMatriculaException;
import org.junit.jupiter.api.Test;
import testes.Mocks.MockAssociacao;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AtletaTesteFuncional {
    @Test
    public void testeCadastrarAtleta() throws SQLException, MatriculaInvalidaException, ClassNotFoundException {
        MockAssociacao associacaoMock = new MockAssociacao();
        associacaoMock.cadastrarAssociacao("mockAssociacao", "mockAssociacao", "mockAssociacao", "mockAssociacao", "mockAssociacao", "mockAssociacao", "mockAssociacao");
        boolean sucesso = false;
        try {
            AtletaMT.cadastrarAtleta("testeAtleta", "testeAtleta", "testeAtleta", "testeAtleta", "testeAtleta", "testeAtleta", associacaoMock.getMatricula());
        }catch (ExceptionDadosIncompletos exceptionDadosIncompletos){
            sucesso = false;
        }
        ResultSet res = AtletaMT.listarAtletas();
        while(res.next()){
            if(res.getString("nome").equals("testeAtleta")) {
                sucesso = true;
                break;
            }
        }
        assertTrue(sucesso);
    }
    @Test
    public void testeTransferirAtleta() throws SQLException, ClassNotFoundException, DadoNaoExisteException {
        MockAssociacao associacaoMock = new MockAssociacao();
        associacaoMock.cadastrarAssociacao("mockAssociacao", "mockAssociacao", "mockAssociacao", "mockAssociacao", "mockAssociacao", "mockAssociacao", "mockAssociacao");
        boolean sucesso = false;
        try {
            AtletaMT.transferirAtleta("testeAtletaTransferir", "testeAtletaTransferir", "testeAtletaTransferir", "testeAtletaTransferir", "teste", associacaoMock.getMatricula());
        } catch (ExceptionDadosIncompletos exceptionDadosIncompletos) {
            sucesso = false;
        } catch (MatriculaInvalidaException e) {
            sucesso = false;
        } catch (MesmaMatriculaException e) {
            sucesso = false;
        }
        ResultSet res = AtletaMT.getDadosAtleta("teste");
        while (res.next()) {
            if (res.getString("numero").equals("testeAtletaTransferir")) {
                sucesso = true;
                break;
            }
        }
        assertTrue(sucesso);
    }
}
