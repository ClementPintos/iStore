package Controller;

import DAO.StoreDAO;
import DAO.UserDAO;
import Model.Store;
import View.DeleteStore.DeleteStoreMainPanel;
import View.DeleteStore.DeleteStoreSousPanel;

import javax.swing.*;
import java.sql.SQLException;

public class DeleteStoreController {
    private final StoreDAO storeDAO;
    private UserDAO userDAO;
    private final DeleteStoreMainPanel parentPanel;

    public DeleteStoreController(DeleteStoreMainPanel parentPanel, StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
        this.parentPanel = parentPanel;
    }

    public void refreshPanel() {
        try {
            for (Store store : storeDAO.getStores()) {
                parentPanel.add(new DeleteStoreSousPanel(store, storeDAO, userDAO, parentPanel));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors du rafraîchissement de la page", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        parentPanel.revalidate();
        parentPanel.repaint();
    }

}
