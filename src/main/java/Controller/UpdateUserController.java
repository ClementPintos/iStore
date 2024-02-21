package Controller;

import DAO.StoreDAO;
import DAO.UserDAO;
import Model.User;
import View.UpdateUser.UpdateUserMainPanel;
import View.UpdateUser.UpdateUserSousPanel;

import javax.swing.*;
import java.sql.SQLException;

public class UpdateUserController {
    private UserDAO userDAO;
    private StoreDAO storeDAO;
    private UpdateUserMainPanel parentPanel;

    public UpdateUserController(UpdateUserMainPanel parentPanel, UserDAO userDAO, StoreDAO storeDAO) {
        this.userDAO = userDAO;
        this.storeDAO = storeDAO;
        this.parentPanel = parentPanel;
    }

    public void refreshPanel() {
        try {
            for (User user : userDAO.getUsers()) {
                parentPanel.add(new UpdateUserSousPanel(user, userDAO, storeDAO, parentPanel));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors du rafraîchissement de la page", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        parentPanel.revalidate();
        parentPanel.repaint();
    }

}
