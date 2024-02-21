package View.DeleteItem;

import DAO.ItemDAO;
import Model.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DeleteItemSousPanel extends JPanel {
    public DeleteItemSousPanel(String storeName, Item item, ItemDAO itemDAO, DeleteItemMainPanel parentPanel) {
        setLayout(new GridLayout(1, 4));

        JLabel storeNameLabel = new JLabel(storeName);
        JLabel itemNameLabel = new JLabel(item.getItemName());
        JLabel itemPriceLabel = new JLabel(String.valueOf(item.getItemPrice()));

        JButton deleteButton = new JButton("Supprimer");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    itemDAO.deleteItem(item.getItemId(), storeName);
                    parentPanel.refreshPanel();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Echec lors de la suppression de l'article", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(storeNameLabel);
        add(itemNameLabel);
        add(itemPriceLabel);
        add(deleteButton);
    }
}
