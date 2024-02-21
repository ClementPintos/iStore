package View.ReadItems;

import DAO.ItemDAO;
import DAO.StoreDAO;
import Model.Item;
import Model.Store;
import View.DeleteItem.DeleteItemSousPanel;
import View.MainWindow;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ReadItemsMainPanel extends JPanel {

    private MainWindow mainWindow;
    private ItemDAO itemDAO;
    private StoreDAO storeDAO;

    public ReadItemsMainPanel(MainWindow mainWindow, ItemDAO itemDAO, StoreDAO storeDAO) {
        this.mainWindow = mainWindow;
        this.itemDAO = itemDAO;
        this.storeDAO = storeDAO;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void refreshPanel() {
        removeAll();
        List<Item> items;
        List<Store> stores;
        JPanel header = new JPanel(new GridLayout(1, 4));
        header.add(new JLabel("Nom du Store"));
        header.add(new JLabel("Nom Produit"));
        header.add(new JLabel("Prix Produit"));
        header.add(new JLabel("Quantité Produit"));
        add(header);

        try {
            if (mainWindow.isAdmin()) {
                stores = storeDAO.getStores();

                for (Store store : stores) {
                    items = itemDAO.getItemsFromStore(store.getStoreId());
                    for (Item item : items) {
                        add(new ReadItemsSousPanel(item, itemDAO, storeDAO, store.getStoreName(), this));
                    }
                }
            } else {
                items = itemDAO.getItemsFromStore(mainWindow.getConnectedUser().getStore());
                for (Item item : items) {
                    String storeName = storeDAO.getStoreName(mainWindow.getConnectedUser().getStore());
                    add(new ReadItemsSousPanel(item, itemDAO, storeDAO, storeName, this));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors du rafraîchissement de la page", "Erreur", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
        revalidate();
        repaint();
    }

}
