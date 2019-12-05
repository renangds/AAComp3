package testes.testesUnitarios;

import exceptions.AtletaJaInscritoEmProvaException;
import exceptions.ExceptionDadosIncompletos;
import exceptions.JaExisteException;
import exceptions.MatriculaInvalidaException;
import org.junit.jupiter.api.Test;
import testes.Mocks.MockProva;

import java.sql.SQLException;


public class ProvaTesteUnitario {
    @Test
    public void testeCadastrarProvaNull() throws ClassNotFoundException, SQLException{
        MockProva testeProva = new MockProva();
        testeProva.cadastrarProva("testeProva","testeProva","", "testeProva");
        testeProva.validateFail();
    }
    @Test
    public void testeInscreverAtletaProvaMatNull() throws ClassNotFoundException, SQLException{
        MockProva testeProva = new MockProva();
        testeProva.inscreverAtletaProva("","testeProva");
        testeProva.validateFail();
    }
    @Test
    public void testeInscreverAtletaProvaMatInvalida() throws ClassNotFoundException, SQLException{
        MockProva testeProva = new MockProva();
        testeProva.inscreverAtletaProva("matriculaInvalida","testeProva");
        testeProva.validateFail();
    }
    @Test
    public void testeInscreverAtletaProvaNull() throws ClassNotFoundException, SQLException{
        MockProva testeProva = new MockProva();
        testeProva.inscreverAtletaProva("testeProva","");
        testeProva.validateFail();
    }
}
