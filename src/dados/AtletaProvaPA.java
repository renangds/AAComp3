package dados;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AtletaProvaPA extends Banco {

    public ResultSet findAtletaProva(String matricula_atleta, String nome_prova) throws SQLException, ClassNotFoundException {
        return super.executeReturn("SELECT * FROM atletaprova WHERE matricula_atleta = '" + matricula_atleta + "' and nome_prova= '" + nome_prova + "';");
    }

    public void cadastrarAtletaProva(String matricula_atleta, String nome_prova) throws SQLException, ClassNotFoundException {
        super.execute("INSERT INTO atletaprova( matricula_atleta, nome_prova) VALUES('"+ matricula_atleta +"', '"+ nome_prova +"');");
    }

    public ResultSet findAtletasProva(String nome_prova) throws SQLException, ClassNotFoundException {
        return super.executeReturn("SELECT a.matricula, a.nome, b.tempo, b.ponto from atleta as a JOIN atletaprova as b on" +
                " b.matricula_atleta = a.matricula WHERE b.nome_prova = '"+ nome_prova +"' order by tempo nulls last;");
    }

    public void inserirTempo(String nome_prova, String matricula_atleta, String tempo) throws SQLException, ClassNotFoundException {
        super.execute("UPDATE atletaprova SET  tempo = '" + tempo + "' WHERE nome_prova = '" + nome_prova + "' and matricula_atleta = '" + matricula_atleta + "' ;");
    }

    public void inserirPonto(String nome_prova, String matricula_atleta, String ponto) throws SQLException, ClassNotFoundException {
        super.execute("UPDATE atletaprova SET  ponto = '" + ponto + "' WHERE nome_prova = '" + nome_prova + "' and matricula_atleta = '" + matricula_atleta + "' ;");
    }

    public ResultSet findPosicoes(String nome_prova) throws SQLException, ClassNotFoundException {
        return super.executeReturn("SELECT * FROM atletaprova WHERE nome_prova = '" + nome_prova + "' order by tempo nulls last;");
    }
}
