package dados;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioPA extends Banco {

    public void inserir(String matricula, String senha, String nivel_acesso) throws SQLException, ClassNotFoundException {
        super.execute("INSERT INTO usuario(matricula, senha, nivel_acesso)" +
                " VALUES('" + matricula + "', '" + senha + "', '" + nivel_acesso + "');");
    }

    public void updateSenha(String matricula, String senha) throws SQLException, ClassNotFoundException {
        super.execute("UPDATE usuario set senha = '"  + senha + "' WHERE matricula = '" + matricula +"';");
    }

    public ResultSet getDadosUsuario(String matricula, String senha) throws SQLException, ClassNotFoundException {
        return super.executeReturn("SELECT nivel_acesso FROM usuario WHERE matricula = '" + matricula + "' AND senha = '" + senha + "';");
    }
}
