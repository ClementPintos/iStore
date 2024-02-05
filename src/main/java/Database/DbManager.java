package Database;

import java.sql.Connection;
import java.sql.SQLException;

public class DbManager {

    private Connection connection;

    public DbManager(){
        DbConfig dbConfig = new DbConfig();
        this.connection = dbConfig.connect();
    }

    public Connection getConnection(){
        return connection;
    }

    public void close(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e){
                System.out.println("Fermeture de la connexion à la database échouée");
            }
        }
    }

}
