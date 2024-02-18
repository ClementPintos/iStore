package Controller;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import View.*;

import java.sql.SQLException;

public class AccueilController {

    private MainWindow mainWindow;
    private AccueilPanel acceuilPanel;
    private UserDAO userDAO;

    public AccueilController(MainWindow mainWindow, AccueilPanel acceuilPanel, UserDAOImpl userDAO){
        this.mainWindow = mainWindow;
        this.acceuilPanel = acceuilPanel;
        this.userDAO = userDAO;

        acceuilPanel.setAddUserButtonAction(e -> {
            openAddUserWindow();
        });
        acceuilPanel.setDeleteUserButtonAction(e -> {
            try {
                openDeleteUserWindow();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        acceuilPanel.setWhitelistUserButtonAction(e -> {
            if (mainWindow.isAdmin()){
                try {
                    openWhitelistUserWindow();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        acceuilPanel.setDeconnectionButtonAction(e -> {
            mainWindow.deconnexion();
        });

    }

    private void openAddUserWindow(){
        AddUserController addUserController = new AddUserController(mainWindow, userDAO);
        addUserController.showPanel();
    }
    private void openDeleteUserWindow() throws SQLException {
        DeleteUserController deleteUserController = new DeleteUserController(mainWindow, userDAO);
        deleteUserController.showPanel();
    }
    private void openWhitelistUserWindow() throws SQLException {
        WhitelistUserController whitelistUserController = new WhitelistUserController(mainWindow, userDAO);
        whitelistUserController.showPanel();
    }
}
