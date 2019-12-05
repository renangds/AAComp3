package testes.testesUnitarios;

import org.junit.jupiter.api.Test;
import testes.Mocks.MockAssociacao;

import java.sql.SQLException;


public class AssociacaoTesteUnitario {
    @Test
    public void testeCadastrarAssociacaoNull(){
        MockAssociacao associacaoTeste = new MockAssociacao();
        associacaoTeste.cadastrarAssociacao("testeAssociacao", "testeAssociacao", "testeAssociacao", "", "", "", "testeAssociacao");
        associacaoTeste.validateFail();
    }
    @Test
    public void testeAlterarAssociacaoDadosNull(){
        MockAssociacao associacaoTeste = new MockAssociacao();
        associacaoTeste.alterarAssociacaoDados("testeAssociacao", "testeAssociacao", "testeAssociacao", "", "", "", "testeAssociacao", "teste");
        associacaoTeste.validateFail();
    }
    @Test
    public void testeAlterarAssociacaoMatriculaInvalida(){
        MockAssociacao associacaoTeste = new MockAssociacao();
        associacaoTeste.alterarAssociacaoDados("testeAssociacao", "testeAssociacao", "testeAssociacao", "testeAssociacao", "testeAssociacao", "testeAssociacao", "testeAssociacao", "matriculaInvalida");
        associacaoTeste.validateFail();
    }
    @Test
    public void testeGetDadosAssociacao(){
        MockAssociacao associacaoTeste = new MockAssociacao();
        associacaoTeste.getDadosAssociacao("matriculaInvalida");
        associacaoTeste.validateFail();
    }
}