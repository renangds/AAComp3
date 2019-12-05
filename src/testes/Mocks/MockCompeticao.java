package testes.Mocks;

import dados.CompeticaoPA;
import dados.LocalPA;
import dominio.CompeticaoMT;
import dominio.LocalMT;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MockCompeticao extends CompeticaoMT {
    private static boolean processCalled;
    static String nome;
    static String data;
    static String nomelocal;

    static CompeticaoPA GatewayCompeticao = new CompeticaoPA();
    static LocalPA GatewayLocal = new LocalPA();

    public static ResultSet listarCompeticoes() throws SQLException {
        try {
            GatewayCompeticao.buscarCompeticaoDados(nome).next();
            processCalled = true;
        } catch (ClassNotFoundException e) {
            processCalled = false;
        }
        return null;
    }

    public static void cadastrarCompeticao(String nome, String data) throws SQLException, ClassNotFoundException{
        if(GatewayCompeticao.buscarCompeticaoDados(nome).next()){
            processCalled = false;
        } else if(nome.isEmpty() | data.isEmpty()){
            processCalled = false;
        }else {
            MockCompeticao.nome = nome;
            MockCompeticao.data = data;
            MockCompeticao.nomelocal = "teste";
            processCalled = true;
        }
    }

    public static void updateLocal(String nome, String local) throws SQLException, ClassNotFoundException {
        if(!GatewayLocal.buscarLocal(local).next()){
            processCalled = false;
        } else if(nome.isEmpty() | local.isEmpty()){
            processCalled = false;
        }else
            processCalled = true;
    }

    public static ResultSet getDadosCompeticao(String nome) throws SQLException, ClassNotFoundException {
        if(!GatewayCompeticao.buscarCompeticaoDados(nome).next()){
            processCalled = false;
        }
        else if(nome.isEmpty())
            processCalled = false;
        else
            processCalled = true;
        return null;
    }

    public static void alterarCompeticaoDados(String nome, String data, String nome_antigo){
        if(nome.isEmpty() | data.isEmpty()){
            processCalled = false;
        }else {
            processCalled = true;
        }
    }

    public static String getNome() {
        return nome;
    }

    public void validateSuccess(){
        assertTrue(processCalled);
    }
    public void validateFail(){
        assertFalse(processCalled);
    }
}
