package DAO;

import Model.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO {

    int getLastItemId() throws SQLException;
    int alreadyExists(Item item) throws SQLException;
    int getItemQuantity(int id_item, int id_store        ) throws SQLException;
    boolean addItemInStore(Item item, int idStore) throws SQLException;
    boolean addItemInInventory(int idItem, int idStore) throws SQLException;

    boolean deleteItem(int idItem, String nomStore) throws SQLException;

    boolean increaseItem(int idItem, int idStore, int quantity) throws SQLException;

    boolean decreaseItem(int idItem, int idStore, int quantity) throws SQLException;

    List<Item> getItemsFromStore(int storeId);
}
