package Controller;

import DAO.StoreDAO;
import DAO.UserDAO;
import Model.Store;
import Model.User;
import View.AddStore.AddStoreMainPanel;
import View.AddStore.AddStoreSousPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddStoreController {

    public AddStoreController(UserDAO userDAO, StoreDAO storeDAO, AddStoreMainPanel mainPanel) {

        mainPanel.getAddButton().addActionListener(e -> {
            String storeName = mainPanel.getStoreNameField().getText();
            List<User> selectedUsers = new ArrayList<>();

            for (Component component : mainPanel.getSousPanel().getComponents()) {
                if (component instanceof AddStoreSousPanel sousPanel) {
                    if (sousPanel.isSelected()) {
                        selectedUsers.add(sousPanel.getUser());
                    }
                }
            }

            try {
                int nextId = storeDAO.getLastStoreId() + 1;
                Store store = new Store(nextId, storeName);
                if(storeDAO.addStore(store)){
                    JOptionPane.showMessageDialog(mainPanel, "Store ajouté avec succès.");
                }
                if(!selectedUsers.isEmpty()){
                    userDAO.mettreAuTravail(store.getStoreId(), selectedUsers);
                }
                mainPanel.refreshPanel();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}
