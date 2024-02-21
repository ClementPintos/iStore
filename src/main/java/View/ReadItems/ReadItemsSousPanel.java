package View.ReadItems;

import DAO.ItemDAO;
import DAO.StoreDAO;
import Model.Item;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ReadItemsSousPanel extends JPanel {

    public ReadItemsSousPanel(Item item, ItemDAO itemDAO, StoreDAO storeDAO, String storeName, ReadItemsMainPanel parentPanel) {

        setLayout(new GridLayout(1, 4));

        int storeId = storeDAO.getStoreId(storeName);
        int itemId = item.getItemId();
        int itemQuantity = 0;

        try {
            itemQuantity = itemDAO.getItemQuantity(itemId, storeId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        JLabel storeNameLabel = new JLabel(storeName);
        JLabel itemNameLabel = new JLabel(item.getItemName());
        JLabel itemPriceLabel = new JLabel(String.valueOf(item.getItemPrice()));
        JLabel itemQuantityLabel = new JLabel(String.valueOf(itemQuantity));

        add(storeNameLabel);
        add(itemNameLabel);
        add(itemPriceLabel);
        add(itemQuantityLabel);
    }

}
