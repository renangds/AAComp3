package testes.Mocks;

import dominio.AtletaMT;

import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MockAtleta extends AtletaMT {
    private static boolean processCalled;
    static String nome;
    static String numero;
    static String data_entrada;
    static String data_oficio;
    static String data_nascimento;
    static String comprovante;
    static String matricula;
    static MockAssociacao associacao;

    public static void cadastrarAtleta(String nome, String numero, String data_entrada, String data_oficio, String data_nascimento, String comprovante, MockAssociacao associacao){
        if(comprovante.isEmpty() | numero.isEmpty() | data_entrada.isEmpty() | data_oficio.isEmpty() | associacao == null )
            processCalled = false;
        else if(associacao.getClass() != MockAssociacao.class)
            processCalled = false;
        else {
            MockAtleta.nome = nome;
            MockAtleta.numero = numero;
            MockAtleta.data_entrada = data_entrada;
            MockAtleta.data_oficio = data_oficio;
            MockAtleta.data_nascimento = data_nascimento;
            MockAtleta.comprovante = comprovante;
            MockAtleta.matricula = "teste";
            MockAtleta.associacao = associacao;
            processCalled = true;
        }
    }
    public static void alterarAtletaDados(String nome, String numero, String data_entrada, String data_oficio, String matricula){
        if(matricula != MockAtleta.matricula)
            processCalled = false;
        if(numero.isEmpty() | data_entrada.isEmpty() | data_oficio.isEmpty() | matricula.isEmpty())
            processCalled = false;
        else if(matricula != MockAtleta.matricula)
            processCalled = false;
        else {
            MockAtleta.nome = nome;
            MockAtleta.numero = numero;
            MockAtleta.data_entrada = data_entrada;
            MockAtleta.data_oficio = data_oficio;
            MockAtleta.data_nascimento = data_nascimento;
            MockAtleta.comprovante = comprovante;
            processCalled = true;
        }
    }
    public static void transferirAtleta(String numero, String data_oficio, String comprovante, String data_entrada, String matricula, MockAssociacao associacao){
        if(comprovante.isEmpty() | numero.isEmpty() | data_entrada.isEmpty() | data_oficio.isEmpty() | matricula.isEmpty() | associacao == null )
            processCalled = false;
        else if(associacao.getClass() != MockAssociacao.class)
            processCalled = false;
        else if(matricula != MockAtleta.matricula)
            processCalled = false;
        else{
            MockAtleta.associacao = associacao;
            processCalled = true;
        }
    }
    public static ResultSet getDadosAtleta(String matricula){
        if(matricula != MockAtleta.matricula){
            processCalled = false;
        }
        else {
            processCalled = true;
        }
        return null;
    }
    public String getMatricula() {
        return matricula;
    }
    public void validateSuccess(){
        assertTrue(processCalled);
    }
    public void validateFail(){
        assertFalse(processCalled);
    }
}
