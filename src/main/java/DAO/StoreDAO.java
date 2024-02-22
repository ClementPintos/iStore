package DAO;

import Model.Store;

import java.sql.SQLException;
import java.util.List;

public interface StoreDAO {

    boolean addStore(Store store) throws SQLException;
    int getLastStoreId() throws SQLException;
    void deleteStore(int idStore) throws SQLException;
    List<Store> getStores() throws SQLException;
    int getStoreId(String nomStore);
    String getStoreName(int idStore);
}
