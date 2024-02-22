package View.DeleteItem;

import DAO.ItemDAO;
import DAO.StoreDAO;
import Model.Item;
import Model.Store;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class DeleteItemMainPanel extends JPanel {
    private final ItemDAO itemDAO;
    private final StoreDAO storeDAO;

    public DeleteItemMainPanel(ItemDAO itemDAO, StoreDAO storeDAO) {
        this.itemDAO = itemDAO;
        this.storeDAO = storeDAO;
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
                List<Item> items = itemDAO.getItemsFromStore(store.getStoreId());
                for (Item item : items) {
                    if(store.getStoreId() != 1){
                        add(new DeleteItemSousPanel(store.getStoreName(), item, itemDAO, this));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        revalidate();
        repaint();
    }
}
