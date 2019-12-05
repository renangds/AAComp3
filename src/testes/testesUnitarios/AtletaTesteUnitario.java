package testes.testesUnitarios;

import org.junit.jupiter.api.Test;
import testes.Mocks.MockAssociacao;
import testes.Mocks.MockAtleta;

public class AtletaTesteUnitario{
    MockAssociacao associacaoMock = new MockAssociacao();
    @Test
    public void testeCadastrarAtletaNull(){
        MockAtleta atletaTeste = new MockAtleta();
        atletaTeste.cadastrarAtleta("testeAtleta", "testeAtleta", "testeAtleta", "", "", "", associacaoMock);
        atletaTeste.validateFail();
    }

    @Test
    public void testeAlterarAtletaDadosNull(){
        MockAtleta atletaTeste = new MockAtleta();
        atletaTeste.alterarAtletaDados("testeAtleta", "testeAtleta", "testeAtleta", "", "");
        atletaTeste.validateFail();
    }

    @Test
    public void testeAlterarAtletaMatriculaInvalida(){
        MockAtleta atletaTeste = new MockAtleta();
        atletaTeste.alterarAtletaDados("testeAtleta", "testeAtleta", "testeAtleta", "testeAtleta", "matriculaInvalida");
        atletaTeste.validateFail();
    }

    @Test
    public void testeTransferirAtletaNull(){
        MockAtleta atletaTeste = new MockAtleta();
        atletaTeste.transferirAtleta("testeAtleta", "", "", "testeAtleta", "testeAtleta", associacaoMock);
        atletaTeste.validateFail();
    }

    @Test
    public void testeTransferirAtletaMatNull(){
        MockAtleta atletaTeste = new MockAtleta();
        atletaTeste.transferirAtleta("testeAtleta", "testeAtleta", "testeAtleta", "testeAtleta", "testeAtleta", associacaoMock);
        atletaTeste.validateFail();
    }
    @Test
    public void testeGetDadosAtleta(){
        MockAtleta atletaTeste = new MockAtleta();
        atletaTeste.getDadosAtleta("matriculaInvalida");
        atletaTeste.validateFail();
    }
}
