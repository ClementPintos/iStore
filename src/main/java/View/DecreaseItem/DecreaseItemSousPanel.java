package View.DecreaseItem;

import DAO.ItemDAO;
import DAO.StoreDAO;
import Model.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DecreaseItemSousPanel extends JPanel {
    public DecreaseItemSousPanel(Item item, StoreDAO storeDAO, ItemDAO itemDAO, String storeName, DecreaseItemMainPanel parentPanel) {

        setLayout(new GridLayout(1, 5));

        int storeId = storeDAO.getStoreId(storeName);
        int itemId = item.getItemId();
        int itemQuantity = 0;

        try {
            itemQuantity = itemDAO.getItemQuantity(itemId, storeId);;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        JTextField decreaseQuantityField = new JTextField();
        JLabel storeNameLabel = new JLabel(storeName);
        JLabel itemNameLabel = new JLabel(item.getItemName());
        JLabel itemPriceLabel = new JLabel(String.valueOf(item.getItemPrice()));
        JLabel itemQuantityLabel = new JLabel(String.valueOf(itemQuantity));;
        JButton decreaseButton = new JButton("Soustraire");

        decreaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int decreaseQuantity = Integer.parseInt(decreaseQuantityField.getText());

                try {
                    itemDAO.decreaseItem(itemId, storeId, decreaseQuantity);
                    parentPanel.refreshPanel();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Echec lors de la diminution de la quantité de l'article", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(storeNameLabel);
        add(itemNameLabel);
        add(itemPriceLabel);
        add(itemQuantityLabel);
        add(decreaseQuantityField);
        add(decreaseButton);
    }
}
