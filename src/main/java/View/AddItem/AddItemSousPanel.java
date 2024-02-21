package View.AddItem;

import DAO.ItemDAO;
import DAO.StoreDAO;
import Model.Item;
import Model.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddItemSousPanel extends JPanel {
    private JTextField itemNameField;
    private JTextField itemPriceField;

    public AddItemSousPanel(Store store, ItemDAO itemDAO, StoreDAO storeDAO, AddItemMainPanel parentPanel) {
        setLayout(new GridLayout(1, 4));

        JLabel storeNameLabel = new JLabel(store.getStoreName());
        itemNameField = new JTextField();
        itemPriceField = new JTextField();

        JButton addButton = new JButton("Ajouter");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int itemId = 1;
                int storeId = 1;
                if(!isNumeric(itemPriceField.getText())){
                    JOptionPane.showMessageDialog(null, "Prix invalide, veuillez rentrer uniquement des chiffres et utiliser un point à la place de la virgule", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    itemId = itemDAO.getLastItemId()+1;
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                storeId = storeDAO.getStoreId(storeNameLabel.getText());

                String itemName = itemNameField.getText();
                double itemPrice = Double.parseDouble(itemPriceField.getText());
                Item item = new Item(itemId, itemName, itemPrice);
                try {
                    int newItemId = itemDAO.alreadyExists(item);
                    System.out.println(newItemId);
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
        });

        add(storeNameLabel);
        add(itemNameField);
        add(itemPriceField);
        add(addButton);
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
