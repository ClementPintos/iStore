package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {

    String BDD = "istore";
    String url = "jdbc:mysql://localhost:3306/" + BDD;
    String user = "root";
    String password = "!Route$assword.";

    public Connection connect(){

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement du driver JDBC");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de donnée");
        } catch (Exception e){
            System.out.println("Connection à la base de donnée échouée");
        }
        return connection;
    }

}
