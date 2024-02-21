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
    public int getLastUserId(){

        String query = "SELECT MAX(id_user) AS max FROM user";

        try(
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
        ){
            if(resultSet.next()){
                return resultSet.getInt("max");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur avec la récupération de l'id des utilisateurs.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
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
                    int id_user = getLastUserId() + 1;
                    String email = result.getString("email");
                    String pseudo = result.getString("pseudo");
                    String password = result.getString("password");
                    String role = result.getString("role");
                    Boolean whitelisted = result.getBoolean("whitelisted");
                    int id_store = result.getInt("id_store");
                    if(password.equals(inputPassword)){
                        if(whitelisted){
                            return new User(id_user, email, pseudo, password, role, whitelisted, id_store);
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
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération de l'utilisateur avec le login : \" + inputLogin", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public boolean addUser(User newUser){

        String query = "INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?);";

        try(
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, newUser.getId());
            statement.setString(2, newUser.getEmail());
            statement.setString(3, newUser.getPseudo());
            statement.setString(4, newUser.getPassword());
            statement.setString(5, newUser.getRole());
            statement.setBoolean(6, newUser.isWhitelisted());
            statement.setInt(7, newUser.getStore());

            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout d'un nouvel utilisateur", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

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
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération du nombre d'utilisateurs", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public boolean readUser(String loginCible) throws SQLException {
        return false;
    }

    public void updateUser(int id, User user) throws SQLException {

        String query = "UPDATE user SET email = ?, password = ?, pseudo = ?, role = ?, id_store = ? WHERE id_user = ?;";

        try(
                Connection connection = dbManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getPseudo());
            statement.setString(4, user.getRole());
            statement.setInt(5, user.getStore());
            statement.setInt(6, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'update de l'utilisateur", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }

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
            JOptionPane.showMessageDialog(null, "Erreur lors de la délétion de l'utilisateur", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void whitelist(String loginCible) throws SQLException {

        String query = "UPDATE user SET whitelisted = true WHERE email = ?;";

        try(
                Connection connection = dbManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, loginCible);

            statement.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors du whitelisting de l'utilisateur", "Erreur", JOptionPane.ERROR_MESSAGE);
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
                    int id_user = result.getInt("id_user");
                    String email = result.getString("email");
                    String pseudo = result.getString("pseudo");
                    String password = result.getString("password");
                    String role = result.getString("role");
                    Boolean whitelisted = result.getBoolean("whitelisted");
                    int id_store = result.getInt("id_store");
                    users.add(new User(id_user, email, pseudo, password, role, whitelisted, id_store));
                }
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des utilisateurs non-whitelistés", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return users;
    }
    public User getUser(String login) throws SQLException {

        String query = "SELECT * FROM user WHERE email = ?";
        User user = null;
        try(
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, login);
            try(ResultSet result = statement.executeQuery()){
                if(result.next()){
                    int id = result.getInt("id_user");
                    String email = result.getString("email");
                    String pseudo = result.getString("pseudo");
                    String password = result.getString("password");
                    String role = result.getString("role");
                    Boolean whitelisted = result.getBoolean("whitelisted");
                    int id_store = result.getInt("id_store");
                    user = new User(id, email, pseudo, password, role, whitelisted, id_store);
                }
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des utilisateurs", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return user;
    }

    public List<User> getUsers() throws SQLException {

        String query = "SELECT * FROM user WHERE whitelisted = true;";
        List<User> users = new ArrayList<>();
        try(
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ) {
            try(ResultSet result = statement.executeQuery()){
                while(result.next()){
                    int id_user = result.getInt("id_user");
                    String email = result.getString("email");
                    String pseudo = result.getString("pseudo");
                    String password = result.getString("password");
                    String role = result.getString("role");
                    Boolean whitelisted = result.getBoolean("whitelisted");
                    int id_store = result.getInt("id_store");

                    users.add(new User(id_user, email, pseudo, password, role, whitelisted, id_store));
                }
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des utilisateurs", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return users;
    }
    public List<User> getUnemployed(){

        String query = "SELECT * from user WHERE id_store = 1 && whitelisted = true";
        List<User> users = new ArrayList<>();

        try(
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
        ){
            try(ResultSet result = statement.executeQuery()){
                while(result.next()){
                    int id_user = result.getInt("id_user");
                    String email = result.getString("email");
                    String pseudo = result.getString("pseudo");
                    String password = result.getString("password");
                    String role = result.getString("role");
                    Boolean whitelisted = result.getBoolean("whitelisted");
                    int id_store = result.getInt("id_store");
                    users.add(new User(id_user, email, pseudo, password, role, whitelisted, id_store));
                }
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des utilisateurs au chomage", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return users;
    }
    public List<User> getEmployes(int idStore){

        String query = "SELECT * from user WHERE id_store = ? && whitelisted = true";
        List<User> users = new ArrayList<>();

        try(
                Connection connection = dbManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ){
            statement.setInt(1, idStore);
            try(ResultSet result = statement.executeQuery()){
                while(result.next()){
                    int id_user = result.getInt("id_user");
                    String email = result.getString("email");
                    String pseudo = result.getString("pseudo");
                    String password = result.getString("password");
                    String role = result.getString("role");
                    Boolean whitelisted = result.getBoolean("whitelisted");
                    int id_store = result.getInt("id_store");
                    users.add(new User(id_user, email, pseudo, password, role, whitelisted, id_store));
                }
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des employés", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return users;
    }
    public void mettreAuChomage(int idStore){
        String query = "UPDATE user SET id_store = 1 WHERE id_store = ?;";

        try(
                Connection connection = dbManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setInt(1, idStore);

            statement.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la mise au chomage de l'utilisateur", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mettreAuTravail(int idStore, List<User> employes){
        String query = "UPDATE user SET id_store = ?  WHERE id_user = ?;";

        try(
                Connection connection = dbManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {

            for (User user : employes) {
                statement.setInt(1, idStore);
                statement.setInt(2, user.getId());
                statement.executeUpdate();
            }

            statement.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la modification du poste de l'utilisateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
