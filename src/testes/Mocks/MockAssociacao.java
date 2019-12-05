package testes.Mocks;

import dominio.AssociacaoMT;

import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MockAssociacao extends AssociacaoMT {
    private static boolean processCalled;
    static String numero_oficio;
    static String data;
    static String nome;
    static String sigla;
    static String endereco;
    static String telefone;
    static String comprovante_pagamento;
    static String matricula;

    public static void cadastrarAssociacao(String numero_oficio, String data, String nome, String sigla, String endereco, String telefone, String comprovante_pagamento) {
        if(numero_oficio.isEmpty() | data.isEmpty() | nome.isEmpty() | sigla.isEmpty() | endereco.isEmpty() | telefone.isEmpty() | comprovante_pagamento.isEmpty())
            processCalled = false;
        else {
            MockAssociacao.numero_oficio = numero_oficio;
            MockAssociacao.data = data;
            MockAssociacao.nome = nome;
            MockAssociacao.sigla = sigla;
            MockAssociacao.endereco = endereco;
            MockAssociacao.telefone = telefone;
            MockAssociacao.comprovante_pagamento = comprovante_pagamento;
            MockAssociacao.matricula = "teste";
            processCalled = true;
        }
    }
    public static void alterarAssociacaoDados(String numero_oficio, String data, String nome, String sigla, String endereco, String telefone, String comprovante_pagamento, String matricula){
        if(matricula != MockAssociacao.matricula)
            processCalled = false;
        else if (numero_oficio.isEmpty() | data.isEmpty() | nome.isEmpty() | sigla.isEmpty() | endereco.isEmpty() | telefone.isEmpty() | comprovante_pagamento.isEmpty() | matricula.isEmpty())
            processCalled = false;
        else{
            MockAssociacao.numero_oficio = numero_oficio;
            MockAssociacao.data = data;
            MockAssociacao.nome = nome;
            MockAssociacao.sigla = sigla;
            MockAssociacao.endereco = endereco;
            MockAssociacao.telefone = telefone;
            MockAssociacao.comprovante_pagamento = comprovante_pagamento;
            processCalled = true;
        }
    }
    public static ResultSet getDadosAssociacao(String matricula){
        if(matricula != MockAssociacao.matricula)
            processCalled = false;
        else
            processCalled = true;
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
