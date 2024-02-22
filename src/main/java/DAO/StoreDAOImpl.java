package DAO;

import Database.DbManager;
import Model.Store;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreDAOImpl implements StoreDAO{

    private final DbManager dbManager;
    public StoreDAOImpl(DbManager dbManager){
        this.dbManager = dbManager;
    }

    public int getLastStoreId(){

        String query = "SELECT MAX(id_store) AS max FROM store";

        try(
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()
        ){
            if(resultSet.next()){
                return resultSet.getInt("max");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur avec la récupération de l'id des stores.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return 1;
    }
    public boolean addStore(Store store) throws SQLException {

        String store_query = "INSERT INTO store VALUES (?, ?);";
        String inventory_query = "INSERT INTO inventory VALUES (?, 1, 1);";

        try(
            Connection connection = dbManager.getConnection();
            PreparedStatement store_statement = connection.prepareStatement(store_query);
            PreparedStatement inventory_statement = connection.prepareStatement(inventory_query)
        ) {
            store_statement.setInt(1, store.getStoreId());
            inventory_statement.setInt(1, store.getStoreId());
            store_statement.setString(2, store.getStoreName());
            store_statement.executeUpdate();
            inventory_statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("id : " + store.getStoreId() + ", name : " + store.getStoreName());
            JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du magasin.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void deleteStore(int idStore) throws SQLException {

        String inventory_query = "DELETE FROM inventory WHERE id_store = ?;";
        String store_query = "DELETE FROM store WHERE id_store = ?;";

        try(
                Connection connection = dbManager.getConnection();
                PreparedStatement inventory_statement = connection.prepareStatement(inventory_query);
                PreparedStatement store_statement = connection.prepareStatement(store_query)
        ) {

            inventory_statement.setInt(1, idStore);
            store_statement.setInt(1, idStore);

            inventory_statement.executeUpdate();
            store_statement.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la suppression du store.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Store> getStores() throws SQLException {

        String query = "SELECT * FROM store WHERE id_store;";
        List<Store> stores = new ArrayList<>();
        try(
                Connection connection = dbManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            try(ResultSet result = statement.executeQuery()){
                while(result.next()){
                    int idStore = result.getInt("id_store");
                    String nomStore = result.getString("name_store");

                    stores.add(new Store(idStore, nomStore));
                }
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des stores", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return stores;
    }
    public String getStoreName(int idStore){

        String query = "SELECT name_store FROM store WHERE id_store = ?";

        try(
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setInt(1, idStore);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                return resultSet.getString("name_store");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur avec la récupération du nom du store.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return "0";
    }

    public int getStoreId(String nomStore){

        String query = "SELECT id_store FROM store WHERE name_store = ?";

        try(
                Connection connection = dbManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ){
            statement.setString(1, nomStore);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                return resultSet.getInt("id_store");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur avec la récupération du nom du store.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }
}
