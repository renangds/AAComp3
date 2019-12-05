package testes.testesUnitarios;

import org.junit.jupiter.api.Test;
import testes.Mocks.MockCompeticao;

import java.sql.SQLException;


public class CompeticaoTesteUnitario {
    @Test
    public void testeCadastrarCompeticaoNull() throws SQLException, ClassNotFoundException {
        MockCompeticao competicaoTeste = new MockCompeticao();
        competicaoTeste.cadastrarCompeticao("testeCompeticao", "");
        competicaoTeste.validateFail();
    }
    @Test
    public void testeUpdateLocalNull() throws SQLException, ClassNotFoundException {
        MockCompeticao competicaoTeste = new MockCompeticao();
        competicaoTeste.updateLocal("testeCompeticao", "");
        competicaoTeste.validateFail();
    }

    @Test
    public void testeAlterarCompeticaoDadosNull() {
        MockCompeticao competicaoTeste = new MockCompeticao();
        competicaoTeste.alterarCompeticaoDados("testeCompeticao", "", "");
        competicaoTeste.validateFail();
    }
    @Test
    public void testeListarCompeticoes() throws SQLException {
        MockCompeticao competicaoTeste = new MockCompeticao();
        competicaoTeste.listarCompeticoes();
        competicaoTeste.validateSuccess();
    }
    @Test
    public void testeGetDadosCompeticao() throws SQLException, ClassNotFoundException {
        MockCompeticao competicaoTeste = new MockCompeticao();
        competicaoTeste.getDadosCompeticao("testeCompeticao");
        competicaoTeste.validateSuccess();
    }
    @Test
    public void testeGetDadosCompeticaoNull() throws SQLException, ClassNotFoundException {
        MockCompeticao competicaoTeste = new MockCompeticao();
        competicaoTeste.getDadosCompeticao("");
        competicaoTeste.validateFail();
    }
}
