package dados;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompeticaoPA extends Banco{
    public ResultSet buscarTodasCompeticoes() throws SQLException, ClassNotFoundException {
        return super.executeReturn("SELECT nome, data FROM competicao ORDER BY nome ASC;");
    }

    public void inserir(String nome, String data) throws SQLException, ClassNotFoundException {
        super.execute("INSERT INTO competicao(nome, data)" +
                " VALUES('" + nome + "', '" + data + "');");
    }

    public void updateLocal(String nome, String local) throws SQLException, ClassNotFoundException {
        super.execute("UPDATE competicao SET nomelocal = '" + local + "' WHERE nome = '" + nome + "';");
    }

    public void update(String nome, String data, String nome_antigo) throws SQLException, ClassNotFoundException {
        super.execute("UPDATE competicao SET nome = '" + nome + "', data = '" + data + "' WHERE nome = '" + nome_antigo + "';");
    }

    public ResultSet buscarCompeticaoDados(String nome) throws SQLException, ClassNotFoundException{
        return super.executeReturn("SELECT nome, data FROM competicao WHERE nome = '" + nome + "';");
    }

    public ResultSet buscarPontuacaoFinal(String nome) throws SQLException, ClassNotFoundException{
        return super.executeReturn("SELECT nome,sigla,matricula, sum(ponto) as pontos from (SELECT ponto,matricula_associacao " +
                "from (SELECT matricula_atleta, t2.ponto from(SELECT * from (SELECT nome_prova,matricula_atleta,ponto FROM " +
                "atletaprova WHERE ponto IS NOT NULL) as T1 join prova on prova.nome = T1.nome_prova)as T2 where " +
                "t2.nome_competicao = '" + nome +"')as T3 join atleta on atleta.matricula = T3.matricula_atleta) as " +
                "T4 join associacao on associacao.matricula = T4.matricula_associacao group by matricula");
    }

    public ResultSet buscarCompeticaoLocal(String nome) throws SQLException, ClassNotFoundException{
        return super.executeReturn("SELECT nome, nomelocal FROM competicao WHERE nome = '" + nome + "';");
    }
}
