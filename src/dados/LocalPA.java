package dados;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocalPA extends Banco {

    public void inserir(String nomeLocal, String logradouro, String piscina) throws SQLException, ClassNotFoundException {
        super.execute("INSERT INTO local(nomeLocal, logradouro, piscina)" +
                " VALUES('" + nomeLocal + "', '" + logradouro + "', '" + piscina + "');");
    }

    public void update(String nomeLocal, String logradouro, String piscina) throws SQLException, ClassNotFoundException{
        super.execute("UPDATE local SET logradouro = '" + logradouro + "', piscina = '" + piscina + "' WHERE nomeLocal = '" + nomeLocal + "';");
    }

    public ResultSet buscarTodosLocais() throws SQLException, ClassNotFoundException {
        return super.executeReturn("SELECT nomeLocal, logradouro, piscina FROM local ORDER BY nomeLocal ASC;");
    }

    public ResultSet buscarLocal(String nomeLocal) throws SQLException, ClassNotFoundException{
        return super.executeReturn("SELECT * FROM local WHERE nomelocal = '" + nomeLocal + "';");
    }
}