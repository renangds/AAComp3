package dados;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AssociacaoPA extends Banco{
    public void update(String numero_oficio, String data_oficio, String nome, String sigla, String senha,  String matricula) throws SQLException, ClassNotFoundException {
        super.execute("UPDATE associacao SET numero_oficio = '" + numero_oficio + "', data_oficio = '" + data_oficio + "', nome = '" + nome + "', sigla = '" + sigla + "', senha = '" +
                senha + "' WHERE matricula = '" + matricula + "';");
    }

    public void inserir(String numero_oficio, String data_oficio, String nome, String sigla, String endereco, String telefone, String comprovante_pagamento, String matricula, String senha) throws SQLException, ClassNotFoundException {
        super.execute("INSERT INTO associacao(numero_oficio, data_oficio, nome, sigla, endereco, telefone, comprovante_pagamento, matricula, senha)" +
                " VALUES('" + numero_oficio + "', '" + data_oficio + "', '" + nome + "', '" + sigla + "', '" + endereco + "', '" + telefone + "', '" + comprovante_pagamento + "', '" + matricula + "', '" + senha + "');");
    }

    public ResultSet buscarSigla(String matricula) throws SQLException, ClassNotFoundException{
        return super.executeReturn("SELECT sigla FROM associacao WHERE matricula = (SELECT matricula_associacao FROM atleta WHERE matricula = '" + matricula + "');");
    }

    public ResultSet buscarTodasAssociacoes() throws SQLException, ClassNotFoundException {
        return super.executeReturn("SELECT matricula, nome FROM associacao ORDER BY nome ASC;");
    }

    public ResultSet buscarAssociacao(String matricula) throws SQLException, ClassNotFoundException{
        return super.executeReturn("SELECT * FROM associacao WHERE matricula = '" + matricula + "';");
    }

}