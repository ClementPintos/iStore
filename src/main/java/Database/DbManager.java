package Database;

import java.sql.Connection;
import java.sql.SQLException;

public class DbManager {

    private DbConfig dbConfig;

    public DbManager(){
        this.dbConfig = new DbConfig();
    }

    public Connection getConnection(){
        return dbConfig.connect();
    }

}
