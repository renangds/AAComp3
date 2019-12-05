package testes.Mocks;

import dados.AtletaPA;
import dados.AtletaProvaPA;
import dados.CompeticaoPA;
import dados.ProvaPA;
import dominio.ProvaMT;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MockProva extends ProvaMT {
    private static boolean processCalled;
    static String nome;
    static String categoria;
    static String classe;
    static String nome_competicao;

    private static CompeticaoPA GatewayCompeticao= new CompeticaoPA();
    private static ProvaPA GatewayProva= new ProvaPA();
    private static AtletaProvaPA GatewayAtletaProva= new AtletaProvaPA();
    private static AtletaPA GatewayAtleta= new AtletaPA();

    public static ResultSet getProvasCompeticao(String nome) throws SQLException, ClassNotFoundException {
        if(GatewayCompeticao.buscarCompeticaoDados(nome).next() == false){
            processCalled = false;
        }
        else
            processCalled = true;
        return null;
    }

    public static void cadastrarProva(String nome, String classe, String categoria, String nome_competicao) throws SQLException, ClassNotFoundException {
        if(GatewayProva.buscarProva(nome).next() == true){
            processCalled = false;
        } else if(nome.isEmpty() | classe.isEmpty() | categoria.isEmpty()){
            processCalled = false;
        } else{
            MockProva.nome = nome;
            MockProva.categoria = categoria;
            MockProva.classe = classe;
            MockProva.nome_competicao = nome_competicao;
            processCalled = true;
        }
    }

    public static void inscreverAtletaProva(String matricula_atleta, String nome_prova) throws SQLException, ClassNotFoundException{
        if(GatewayAtleta.buscarAtleta(matricula_atleta).next() == false){
            processCalled = false;
        }
        else if(matricula_atleta.isEmpty() | nome_prova.isEmpty()){
            processCalled = false;
        }
        else if(GatewayAtletaProva.findAtletaProva(matricula_atleta,nome_prova).next() == true){
            processCalled = false;
        }
        else{
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
