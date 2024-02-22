package DAO;

import Model.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO {

    int getLastItemId() throws SQLException;
    int alreadyExists(Item item) throws SQLException;
    int getItemQuantity(int id_item, int id_store        ) throws SQLException;
    void addItemInStore(Item item, int idStore) throws SQLException;
    boolean addItemInInventory(int idItem, int idStore) throws SQLException;
    void deleteItem(int idItem, String nomStore) throws SQLException;
    void increaseItem(int idItem, int idStore, int quantity) throws SQLException;
    void decreaseItem(int idItem, int idStore, int quantity) throws SQLException;
    List<Item> getItemsFromStore(int storeId);
}
