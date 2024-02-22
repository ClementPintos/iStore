package Controller;

import DAO.UserDAO;
import Model.User;
import View.WhitelistUser.WhitelistUserMainPanel;
import View.WhitelistUser.WhitelistUserSousPanel;

import javax.swing.*;
import java.sql.SQLException;

public class WhitelistUserController {
    private final UserDAO userDAO;
    private final WhitelistUserMainPanel parentPanel;

    public WhitelistUserController(WhitelistUserMainPanel parentPanel, UserDAO userDAO) {
        this.userDAO = userDAO;
        this.parentPanel = parentPanel;
    }

    public void refreshPanel() {
        try {
            for (User user : userDAO.getNonWhitelistedUsers()) {
                parentPanel.add(new WhitelistUserSousPanel(user, userDAO, parentPanel));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors du rafraîchissement de la page", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        parentPanel.revalidate();
        parentPanel.repaint();
    }

}
