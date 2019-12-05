package testes.testesUnitarios;

import org.junit.jupiter.api.Test;
import testes.Mocks.MockLocal;

import java.sql.SQLException;



public class LocalTesteUnitario {
    @Test
    public void testeCadastrarLocalNull() throws SQLException, ClassNotFoundException {
        MockLocal testeLocal = new MockLocal();
        testeLocal.cadastrarLocal("testeLocal", "testeLocal", "");
        testeLocal.validateFail();
    }
    @Test
    public void testeCadastrarLocal() throws SQLException, ClassNotFoundException {
        MockLocal testeLocal = new MockLocal();
        testeLocal.cadastrarLocal("testeLocal", "testeLocal", "testeLocal");
        testeLocal.validateFail();
    }
    @Test
    public void testeSetNewLocalDataNull(){
        MockLocal testeLocal = new MockLocal();
        testeLocal.setNewLocalData("testeLocal", "testeLocal", "");
        testeLocal.validateFail();
    }
    @Test
    public void testeGetDadosLocalNull() throws SQLException, ClassNotFoundException {
        MockLocal testeLocal = new MockLocal();
        testeLocal.getDadosLocal("");
        testeLocal.validateFail();
    }
    @Test
    public void testeListarLocais() throws SQLException {
        MockLocal testeLocal = new MockLocal();
        testeLocal.listarLocais();
        testeLocal.validateSuccess();
    }
}
