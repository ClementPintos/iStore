package DAO;

import java.sql.SQLException;
import java.util.List;

import Model.Item;
import Model.User;

public interface UserDAO {

    int getLastUserId() throws SQLException;
    int getUserCount() throws SQLException;
    boolean addUser(User newUser) throws SQLException;
    boolean deleteUser(String loginCible) throws SQLException;
    void whitelist(String loginCible) throws SQLException;
    void updateUser(int id, User user) throws SQLException;
    void mettreAuTravail(int idStore, List<User> employes) throws SQLException;
    void mettreAuChomage(int idStore) throws SQLException;

    User checkLogin(String inputLogin, String inputPassword) throws SQLException;
    User getUser(String login) throws SQLException;
    List<User> getNonWhitelistedUsers() throws SQLException;
    List<User> getUsers() throws SQLException;
    List<User> getEmployes(int idStore) throws SQLException;
    List<User> getUnemployed() throws SQLException;

}

