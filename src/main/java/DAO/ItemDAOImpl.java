package DAO;

import Database.DbManager;
import Model.Item;
import Model.User;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    private DbManager dbManager;
    public ItemDAOImpl(DbManager dbManager){
        this.dbManager = dbManager;
    }

    public int getLastItemId(){

        String query = "SELECT MAX(id_item) AS max FROM item";

        try(
                Connection connection = dbManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ){
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                return resultSet.getInt("max");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur avec la récupération de l'id des produits.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return 1;
    }

    public boolean addItemInStore(Item item, int idStore) {

        String query = "INSERT INTO item VALUES (?, ?, ?);";

        try(
            Connection connection = dbManager.getConnection();

            PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setInt(1, item.getItemId());
            statement.setString(2, item.getItemName());
            statement.setDouble(3, item.getItemPrice());

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("id : " + item.getItemId() + ", name : " + item.getItemName());
            JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du produit au store.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public boolean addItemInInventory(int idItem, int idStore) {

        String query = "INSERT INTO inventory VALUES (?, ?, 0);";

        try(
                Connection connection = dbManager.getConnection();

                PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setInt(1, idStore);
            statement.setInt(2, idItem);

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("id : " + idStore + ", id item : " + idItem);
            return false;
        }
    }
    public int alreadyExists(Item item){

        String query = "SELECT id_item FROM item WHERE name_item = ? && price_item = ?;";

        try(
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setString(1, item.getItemName());
            statement.setDouble(2, item.getItemPrice());

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                return resultSet.getInt("id_item");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la vérification du produit.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }
    public int getItemQuantity(int id_item, int id_store){

        String query = "SELECT quantity_item FROM inventory WHERE id_item = ? && id_store = ?;";

        try(
                Connection connection = dbManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setInt(1, id_item);
            statement.setInt(2, id_store);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                return resultSet.getInt("quantity_item");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la recherche de la quantité du produit.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }
    public boolean deleteItem(int idItem, String nomStore){

        String query = "DELETE FROM inventory WHERE id_item = ? AND id_store IN (SELECT id_store FROM store WHERE name_store = ?)";

        try(
                Connection connection = dbManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setInt(1, idItem);
            statement.setString(2, nomStore);

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la suppression du store.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public boolean increaseItem(int idItem, int idStore, int quantity){

        String query = "UPDATE inventory SET quantity_item = quantity_item + ? WHERE id_store = ? AND id_item = ?;";

        try(
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setInt(1, quantity);
            statement.setInt(2, idStore);
            statement.setInt(3, idItem);

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de l' ajout de produit en stock.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }    }
    public boolean decreaseItem(int idItem, int idStore, int quantity){
        String query = "UPDATE inventory SET quantity_item = quantity_item - ? WHERE id_store = ? AND id_item = ?;";

        try(
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setInt(1, quantity);
            statement.setInt(2, idStore);
            statement.setInt(3, idItem);

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors du retrait de produit en stock.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public List<Item> getItemsFromStore(int storeId) {

        String query = "SELECT * FROM item NATURAL JOIN inventory WHERE id_store = ? && id_item > 1";
        List<Item> items = new ArrayList<>();

        try(
            Connection connection = dbManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
        ){
            statement.setInt(1, storeId);
            try(ResultSet result = statement.executeQuery()){
                while(result.next()){
                    int id_item = result.getInt("id_item");
                    String name_item = result.getString("name_item");
                    double price_item = result.getDouble("price_item");
                    items.add(new Item(id_item, name_item, price_item));
                }
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des items de ce store", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return items;
    }

}
