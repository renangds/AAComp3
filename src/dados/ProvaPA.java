package dados;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvaPA extends Banco {

    public ResultSet buscarProvasporCompeticao(String nome_competicao) throws SQLException, ClassNotFoundException {
        return super.executeReturn("SELECT nome, categoria, classe FROM prova WHERE nome_competicao = '" + nome_competicao + "';");
    }

    public ResultSet buscarProva(String nome) throws SQLException, ClassNotFoundException {
        return super.executeReturn("SELECT * FROM prova WHERE nome = '" + nome + "';");
    }

    public void inserir(String nome, String classe, String categoria, String nome_competicao) throws SQLException, ClassNotFoundException {
        super.execute("INSERT INTO prova(nome, classe, categoria, nome_competicao)" +
                " VALUES('" + nome + "', '" + classe + "' , '" + categoria + "', '" + nome_competicao + "');");
    }
}
