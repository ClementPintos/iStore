package View.AddItem;

import Controller.AddItemController;
import DAO.ItemDAO;
import DAO.StoreDAO;
import Model.Store;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class AddItemMainPanel extends JPanel {
    private final StoreDAO storeDAO;
    private final AddItemController controller;

    public AddItemMainPanel(ItemDAO itemDAO, StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
        this.controller = new AddItemController(this, itemDAO, storeDAO);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void refreshPanel() {
        removeAll();
        List<Store> stores;
        try {
            stores = storeDAO.getStores();
            JPanel header = new JPanel(new GridLayout(1, 4));
            header.add(new JLabel("Nom du Store"));
            header.add(new JLabel("Nom Produit"));
            header.add(new JLabel("Prix Produit"));
            header.add(new JLabel(""));
            add(header);

            for (Store store : stores) {
                if(store.getStoreId() != 1){
                    add(new AddItemSousPanel(store, controller));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        revalidate();
        repaint();
    }
}
