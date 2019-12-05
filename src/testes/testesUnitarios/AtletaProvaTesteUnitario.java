package testes.testesUnitarios;

import org.junit.jupiter.api.Test;
import testes.Mocks.MockAtletaProva;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AtletaProvaTesteUnitario {
    @Test
    public void testePontuarAtletaNull() {
        MockAtletaProva atletaProvateste = new MockAtletaProva();
        atletaProvateste.pontuarAtleta("testeAtletaProva", "teste", "");
        atletaProvateste.validateFail();
    }
    @Test
    public void testeGetAtletaProvaNull(){
        MockAtletaProva atletaProvateste = new MockAtletaProva();
        atletaProvateste.getAtletasProva("");
        atletaProvateste.validateFail();
    }
}
