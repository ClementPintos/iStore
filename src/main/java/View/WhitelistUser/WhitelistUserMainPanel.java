package View.WhitelistUser;

import DAO.UserDAO;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class WhitelistUserMainPanel extends JPanel {
    private final UserDAO userDAO;
    private List<User> users;

    public WhitelistUserMainPanel(UserDAO userDAO) {
        this.userDAO = userDAO;
        try {
            this.users = userDAO.getUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void refreshPanel() {
        removeAll();
        try {
            this.users = userDAO.getNonWhitelistedUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        JPanel header = new JPanel(new GridLayout(1, 2));
        header.add(new JLabel("eMail"));
        header.add(new JLabel(""));
        add(header);

        for (User user : users) {
            add(new WhitelistUserSousPanel(user, userDAO, this));
        }
        revalidate();
        repaint();
    }

}

