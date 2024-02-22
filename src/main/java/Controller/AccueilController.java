package Controller;

import DAO.*;
import View.AccueilPanel;
import View.AddItem.AddItemMainPanel;
import View.AddStore.AddStoreMainPanel;
import View.AddUserPanel;
import View.DecreaseItem.DecreaseItemMainPanel;
import View.DeleteItem.DeleteItemMainPanel;
import View.DeleteStore.DeleteStoreMainPanel;
import View.DeleteUser.DeleteUserMainPanel;
import View.IncreaseItem.IncreaseItemMainPanel;
import View.MainWindow;
import View.ReadEmployees.ReadEmployeesMainPanel;
import View.ReadItems.ReadItemsMainPanel;
import View.ReadUser.ReadUserMainPanel;
import View.UpdateUser.UpdateUserMainPanel;
import View.WhitelistUser.WhitelistUserMainPanel;

import javax.swing.*;
import java.sql.SQLException;

public class AccueilController {

    private final MainWindow mainWindow;
    private final UserDAO userDAO;
    private final StoreDAO storeDAO;
    private final ItemDAO itemDAO;

    public AccueilController(MainWindow mainWindow, AccueilPanel acceuilPanel, UserDAOImpl userDAO, StoreDAOImpl storeDAO, ItemDAOImpl itemDAO){
        this.mainWindow = mainWindow;
        this.userDAO = userDAO;
        this.storeDAO = storeDAO;
        this.itemDAO = itemDAO;

        acceuilPanel.setAddUserButtonAction(e -> {
            try {
                openAddUserWindow();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'ouverture de la page", "Erreur", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }
        });
        acceuilPanel.setReadUserButtonAction(e -> {
            try {
                openReadUserWindow();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'ouverture de la page", "Erreur", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }
        });
        acceuilPanel.setDeleteUserButtonAction(e -> {
            try {
                openDeleteUserWindow();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'ouverture de la page", "Erreur", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }
        });
        acceuilPanel.setUpdateUserButtonAction(e -> {
            try {
                openUpdateUserWindow();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'ouverture de la page", "Erreur", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }
        });
        acceuilPanel.setWhitelistUserButtonAction(e -> {
            if (mainWindow.isAdmin()){
                try {
                    openWhitelistUserWindow();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'ouverture de la page", "Erreur", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Vous avez besoin d'avoir un rôle Admin pour accéder à cette fonctionnalité", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        acceuilPanel.setAddStoreButtonAction(e -> {
            if (mainWindow.isAdmin()){
                try {
                    openAddStoreWindow();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'ouverture de la page", "Erreur", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Vous avez besoin d'avoir un rôle Admin pour accéder à cette fonctionnalité", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        acceuilPanel.setDeleteStoreButtonAction(e -> {
            if (mainWindow.isAdmin()){
                try {
                    openDeleteStoreWindow();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'ouverture de la page", "Erreur", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Vous avez besoin d'avoir un rôle Admin pour accéder à cette fonctionnalité", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        acceuilPanel.setReadEmployeesButtonAction(e -> {
            try {
                openReadEmployeesWindow();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'ouverture de la page", "Erreur", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }
        });

        acceuilPanel.setAddItemButtonAction(e -> {
            if (mainWindow.isAdmin()){
                try {
                    openAddItemWindow();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Vous avez besoin d'avoir un rôle Admin pour accéder à cette fonctionnalité", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        acceuilPanel.setDeleteItemButtonAction(e -> {
            if (mainWindow.isAdmin()){
                try {
                    openDeleteItemWindow();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Vous avez besoin d'avoir un rôle Admin pour accéder à cette fonctionnalité", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        acceuilPanel.setReadItemsButtonAction(e -> {
            try {
                openReadItemsWindow();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'ouverture de la page", "Erreur", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }
        });
        acceuilPanel.setIncreaseItemButtonAction(e -> {
            try {
                openIncreaseItemWindow();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'ouverture de la page", "Erreur", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }
        });
        acceuilPanel.setDecreaseItemButtonAction(e -> {
            try {
                openDecreaseItemWindow();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'ouverture de la page", "Erreur", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }
        });
        acceuilPanel.setDeconnectionButtonAction(e -> {
            mainWindow.deconnexion();
        });

    }


    ////////////////////////// Constructeurs Users

    private void openAddUserWindow() throws SQLException {
        AddUserPanel addUserMainPanel = new AddUserPanel(storeDAO);
        AddUserController addUserController = new AddUserController(userDAO, storeDAO, addUserMainPanel, mainWindow);
        addUserMainPanel.refreshPanel(mainWindow.isAdmin());
        openWindow("Add User", addUserMainPanel);
    }

    private void openDeleteUserWindow() throws SQLException {
        DeleteUserMainPanel deleteUserMainPanel = new DeleteUserMainPanel(userDAO, storeDAO, mainWindow);
        DeleteUserController deleteUserController = new DeleteUserController(deleteUserMainPanel, userDAO, storeDAO);
        deleteUserMainPanel.refreshPanel();
        openWindow("Delete User", deleteUserMainPanel);

    }
    private void openWhitelistUserWindow() throws SQLException {
        WhitelistUserMainPanel whitelistUserMainPanel = new WhitelistUserMainPanel(userDAO);
        WhitelistUserController whitelistUserController = new WhitelistUserController(whitelistUserMainPanel, userDAO);
        whitelistUserMainPanel.refreshPanel();
        openWindow("Whitelist User", whitelistUserMainPanel);

    }
    private void openUpdateUserWindow() throws SQLException {
        UpdateUserMainPanel updateUserMainPanel = new UpdateUserMainPanel(userDAO, storeDAO, mainWindow);
        UpdateUserController updateUserController = new UpdateUserController(updateUserMainPanel, mainWindow, userDAO, storeDAO);
        updateUserMainPanel.refreshPanel();
        openWindow("Update User", updateUserMainPanel);

    }

    private void openReadUserWindow() throws SQLException {
        ReadUserMainPanel readUserMainPanel = new ReadUserMainPanel(userDAO);
        ReadUserController updateUserController = new ReadUserController(readUserMainPanel, userDAO);
        readUserMainPanel.refreshPanel();
        openWindow("Read User", readUserMainPanel);

    }

    ////////////////////////// Constructeurs Stores

    private void openAddStoreWindow() throws SQLException {
        AddStoreMainPanel addStoreMainPanel = new AddStoreMainPanel(userDAO);
        AddStoreController addStoreController = new AddStoreController(userDAO, storeDAO, addStoreMainPanel);
        addStoreMainPanel.refreshPanel();
        openWindow("Add Store", addStoreMainPanel);
    }
    private void openDeleteStoreWindow() throws SQLException {
        DeleteStoreMainPanel deleteStoreMainPanel = new DeleteStoreMainPanel(storeDAO, userDAO, mainWindow);
        DeleteStoreController deleteStoreController = new DeleteStoreController(deleteStoreMainPanel, storeDAO);
        deleteStoreMainPanel.refreshPanel();
        openWindow("Delete Store", deleteStoreMainPanel);
    }
    private void openReadEmployeesWindow() throws SQLException {
        ReadEmployeesMainPanel readEmployeesMainPanel = new ReadEmployeesMainPanel(mainWindow, userDAO, storeDAO);
        ReadEmployeesController readEmployeesController = new ReadEmployeesController(readEmployeesMainPanel, userDAO, storeDAO);
        readEmployeesMainPanel.refreshPanel();
        openWindow("Visualiser les employés", readEmployeesMainPanel);
    }

    ////////////////////////// Constructeurs Items
    private void openAddItemWindow() throws SQLException {
        AddItemMainPanel addItemMainPanel = new AddItemMainPanel(itemDAO, storeDAO);
        AddItemController addItemController = new AddItemController(addItemMainPanel, itemDAO, storeDAO);
        addItemMainPanel.refreshPanel();
        openWindow("Ajouter un Item", addItemMainPanel);
    }

    private void openReadItemsWindow() throws SQLException {
        ReadItemsMainPanel readItemsMainPanel = new ReadItemsMainPanel(mainWindow, itemDAO, storeDAO);
        ReadItemsController readItemsController = new ReadItemsController(readItemsMainPanel);
        readItemsMainPanel.refreshPanel();
        openWindow("Visualiser les items", readItemsMainPanel);
    }

    private void openIncreaseItemWindow() throws SQLException {
        IncreaseItemMainPanel increaseItemMainPanel = new IncreaseItemMainPanel(mainWindow, itemDAO , storeDAO);
        IncreaseItemController increaseItemController = new IncreaseItemController(increaseItemMainPanel, itemDAO, storeDAO);
        increaseItemMainPanel.refreshPanel();
        openWindow("Augmenter la quantité d'un produit", increaseItemMainPanel);
    }

    private void openDecreaseItemWindow() throws SQLException {
        DecreaseItemMainPanel decreaseItemMainPanel = new DecreaseItemMainPanel(mainWindow, itemDAO , storeDAO);
        DecreaseItemController decreaseItemController = new DecreaseItemController(decreaseItemMainPanel, itemDAO, storeDAO);
        decreaseItemMainPanel.refreshPanel();
        openWindow("Diminuer la quantité d'un produit", decreaseItemMainPanel);
    }

    private void openDeleteItemWindow() throws SQLException {
        DeleteItemMainPanel deleteStoreMainPanel = new DeleteItemMainPanel(itemDAO, storeDAO);
        DeleteItemController deleteItemController = new DeleteItemController(deleteStoreMainPanel, itemDAO, storeDAO);
        deleteStoreMainPanel.refreshPanel();
        openWindow("Delete Item", deleteStoreMainPanel);
    }


    private void openWindow(String titre, JPanel panel) throws SQLException   {
        JDialog dialog = new JDialog();
        dialog.setTitle(titre);
        dialog.setModal(true);
        dialog.getContentPane().add(panel);
        dialog.setSize(1000, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

}
