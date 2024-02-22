package Database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {

Dotenv dotenv = Dotenv.configure()
        .directory("./")
        .filename("infos.env")
        .load();
    String url = dotenv.get("DB_PATH");
    String user = dotenv.get("DB_USER");
    String password = dotenv.get("DB_PASSWORD");

    public Connection connect(){

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement du driver JDBC");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de donnée");
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Connection à la base de donnée échouée");
            e.printStackTrace();
        }
        return connection;
    }

}
