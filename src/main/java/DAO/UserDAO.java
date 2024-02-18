package DAO;

import java.sql.SQLException;
import java.util.List;

import Model.Item;
import Model.Store;
import Model.User;

public interface UserDAO {

    User checkLogin(String inputLogin, String inputPassword) throws SQLException;
    boolean addUser(User newUser) throws SQLException;
    int getUserCount() throws SQLException;
    boolean readUser(String loginCible) throws SQLException;
    boolean updateUser(String loginCible) throws SQLException;
    boolean deleteUser(String loginCible) throws SQLException;
    List<User> getNonWhitelistedUsers() throws SQLException;
    List<User> getUsers() throws SQLException;
    boolean whitelist(String loginCible) throws SQLException;
    boolean addStore(Store store) throws SQLException;
    boolean addItem(Item item) throws SQLException;
    boolean deleteItem(int itemId) throws SQLException;
    boolean increaseItem(int itemId, int quantity) throws SQLException;
    boolean decreaseItem(int itemId, int quantity) throws SQLException;
}

