package Database;

import java.sql.Connection;

public class DbManager {

    private final DbConfig dbConfig;

    public DbManager(){
        this.dbConfig = new DbConfig();
    }

    public Connection getConnection(){
        return dbConfig.connect();
    }

}
