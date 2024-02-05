package DAO;

import Database.DbManager;
import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    private DbManager dbManager;

    public UserDAOImpl(DbManager dbManager){
        this.dbManager = dbManager;
    }

    @Override
    public User getUserById(int id){

        String query = "SELECT * FROM user WHERE id = ?";

        try(
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, id);
            try(ResultSet result = statement.executeQuery()){
                if(result.next()){
                    return new User(
                        result.getString("email"),
                        result.getString("pseudo"),
                        result.getString("password")
                    );
                }
            }
        } catch (SQLException e){
            System.out.println("Erreur lors de la récupération de l'utilisateur avec l'ID : " + id);
        }
        return null;
    }
}
