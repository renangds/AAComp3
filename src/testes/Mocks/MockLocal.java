package testes.Mocks;

import dados.LocalPA;
import dominio.LocalMT;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MockLocal extends LocalMT {
    private static boolean processCalled;
    static String nomelocal;
    static String logradouro;
    static String piscina;

    static LocalPA GatewayLocal = new LocalPA();
    public static ResultSet listarLocais() throws SQLException {
        try{
            processCalled = true;
            GatewayLocal.buscarTodosLocais();
        } catch(ClassNotFoundException e){
            processCalled = false;
        }
        return null;
    }

    public static void setNewLocalData(String nomelocal, String logradouro, String piscina){
        if(logradouro.isEmpty() | piscina.isEmpty()){
            processCalled = false;
        } else{
            processCalled = true;
        }
    }

    public static ResultSet getDadosLocal(String nomeLocal) throws SQLException, ClassNotFoundException {
        if(GatewayLocal.buscarLocal(nomeLocal).next() == false){
            processCalled = false;
        }
        else
            processCalled = true;
        return null;
    }

    public static void cadastrarLocal(String nomeLocal, String logradouro, String piscina) throws SQLException, ClassNotFoundException{
        if(GatewayLocal.buscarLocal(nomeLocal).next() == true){
            processCalled = false;
        } else if(nomeLocal.isEmpty() | logradouro.isEmpty() | piscina == null){
            processCalled = false;
        } else{
            MockLocal.nomelocal = nomeLocal;
            MockLocal.logradouro = logradouro;
            MockLocal.piscina = piscina;
            processCalled = true;
        }
    }
    public void validateSuccess(){
        assertTrue(processCalled);
    }
    public void validateFail(){
        assertFalse(processCalled);
    }
}
