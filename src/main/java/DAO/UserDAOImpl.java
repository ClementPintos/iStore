package DAO;

import Database.DbManager;
import Model.Item;
import Model.Store;
import Model.User;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private DbManager dbManager;

    public UserDAOImpl(DbManager dbManager){
        this.dbManager = dbManager;
    }

    public User checkLogin(String inputLogin, String inputPassword){

        String query = "SELECT * FROM user WHERE email = ?;";

        try(
                Connection connection = dbManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, inputLogin);
            try(ResultSet result = statement.executeQuery()){
                if(result.next()){
                    String email = result.getString("email");
                    String pseudo = result.getString("pseudo");
                    String password = result.getString("password");
                    String role = result.getString("role");
                    Boolean whitelisted = result.getBoolean("whitelisted");
                    if(password.equals(inputPassword)){
                        if(whitelisted){
                            return new User(email, pseudo, password, role, whitelisted);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Compte pas encore validé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Mot de passe erroné", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Login erroné", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e){
            System.out.println("Erreur lors de la récupération de l'utilisateur avec le login : " + inputLogin);
        }
        return null;
    }

    @Override
    public boolean addUser(User newUser){

        String query = "INSERT INTO user(email, pseudo, password, role, whitelisted) VALUES (?, ?, ?, ?, ?);";

        try(
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setString(1, newUser.getEmail());
            statement.setString(2, newUser.getPseudo());
            statement.setString(3, newUser.getPassword());
            statement.setString(4, newUser.getRole());
            statement.setBoolean(5, newUser.isWhitelisted());

            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout d'un nouvel utilisateur : " + e.getMessage());
            return false;

        }
    }

    @Override
    public int getUserCount() throws SQLException {

        String query = "SELECT COUNT(*) AS count FROM user";

        try(
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
        ){
          if(resultSet.next()){
              return resultSet.getInt("count");
          }
          else {
              throw new SQLException("Erreur lors de la récupération du nombre d'utilisateurs");
          }
        }
    }

    @Override
    public boolean readUser(String loginCible) throws SQLException {
        return false;
    }

    @Override
    public boolean updateUser(String loginCible) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteUser(String loginCible) throws SQLException {

        String query = "DELETE FROM user WHERE email = ?;";

        try(
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, loginCible);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la délétion de l'utilisateur : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean whitelist(String loginCible) throws SQLException {

        String query = "UPDATE user SET whitelisted = true WHERE email = ?;";

        try(
                Connection connection = dbManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, loginCible);

            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erreur lors du whitelisting de l'utilisateur : " + e.getMessage());
            return false;

        }
    }
    public List<User> getNonWhitelistedUsers() throws SQLException {

        String query = "SELECT * FROM user WHERE whitelisted = false;";
        List<User> users = new ArrayList<>();

        try(
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ) {
            try(ResultSet result = statement.executeQuery()){
                while(result.next()){
                    String email = result.getString("email");
                    String pseudo = result.getString("pseudo");
                    String password = result.getString("password");
                    String role = result.getString("role");
                    Boolean whitelisted = result.getBoolean("whitelisted");
                    users.add(new User(email, pseudo, password, role, whitelisted));
                }
            }
        } catch (SQLException e){
            System.out.println("Erreur lors de la récupération des utilisateurs non-whitelistés : ");
        }
        return users;
    }

    public List<User> getUsers() throws SQLException {

        String query = "SELECT * FROM user;";
        List<User> users = new ArrayList<>();
        try(
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ) {
            try(ResultSet result = statement.executeQuery()){
                while(result.next()){
                    String email = result.getString("email");
                    String pseudo = result.getString("pseudo");
                    String password = result.getString("password");
                    String role = result.getString("role");
                    Boolean whitelisted = result.getBoolean("whitelisted");
                    users.add(new User(email, pseudo, password, role, whitelisted));
                }
            }
        } catch (SQLException e){
            System.out.println("Erreur lors de la récupération des utilisateurs");
        }
        return users;
    }

    @Override
    public boolean addStore(Store store) throws SQLException {
        return false;
    }

    @Override
    public boolean addItem(Item item) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteItem(int itemId) throws SQLException {
        return false;
    }

    @Override
    public boolean increaseItem(int itemId, int quantity) throws SQLException {
        return false;
    }

    @Override
    public boolean decreaseItem(int itemId, int quantity) throws SQLException {
        return false;
    }

}
