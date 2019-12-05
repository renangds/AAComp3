package testes.Mocks;

import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MockAtletaProva {
    private static boolean processCalled;
    static String nome_prova ;
    static String matricula_atleta;
    static int ponto;
    static String tempo;
    public static ResultSet getAtletasProva(String nome_prova){
        if(MockAtletaProva.nome_prova != nome_prova){
            processCalled = false;
        }
        else
            processCalled = true;
        return null;
    }
    public static void pontuarAtleta(String nome_prova,String matricula_atleta, String tempo){
        if(MockAtletaProva.matricula_atleta != matricula_atleta){
            processCalled = false;
        }
        else if(matricula_atleta.isEmpty() | tempo.isEmpty()){
            processCalled = false;
        }
        else
            processCalled = true;
    }
    public void validateSuccess(){
        assertTrue(processCalled);
    }
    public void validateFail(){
        assertFalse(processCalled);
    }
}

