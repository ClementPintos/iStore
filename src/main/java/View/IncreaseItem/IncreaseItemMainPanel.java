package View.IncreaseItem;

import DAO.ItemDAO;
import DAO.StoreDAO;
import Model.Item;
import Model.Store;
import View.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class IncreaseItemMainPanel extends JPanel {
    private final MainWindow mainWindow;
    private final ItemDAO itemDAO;
    private final StoreDAO storeDAO;

    public IncreaseItemMainPanel(MainWindow mainWindow, ItemDAO itemDAO, StoreDAO storeDAO) {
        this.mainWindow = mainWindow;
        this.itemDAO = itemDAO;
        this.storeDAO = storeDAO;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void refreshPanel() {




        removeAll();
        List<Store> stores;
        List<Item> items;
        JPanel header = new JPanel(new GridLayout(1, 5));
        header.add(new JLabel("Nom du Store"));
        header.add(new JLabel("Nom Produit"));
        header.add(new JLabel("Prix Produit"));
        header.add(new JLabel("Quantité Produit"));
        header.add(new JLabel("Quantité à ajouter"));
        add(header);

        try {
            if (mainWindow.isAdmin()) {
                stores = storeDAO.getStores();
                for (Store store : stores) {
                    if (store.getStoreId() != 1) {
                        items = itemDAO.getItemsFromStore(store.getStoreId());
                        for (Item item : items) {
                            add(new IncreaseItemSousPanel(item, storeDAO, itemDAO, store.getStoreName(), this));
                        }
                    }
                }
            }
            else {
                items = itemDAO.getItemsFromStore(mainWindow.getConnectedUser().getStore());
                String storeName = storeDAO.getStoreName(mainWindow.getConnectedUser().getStore());
                for (Item item : items) {
                    add(new IncreaseItemSousPanel(item, storeDAO, itemDAO, storeName, this));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        revalidate();
        repaint();
    }
}
