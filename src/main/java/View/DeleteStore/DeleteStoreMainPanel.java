package View.DeleteStore;

import DAO.StoreDAO;
import DAO.UserDAO;
import Model.Store;
import View.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class DeleteStoreMainPanel extends JPanel {
    private StoreDAO storeDAO;
    private UserDAO userDAO;
    private List<Store> stores;
    private MainWindow mainWindow;

    public DeleteStoreMainPanel(StoreDAO storeDAO, UserDAO userDAO, MainWindow mainWindow) {
        this.storeDAO = storeDAO;
        this.userDAO = userDAO;
        this.mainWindow = mainWindow;
        try {
            this.stores = storeDAO.getStores();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void refreshPanel() {
        removeAll();
        try {
            this.stores = storeDAO.getStores();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        JPanel header = new JPanel(new GridLayout(1, 2));
        header.add(new JLabel("Nom du Store"));
        header.add(new JLabel(""));
        add(header);

        for (Store store : stores) {
            add(new DeleteStoreSousPanel(store, storeDAO, userDAO, this));
        }
        revalidate();
        repaint();
    }

}

