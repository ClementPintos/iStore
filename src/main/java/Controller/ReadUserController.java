package Controller;

import DAO.UserDAO;
import Model.User;
import View.ReadUser.ReadUserMainPanel;
import View.ReadUser.ReadUserSousPanel;

import javax.swing.*;
import java.sql.SQLException;

public class ReadUserController {
    private final UserDAO userDAO;
    private final ReadUserMainPanel parentPanel;

    public ReadUserController(ReadUserMainPanel parentPanel, UserDAO userDAO) {
        this.userDAO = userDAO;
        this.parentPanel = parentPanel;
    }

    public void refreshPanel() {
        try {
            for (User user : userDAO.getUsers()) {
                parentPanel.add(new ReadUserSousPanel(user));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors du rafraîchissement de la page", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        parentPanel.revalidate();
        parentPanel.repaint();
    }

}