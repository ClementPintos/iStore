package DAO;

import Model.Item;

import java.sql.SQLException;

public interface ItemDAO {

    boolean addItem(Item item) throws SQLException;
    boolean deleteItem(int itemId) throws SQLException;
    boolean increaseItem(int itemId, int quantity) throws SQLException;
    boolean decreaseItem(int itemId, int quantity) throws SQLException;
}
