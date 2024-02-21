package Controller;

import DAO.*;
import View.*;
import View.AddStore.AddStoreMainPanel;
import View.DeleteStore.DeleteStoreMainPanel;
import View.DeleteUser.DeleteUserMainPanel;
import View.ReadEmployees.ReadEmployeesMainPanel;
import View.ReadUser.ReadUserMainPanel;
import View.UpdateUser.UpdateUserMainPanel;
import View.WhitelistUser.WhitelistUserMainPanel;

import javax.swing.*;
import java.sql.SQLException;

public class AccueilController {

    private MainWindow mainWindow;
    private UserDAO userDAO;
    private StoreDAO storeDAO;
    private ItemDAO itemDAO;

    public AccueilController(MainWindow mainWindow, AccueilPanel acceuilPanel, UserDAOImpl userDAO, StoreDAOImpl storeDAO, ItemDAOImpl itemDAO){
        this.mainWindow = mainWindow;
        this.userDAO = userDAO;
        this.storeDAO = storeDAO;
        // this.itemDAO = itemDAO;

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
        acceuilPanel.setDeconnectionButtonAction(e -> {
            mainWindow.deconnexion();
        });

    }

    private void openAddUserWindow() throws SQLException {
        AddUserPanel addUserMainPanel = new AddUserPanel(userDAO, storeDAO);
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
        UpdateUserController updateUserController = new UpdateUserController(updateUserMainPanel, userDAO, storeDAO);
        updateUserMainPanel.refreshPanel();
        openWindow("Update User", updateUserMainPanel);

    }

    private void openReadUserWindow() throws SQLException {
        ReadUserMainPanel readUserMainPanel = new ReadUserMainPanel(userDAO);
        ReadUserController updateUserController = new ReadUserController(readUserMainPanel, userDAO);
        readUserMainPanel.refreshPanel();
        openWindow("Read User", readUserMainPanel);

    }

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
