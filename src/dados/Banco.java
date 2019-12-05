package dados;


import java.sql.*;

public abstract class Banco{
    final private String driver = "org.h2.Driver";
    final private String user = "sa";
    final private String password = "";
    final private String url = "jdbc:h2:~/test";
    private Connection conn = null;

    protected ResultSet executeReturn (String query)throws ClassNotFoundException, SQLException{
        Class.forName(driver);
        this.conn = DriverManager.getConnection(url, user, password);
        PreparedStatement stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet res = stmt.executeQuery();
        return res;
    }

    protected void execute(String query) throws ClassNotFoundException, SQLException{
        Class.forName(this.driver);
        this.conn = DriverManager.getConnection(url, user, password);
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.execute();
    }
}