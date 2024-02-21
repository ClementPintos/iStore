package Controller;

import DAO.StoreDAO;
import DAO.UserDAO;
import Model.Store;
import Model.User;
import View.DeleteUser.DeleteUserMainPanel;
import View.DeleteUser.DeleteUserSousPanel;

import javax.swing.*;
import java.sql.SQLException;

public class DeleteUserController {
    private UserDAO userDAO;
    private StoreDAO storeDAO;
    private DeleteUserMainPanel parentPanel;

    public DeleteUserController(DeleteUserMainPanel parentPanel, UserDAO userDAO, StoreDAO storeDAO) {
        this.userDAO = userDAO;
        this.storeDAO = storeDAO;
        this.parentPanel = parentPanel;
    }

    public void refreshPanel() {
        try {
            for (User user : userDAO.getUsers()) {
                parentPanel.add(new DeleteUserSousPanel(user, userDAO, storeDAO, parentPanel));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors du rafraîchissement de la page", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        parentPanel.revalidate();
        parentPanel.repaint();
    }

}