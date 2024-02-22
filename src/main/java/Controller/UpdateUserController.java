package Controller;

import DAO.StoreDAO;
import DAO.UserDAO;
import Model.User;
import View.MainWindow;
import View.UpdateUser.UpdateUserMainPanel;
import View.UpdateUser.UpdateUserSousPanel;

import javax.swing.*;
import java.sql.SQLException;

public class UpdateUserController {
    private final UserDAO userDAO;
    private final StoreDAO storeDAO;
    private final UpdateUserMainPanel parentPanel;
    private final MainWindow mainWindow;

    public UpdateUserController(UpdateUserMainPanel parentPanel, MainWindow mainWindow, UserDAO userDAO, StoreDAO storeDAO) {
        this.userDAO = userDAO;
        this.storeDAO = storeDAO;
        this.parentPanel = parentPanel;
        this.mainWindow = mainWindow;
    }

    public void refreshPanel() {
        try {
            for (User user : userDAO.getUsers()) {
                parentPanel.add(new UpdateUserSousPanel(mainWindow, user, userDAO, storeDAO, parentPanel));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors du rafraîchissement de la page", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        parentPanel.revalidate();
        parentPanel.repaint();
    }

}
