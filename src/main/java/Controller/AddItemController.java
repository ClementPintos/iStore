package Controller;

import DAO.ItemDAO;
import DAO.StoreDAO;
import Model.Item;
import Model.Store;
import View.AddItem.AddItemMainPanel;

import javax.swing.*;
import java.sql.SQLException;

public class AddItemController {
    private final AddItemMainPanel parentPanel;
    private final ItemDAO itemDAO;
    private final StoreDAO storeDAO;

    public AddItemController(AddItemMainPanel parentPanel, ItemDAO itemDAO, StoreDAO storeDAO) {
        this.parentPanel = parentPanel;
        this.itemDAO = itemDAO;
        this.storeDAO = storeDAO;
    }

    public void addItem(Store store, String itemName, String itemPrice) {
        if(!isNumeric(itemPrice)){
            JOptionPane.showMessageDialog(null, "Prix invalide, veuillez rentrer uniquement des chiffres et utiliser un point à la place de la virgule", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int itemId = 0;
        try {
            itemId = itemDAO.getLastItemId()+1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int storeId = storeDAO.getStoreId(store.getStoreName());
        double price = Double.parseDouble(itemPrice);
        Item item = new Item(itemId, itemName, price);
        try {
            int newItemId = itemDAO.alreadyExists(item);
            if(newItemId != 0){
                if(!itemDAO.addItemInInventory(newItemId, storeId)){
                    JOptionPane.showMessageDialog(null, "Produit déjà présent dans ce store", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                itemDAO.addItemInStore(item, storeId);
                itemDAO.addItemInInventory(itemId, storeId);
            }
            parentPanel.refreshPanel();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Echec lors de l'ajout de l'article", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}
